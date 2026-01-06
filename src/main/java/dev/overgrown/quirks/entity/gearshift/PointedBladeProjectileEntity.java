package dev.overgrown.quirks.entity.gearshift;

import dev.overgrown.quirks.entity.registry.ModEntities;
import dev.overgrown.quirks.item.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PointedBladeProjectileEntity extends PersistentProjectileEntity {
    private static final float DAMAGE = 5.0F;
    private boolean canBePickedUp = true;
    private boolean canRicochet = false;
    private int ricochetCount = 0;
    private static final int MAX_RICOCHETS = 3;

    public PointedBladeProjectileEntity(EntityType<? extends PointedBladeProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setDamage(DAMAGE);
        this.pickupType = PickupPermission.ALLOWED;
    }

    public PointedBladeProjectileEntity(World world, double x, double y, double z) {
        this(ModEntities.POINTED_BLADE_PROJECTILE, world);
        this.setPosition(x, y, z);
    }

    public PointedBladeProjectileEntity(World world, LivingEntity owner) {
        this(ModEntities.POINTED_BLADE_PROJECTILE, world);
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    @Override
    public void tick() {
        super.tick();

        // Add trail particles
        if (this.getWorld().isClient && !this.inGround) {
            for (int i = 0; i < 2; i++) {
                this.getWorld().addParticle(ParticleTypes.SMOKE,
                        this.getX() + (this.random.nextDouble() - 0.5) * 0.1,
                        this.getY() + (this.random.nextDouble() - 0.5) * 0.1,
                        this.getZ() + (this.random.nextDouble() - 0.5) * 0.1,
                        0, 0, 0);
            }
        }

        // Despawn after 1 minute if stuck in ground
        if (this.inGround && this.inGroundTime > 1200) {
            this.discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        // Check if we can ricochet
        if (canRicochet && ricochetCount < MAX_RICOCHETS) {
            ricochet(blockHitResult);
            ricochetCount++;
            return;
        }

        super.onBlockHit(blockHitResult);

        // Play hit sound
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.ITEM_TRIDENT_HIT_GROUND, 1.0F, 1.2F);
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        // Check if we can ricochet
        if (canRicochet && ricochetCount < MAX_RICOCHETS) {
            ricochet(entityHitResult);
            ricochetCount++;
            return;
        }

        super.onEntityHit(entityHitResult);

        // Play hit sound
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0F, 1.0F);
        }
    }

    private void ricochet(BlockHitResult blockHitResult) {
        // Calculate reflection direction
        Vec3d velocity = this.getVelocity();
        Vec3d normal = Vec3d.of(blockHitResult.getSide().getVector()).normalize();

        // Reflect velocity: v' = v - 2*(v·n)*n
        double dotProduct = velocity.dotProduct(normal);
        Vec3d reflectedVelocity = velocity.subtract(normal.multiply(2 * dotProduct));

        // Apply the reflected velocity with slight randomness
        double speed = reflectedVelocity.length();
        reflectedVelocity = reflectedVelocity.normalize().multiply(speed * 0.85); // Lose 15% speed per ricochet

        // Add random deviation
        double deviation = 0.1;
        reflectedVelocity = reflectedVelocity.add(
                (this.random.nextDouble() - 0.5) * deviation,
                (this.random.nextDouble() - 0.5) * deviation,
                (this.random.nextDouble() - 0.5) * deviation
        ).normalize().multiply(speed * 0.85);

        this.setVelocity(reflectedVelocity);

        // Reset inGround state
        this.inGround = false;
        this.inGroundTime = 0;

        // Play ricochet sound
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 1.0F, 1.0F + this.random.nextFloat() * 0.2F);
        }

        // Add ricochet particles
        if (this.getWorld().isClient) {
            Vec3d pos = blockHitResult.getPos();
            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(ParticleTypes.ENCHANT,
                        pos.x, pos.y, pos.z,
                        (this.random.nextDouble() - 0.5) * 0.2,
                        (this.random.nextDouble() - 0.5) * 0.2,
                        (this.random.nextDouble() - 0.5) * 0.2);
            }
        }

        // Update rotation to match new velocity
        this.updateRotation();
    }

    private void ricochet(EntityHitResult entityHitResult) {
        // Calculate reflection direction based on entity hit
        Vec3d velocity = this.getVelocity();
        Vec3d entityPos = entityHitResult.getEntity().getPos();
        Vec3d thisPos = this.getPos();

        // Calculate direction from entity to projectile for reflection
        Vec3d toProjectile = thisPos.subtract(entityPos).normalize();

        // Reflect velocity: v' = v - 2*(v·n)*n
        double dotProduct = velocity.dotProduct(toProjectile);
        Vec3d reflectedVelocity = velocity.subtract(toProjectile.multiply(2 * dotProduct));

        // Apply the reflected velocity with slight randomness
        double speed = reflectedVelocity.length();
        reflectedVelocity = reflectedVelocity.normalize().multiply(speed * 0.85); // Lose 15% speed per ricochet

        // Add random deviation
        double deviation = 0.15;
        reflectedVelocity = reflectedVelocity.add(
                (this.random.nextDouble() - 0.5) * deviation,
                (this.random.nextDouble() - 0.5) * deviation,
                (this.random.nextDouble() - 0.5) * deviation
        ).normalize().multiply(speed * 0.85);

        this.setVelocity(reflectedVelocity);

        // Play ricochet sound
        if (!this.getWorld().isClient) {
            this.playSound(SoundEvents.ITEM_TRIDENT_RETURN, 1.0F, 1.0F + this.random.nextFloat() * 0.2F);
        }

        // Add ricochet particles
        if (this.getWorld().isClient) {
            Vec3d pos = entityHitResult.getPos();
            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(ParticleTypes.ENCHANT,
                        pos.x, pos.y, pos.z,
                        (this.random.nextDouble() - 0.5) * 0.2,
                        (this.random.nextDouble() - 0.5) * 0.2,
                        (this.random.nextDouble() - 0.5) * 0.2);
            }
        }

        // Update rotation to match new velocity
        this.updateRotation();
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.canBePickedUp = nbt.getBoolean("CanBePickedUp");

        // Read ricochet data
        if (nbt.contains("CanRicochet")) {
            // Support both numeric (1/0) and boolean values
            if (nbt.get("CanRicochet").getType() == 1) { // Byte type
                this.canRicochet = nbt.getByte("CanRicochet") == 1;
            } else if (nbt.get("CanRicochet").getType() == 3) { // Int type
                this.canRicochet = nbt.getInt("CanRicochet") == 1;
            } else {
                this.canRicochet = nbt.getBoolean("CanRicochet");
            }
        }
        this.ricochetCount = nbt.getInt("RicochetCount");
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("CanBePickedUp", this.canBePickedUp);

        // Write ricochet data
        nbt.putBoolean("CanRicochet", this.canRicochet);
        nbt.putInt("RicochetCount", this.ricochetCount);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.POINTED_BLADE);
    }

    @Override
    public boolean isNoClip() {
        return false;
    }

    @Override
    protected float getDragInWater() {
        return 0.6F;
    }

    // Helper method to set ricochet capability
    public void setCanRicochet(boolean canRicochet) {
        this.canRicochet = canRicochet;
    }

    public boolean canRicochet() {
        return this.canRicochet;
    }

    public int getRicochetCount() {
        return this.ricochetCount;
    }
}