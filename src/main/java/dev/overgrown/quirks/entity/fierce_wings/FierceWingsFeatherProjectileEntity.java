package dev.overgrown.quirks.entity.fierce_wings;

import dev.overgrown.quirks.item.registry.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class FierceWingsFeatherProjectileEntity extends ArrowEntity {
    private static final String SLOW_FALLING_TAG = "SlowFalling";
    private static final String CUSTOM_EFFECTS_TAG = "CustomPotionEffects";

    private boolean hasSlowFalling = false;
    private int slowFallingDuration = 100; // 5 seconds default (20 ticks = 1 second)
    private int slowFallingAmplifier = 0;

    public FierceWingsFeatherProjectileEntity(EntityType<? extends FierceWingsFeatherProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FierceWingsFeatherProjectileEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public FierceWingsFeatherProjectileEntity(World world, LivingEntity owner) {
        super(world, owner);
    }

    // Method to enable slow falling effect
    public void setSlowFallingEffect(int duration, int amplifier) {
        this.hasSlowFalling = true;
        this.slowFallingDuration = duration;
        this.slowFallingAmplifier = amplifier;

        // Also add to the arrow's effect system for compatibility
        this.addEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, duration, amplifier));
    }

    @Override
    public void tick() {
        super.tick();

        // Add passive red wool falling dust particles
        if (this.getWorld().isClient && !this.inGround) {
            for (int i = 0; i < 2; i++) {
                double offsetX = (this.random.nextDouble() - 0.5) * 0.1;
                double offsetY = (this.random.nextDouble() - 0.5) * 0.1;
                double offsetZ = (this.random.nextDouble() - 0.5) * 0.1;

                this.getWorld().addParticle(
                        new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, net.minecraft.block.Blocks.RED_WOOL.getDefaultState()),
                        this.getX() + offsetX,
                        this.getY() + offsetY,
                        this.getZ() + offsetZ,
                        0, 0, 0
                );
            }

            // Add slow falling particles if effect is active
            if (this.hasSlowFalling && !this.inGround) {
                for (int i = 0; i < 1; i++) {
                    this.getWorld().addParticle(
                            ParticleTypes.ENTITY_EFFECT,
                            this.getParticleX(0.5),
                            this.getRandomBodyY(),
                            this.getParticleZ(0.5),
                            0.7, 0.9, 1.0  // Light blue color for slow falling
                    );
                }
            }
        }
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);

        // Apply slow falling effect if enabled
        if (this.hasSlowFalling && !this.getWorld().isClient()) {
            Entity owner = this.getEffectCause();

            StatusEffectInstance slowFallingEffect = new StatusEffectInstance(
                    StatusEffects.SLOW_FALLING,
                    this.slowFallingDuration,
                    this.slowFallingAmplifier,
                    false, // ambient
                    true,  // show particles
                    true   // show icon
            );

            target.addStatusEffect(slowFallingEffect, owner);
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);

        // Save custom slow falling data
        NbtCompound slowFallingData = new NbtCompound();
        slowFallingData.putBoolean("Enabled", this.hasSlowFalling);
        slowFallingData.putInt("Duration", this.slowFallingDuration);
        slowFallingData.putInt("Amplifier", this.slowFallingAmplifier);

        nbt.put(SLOW_FALLING_TAG, slowFallingData);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        // Load custom slow falling data
        if (nbt.contains(SLOW_FALLING_TAG)) {
            NbtCompound slowFallingData = nbt.getCompound(SLOW_FALLING_TAG);
            this.hasSlowFalling = slowFallingData.getBoolean("Enabled");
            this.slowFallingDuration = slowFallingData.getInt("Duration");
            this.slowFallingAmplifier = slowFallingData.getInt("Amplifier");

            // Re-add to arrow effect system for consistency
            if (this.hasSlowFalling) {
                this.addEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING,
                        this.slowFallingDuration, this.slowFallingAmplifier));
            }
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.FIERCE_WINGS_FEATHER);
    }

    // Helper method to check if this projectile has slow falling
    public boolean hasSlowFallingEffect() {
        return this.hasSlowFalling;
    }

    // Helper method to get slow falling duration
    public int getSlowFallingDuration() {
        return this.slowFallingDuration;
    }

    // Helper method to get slow falling amplifier
    public int getSlowFallingAmplifier() {
        return this.slowFallingAmplifier;
    }
}