package dev.overgrown.quirks.item.fierce_wings;

import dev.overgrown.quirks.item.ModItems;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class FeatherBladeItem extends SwordItem {
    public FeatherBladeItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(ModItems.FIERCE_WINGS_FEATHER) || super.canRepair(stack, ingredient);
    }
}