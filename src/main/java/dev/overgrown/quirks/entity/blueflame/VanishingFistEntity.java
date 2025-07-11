package dev.overgrown.quirks.entity.blueflame;

import dev.overgrown.quirks.entity.ModEntities;
import dev.overgrown.quirks.particle.blueflame.BlueflameParticleEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VanishingFistEntity extends PersistentProjectileEntity {
    private static final float DAMAGE = 8.0F;
    private final ParticleEffect particleEffect = new BlueflameParticleEffect(0.3f);
    private int maxLife = 100; // 5 second lifetime

    public VanishingFistEntity(EntityType<? extends VanishingFistEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
    }

    public VanishingFistEntity(World world, LivingEntity owner) {
        super(ModEntities.VANISHING_FIST, owner, world);
        Vec3d lookVec = owner.getRotationVec(1.0F).multiply(3.0);
        this.setVelocity(lookVec);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();

        // Cancel drag effect applied by superclass (0.99 in air / 0.6 in water)
        if (!this.isRemoved()) {
            float dragFactor = this.isTouchingWater() ? 0.6F : 0.99F;
            Vec3d velocity = this.getVelocity();
            this.setVelocity(velocity.multiply(1.0 / dragFactor));
        }

        // Despawn after timeout
        if (this.age >= maxLife) {
            discard();
            return;
        }

        // Add particles every tick while alive
        if (getWorld().isClient()) {
            Vec3d pos = getPos();
            // More particles with spread
            for (int i = 0; i < 5; i++) {
                double offsetX = random.nextGaussian() * 0.02;
                double offsetY = random.nextGaussian() * 0.02;
                double offsetZ = random.nextGaussian() * 0.02;

                getWorld().addParticle(particleEffect,
                        pos.x, pos.y, pos.z,
                        offsetX, offsetY, offsetZ);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entity == getOwner()) return;

        entity.damage(getDamageSources().magic(), DAMAGE);
        if (entity instanceof LivingEntity) {
            entity.setOnFireFor(5);
        }
        explodeAndRemove();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        explodeAndRemove();
    }

    private void explodeAndRemove() {
        if (!getWorld().isClient()) {
            Vec3d pos = getPos();
            getWorld().createExplosion(
                    this,
                    pos.x, pos.y, pos.z,
                    2.0F,
                    World.ExplosionSourceType.NONE
            );
            discard();
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.AIR);
    }
}