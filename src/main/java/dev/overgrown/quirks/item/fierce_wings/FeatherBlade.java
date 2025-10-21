package dev.overgrown.quirks.item.fierce_wings;

import dev.overgrown.quirks.item.registry.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FeatherBlade extends SwordItem {
    private static final int MAX_CHARGE_TIME = 40; // 2 seconds at 20 ticks/second
    private static final float MAX_DAMAGE_MULTIPLIER = 10.0f; // Cap on maximum damage
    private static final float DAMAGE_MULTIPLIER = 0.5f; // Base damage multiplier
    private final Set<UUID> chargedEntities = new HashSet<>();

    // Charge attack stages
    public enum ChargeStage {
        ENGAGED(0, 20),    // First 1 second
        TIRED(20, 35),     // Next 0.75 seconds
        DISENGAGED(35, 40); // Final 0.25 seconds

        public final int startTick;
        public final int endTick;

        ChargeStage(int startTick, int endTick) {
            this.startTick = startTick;
            this.endTick = endTick;
        }

        public static ChargeStage getStage(int useTicks) {
            for (ChargeStage stage : values()) {
                if (useTicks >= stage.startTick && useTicks < stage.endTick) {
                    return stage;
                }
            }
            return DISENGAGED;
        }
    }

    public FeatherBlade(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.FIERCE_WINGS_FEATHER) || super.canRepair(stack, ingredient);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return MAX_CHARGE_TIME;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.getDamage() >= itemStack.getMaxDamage() - 1) {
            return TypedActionResult.fail(itemStack);
        }

        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int useTicks = this.getMaxUseTime(stack) - remainingUseTicks;
        ChargeStage stage = ChargeStage.getStage(useTicks);

        // Visual and audio feedback based on stage
        if (!world.isClient) {
            switch (stage) {
                case ENGAGED:
                    // Steady holding - minimal effects
                    if (useTicks % 10 == 0) {
                        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.1f, 1.0f);
                    }
                    break;
                case TIRED:
                    // Shaking and rotating - more intense effects
                    if (useTicks % 5 == 0) {
                        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, SoundCategory.PLAYERS, 0.3f, 0.8f);
                    }
                    break;
                case DISENGAGED:
                    // Lowered spear - intense effects
                    if (useTicks % 3 == 0) {
                        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                                SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 0.5f, 0.6f);
                    }
                    break;
            }
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        int useTicks = this.getMaxUseTime(stack) - remainingUseTicks;
        if (useTicks < 5) return; // Minimum charge time

        ChargeStage stage = ChargeStage.getStage(useTicks);
        performChargeAttack(player, world, stack, stage, useTicks);

        // Apply durability damage based on charge time
        int durabilityCost = Math.max(1, useTicks / 10);
        stack.damage(durabilityCost, player, p -> p.sendToolBreakStatus(user.getActiveHand()));

        // Reset charge tracking for this entity
        chargedEntities.remove(user.getUuid());
    }

    private void performChargeAttack(PlayerEntity player, World world, ItemStack stack, ChargeStage stage, int chargeTicks) {
        Vec3d playerPos = player.getPos();
        Vec3d lookVec = player.getRotationVec(1.0F);
        float chargeProgress = (float) chargeTicks / MAX_CHARGE_TIME;

        // Calculate attack range based on charge time
        double attackRange = 3.0 + (chargeProgress * 4.0); // 3-7 block range

        // Get all entities in front of player
        world.getOtherEntities(player, player.getBoundingBox().stretch(lookVec.multiply(attackRange)).expand(1.0),
                        entity -> entity instanceof LivingEntity && entity.isAlive() && !chargedEntities.contains(entity.getUuid()))
                .forEach(entity -> {
                    if (entity instanceof LivingEntity target) {
                        handleEntityCollision(player, target, world, stack, stage, chargeProgress);
                        chargedEntities.add(target.getUuid()); // Mark as hit to prevent multiple hits
                    }
                });

        // Play charge release sound
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0f, 0.9f + chargeProgress * 0.3f);
    }

    private void handleEntityCollision(PlayerEntity attacker, LivingEntity target, World world, ItemStack stack, ChargeStage stage, float chargeProgress) {
        Vec3d attackerVel = attacker.getVelocity();
        Vec3d targetVel = target.getVelocity();

        // Calculate relative velocity
        Vec3d relativeVel = attackerVel.subtract(targetVel);
        double impactVelocity = relativeVel.length();

        // Calculate base damage from velocity (convert from blocks/tick to blocks/second)
        double velocityDamage = impactVelocity * 20.0 * DAMAGE_MULTIPLIER;

        // Apply stage modifiers
        float stageMultiplier = getStageMultiplier(stage);
        double finalDamage = Math.min(velocityDamage * stageMultiplier, MAX_DAMAGE_MULTIPLIER);

        // Ensure minimum damage even when standing still
        if (finalDamage < 1.0) {
            finalDamage = 1.0 + (chargeProgress * 2.0); // 1-3 minimum damage based on charge
        }

        // Apply damage
        if (target.damage(world.getDamageSources().playerAttack(attacker), (float) finalDamage)) {
            applyStageEffects(attacker, target, world, stage, impactVelocity);
        }

        // Apply knockback based on stage and velocity
        applyKnockback(attacker, target, stage, impactVelocity, chargeProgress);
    }

    private float getStageMultiplier(ChargeStage stage) {
        switch (stage) {
            case ENGAGED: return 1.2f; // Bonus damage for well-timed release
            case TIRED: return 0.8f;   // Reduced damage when tired
            case DISENGAGED: return 0.5f; // Minimal damage when disengaged
            default: return 1.0f;
        }
    }

    private void applyStageEffects(PlayerEntity attacker, LivingEntity target, World world, ChargeStage stage, double impactVelocity) {
        switch (stage) {
            case ENGAGED:
                // Engaged: dismount targets and apply strong effects
                if (target.hasVehicle()) {
                    target.stopRiding();
                }
                // Additional effects for high-velocity impacts
                if (impactVelocity > 0.5) {
                    target.setVelocity(target.getVelocity().add(0, 0.3, 0));
                }
                break;

            case TIRED:
                // Tired: standard knockback only
                world.playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, SoundCategory.PLAYERS, 0.7f, 1.0f);
                break;

            case DISENGAGED:
                // Disengaged: minimal effects
                world.playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, SoundCategory.PLAYERS, 0.5f, 1.2f);
                break;
        }
    }

    private void applyKnockback(PlayerEntity attacker, LivingEntity target, ChargeStage stage, double impactVelocity, float chargeProgress) {
        Vec3d knockbackDir = target.getPos().subtract(attacker.getPos()).normalize();
        double knockbackStrength = impactVelocity * chargeProgress;

        switch (stage) {
            case ENGAGED:
                knockbackStrength *= 1.5; // Strong knockback
                break;
            case TIRED:
                knockbackStrength *= 1.0; // Standard knockback
                break;
            case DISENGAGED:
                knockbackStrength *= 0.5; // Weak knockback
                break;
        }

        // Apply knockback
        if (knockbackStrength > 0.1) {
            target.setVelocity(target.getVelocity().add(knockbackDir.multiply(knockbackStrength)));
            target.velocityModified = true;
        }
    }

    // Helper method to get current charge stage for rendering
    public static ChargeStage getChargeStage(ItemStack stack, LivingEntity user) {
        if (user == null || !user.isUsingItem()) return null;
        int useTicks = user.getItemUseTime();
        return ChargeStage.getStage(useTicks);
    }
}