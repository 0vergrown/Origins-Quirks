package dev.overgrown.quirks.item.grenadier_bracer;

import dev.overgrown.quirks.Quirks;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.VariableIntPower;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import io.github.apace100.origins.Origins;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.registry.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GrenadierBracer extends Item {
    private static final Identifier NITRO_SWEAT_RESOURCE = new Identifier(Quirks.MOD_ID, "explosion/nitro_sweat_secretion_resource");
    private static final Identifier QUIRKS_LAYER_ID = new Identifier(Origins.MODID, "quirks");
    private static final Identifier EXPLOSION_QUIRK_ID = new Identifier(Quirks.MOD_ID, "explosion");

    private static final int MAX_CHARGE = 500;
    private static final int CHARGE_PER_UNIT = 10;
    private static final int COOLDOWN_TICKS = 20;
    private static final float MAX_EXPLOSION_POWER = 14.0f;

    public GrenadierBracer(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        int currentCharge = getCharge(stack);
        boolean isExplosionUser = hasExplosionQuirk(user);

        // Handle firing logic
        if (shouldFire(user, currentCharge, isExplosionUser)) {
            float explosionPower = calculateExplosionPower(currentCharge);
            fireExplosion(world, user, explosionPower);
            setCharge(stack, 0);
            user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            return TypedActionResult.success(stack);
        }

        // Handle charging logic (only for explosion quirk users)
        if (isExplosionUser) {
            PowerHolderComponent powerComponent = PowerHolderComponent.KEY.get(user);
            PowerType<?> powerType = PowerTypeRegistry.get(NITRO_SWEAT_RESOURCE);

            if (powerType != null && powerComponent.hasPower(powerType)) {
                VariableIntPower nitroSweatPower = (VariableIntPower) powerComponent.getPower(powerType);
                int availableSweat = nitroSweatPower.getValue();

                if (availableSweat > 0) {
                    int newCharge = Math.min(currentCharge + CHARGE_PER_UNIT, MAX_CHARGE);
                    setCharge(stack, newCharge);
                    nitroSweatPower.setValue(availableSweat - CHARGE_PER_UNIT);
                    PowerHolderComponent.syncPower(user, powerType);

                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.BLOCK_SCULK_CHARGE, SoundCategory.PLAYERS, 1.0F, 0.5F);
                    return TypedActionResult.success(stack);
                }
            }
        }

        // Play failure sound if no action was taken
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return TypedActionResult.fail(stack);
    }

    private boolean shouldFire(PlayerEntity user, int currentCharge, boolean isExplosionUser) {
        if (currentCharge <= 0) return false;

        // Non-explosion users can always fire
        if (!isExplosionUser) return true;

        // Explosion users can fire without sneaking when full
        if (currentCharge >= MAX_CHARGE) return true;

        // Explosion users can fire with sneaking when partially charged
        return user.isSneaking();
    }

    private float calculateExplosionPower(int currentCharge) {
        return MAX_EXPLOSION_POWER * (currentCharge / (float) MAX_CHARGE);
    }

    private void fireExplosion(World world, PlayerEntity user, float power) {
        if (!world.isClient()) {
            Vec3d lookVec = user.getRotationVector();
            Vec3d explosionPos = user.getEyePos().add(lookVec.multiply(3));

            // Create explosion with configurable power
            world.createExplosion(
                    user,
                    explosionPos.x, explosionPos.y, explosionPos.z,
                    power,
                    false,
                    World.ExplosionSourceType.NONE
            );

            // Play explosion sounds
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F);
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }
    }

    private boolean hasExplosionQuirk(PlayerEntity player) {
        OriginComponent originComponent = ModComponents.ORIGIN.get(player);
        try {
            OriginLayer quirksLayer = OriginLayers.getLayer(QUIRKS_LAYER_ID);
            if (quirksLayer == null) return false;
            return originComponent.hasOrigin(quirksLayer) &&
                    originComponent.getOrigin(quirksLayer).getIdentifier().equals(EXPLOSION_QUIRK_ID);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private int getCharge(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt() != null && stack.getNbt().contains("Charge")) {
            return stack.getNbt().getInt("Charge");
        }
        return 0;
    }

    private void setCharge(ItemStack stack, int charge) {
        stack.getOrCreateNbt().putInt("Charge", charge);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return Math.round(13.0f * getCharge(stack) / (float) MAX_CHARGE);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        float chargePercent = getCharge(stack) / (float) MAX_CHARGE;
        int r = (int) (255 * (1 - chargePercent));
        int g = (int) (255 * chargePercent);
        return (0xFF << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8);
    }
}