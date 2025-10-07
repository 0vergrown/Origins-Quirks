package dev.overgrown.quirks.entity.fierce_wings;

import dev.overgrown.quirks.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class FierceWingsFeatherProjectileEntity extends ArrowEntity {
    public FierceWingsFeatherProjectileEntity(EntityType<? extends FierceWingsFeatherProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FierceWingsFeatherProjectileEntity(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public FierceWingsFeatherProjectileEntity(World world, LivingEntity owner) {
        super(world, owner);
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
        }
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(ModItems.FIERCE_WINGS_FEATHER);
    }
}