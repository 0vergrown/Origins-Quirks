package dev.overgrown.quirks.item.gearshift;

import dev.overgrown.quirks.entity.gearshift.PointedBladeProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class PointedBlade extends Item {
    public PointedBlade(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // Play throw sound
        world.playSound(null, user.getX(), user.getY(), user.getZ(),
                SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS,
                0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            // Create and launch projectile
            PointedBladeProjectileEntity projectile = new PointedBladeProjectileEntity(world, user);
            projectile.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);

            // Check if the item stack has CanRicochet NBT data
            if (itemStack.hasNbt() && itemStack.getNbt().contains("CanRicochet")) {
                NbtCompound nbt = itemStack.getNbt();
                boolean canRicochet = false;

                // Support both numeric (1/0) and boolean values
                if (nbt.get("CanRicochet").getType() == 1) { // Byte type
                    canRicochet = nbt.getByte("CanRicochet") == 1;
                } else if (nbt.get("CanRicochet").getType() == 3) { // Int type
                    canRicochet = nbt.getInt("CanRicochet") == 1;
                } else {
                    canRicochet = nbt.getBoolean("CanRicochet");
                }

                projectile.setCanRicochet(canRicochet);
            }

            // Reduce item count in creative mode
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            world.spawnEntity(projectile);
        }

        // Update usage statistic
        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(itemStack, world.isClient());
    }
}