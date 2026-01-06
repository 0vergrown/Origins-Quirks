package dev.overgrown.quirks.item.gearshift;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class GearshiftGauntlet extends SwordItem {
    public GearshiftGauntlet(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public static final ToolMaterial GEARSHIFT_GAUNTLET_MATERIAL = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 750; // Slightly more than iron
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 5.0F;
        }

        @Override
        public float getAttackDamage() {
            return 0; // Base damage, actual damage comes from SwordItem constructor
        }

        @Override
        public int getMiningLevel() {
            return 2;
        }

        @Override
        public int getEnchantability() {
            return 12;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS);
        }

    };

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Play metallic hit sound
        World world = attacker.getWorld();
        if (!world.isClient) {
            world.playSound(null, target.getBlockPos(), SoundEvents.BLOCK_ANVIL_LAND,
                    SoundCategory.PLAYERS, 0.5F, 1.0F + world.random.nextFloat() * 0.4F);

            // Apply knockback (like a blunt weapon)
            target.takeKnockback(0.5F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }

        // Apply durability damage
        stack.damage(1, attacker, e -> e.sendToolBreakStatus(attacker.getActiveHand()));
        return true;
    }
}