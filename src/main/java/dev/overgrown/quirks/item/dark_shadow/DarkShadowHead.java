package dev.overgrown.quirks.item.dark_shadow;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class DarkShadowHead extends Item implements Equipment {
    public DarkShadowHead(Item.Settings settings) {
        super(settings
                .maxCount(1)
                .maxDamage(0) // Makes it unbreakable
        );
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    // Make item truly unbreakable
    @Override
    public boolean isDamageable() {
        return false;
    }
}