package dev.overgrown.quirks.item.chainsaw;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class ChainsawHead extends Item implements Equipment {
    public ChainsawHead(Item.Settings settings) {
        super(settings);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    // Prevent enchantment glint from appearing
    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
}