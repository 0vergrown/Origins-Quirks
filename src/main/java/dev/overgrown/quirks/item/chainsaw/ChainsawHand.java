package dev.overgrown.quirks.item.chainsaw;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TypedActionResult;

import java.util.UUID;

public class ChainsawHand extends SwordItem implements Equipment {

    // Custom tool material for Chainsaw Hand
    public static final ToolMaterial CHAINSAW_HAND_MATERIAL = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 350; // Better than iron (250), less than diamond (1561)
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 6.0F; // Similar to diamond
        }

        @Override
        public float getAttackDamage() {
            return 3.0F; // Base attack damage
        }

        @Override
        public int getMiningLevel() {
            return 2; // Iron level
        }

        @Override
        public int getEnchantability() {
            return 14; // Between iron (14) and diamond (10)
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(Items.IRON_INGOT);
        }
    };

    // Attribute modifiers for damage and speed
    private static final UUID ATTACK_DAMAGE_MODIFIER_ID = UUID.fromString("FA3E1C1C-4180-4865-B01B-BCCE9785ACA3");
    private static final UUID ATTACK_SPEED_MODIFIER_ID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public ChainsawHand(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);

        // Build attribute modifiers similar to a sword
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(
                        ATTACK_DAMAGE_MODIFIER_ID,
                        "Weapon modifier",
                        (double)(attackDamage + toolMaterial.getAttackDamage()),
                        EntityAttributeModifier.Operation.ADDITION
                )
        );
        builder.put(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(
                        ATTACK_SPEED_MODIFIER_ID,
                        "Weapon modifier",
                        (double)attackSpeed,
                        EntityAttributeModifier.Operation.ADDITION
                )
        );
        this.attributeModifiers = builder.build();
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.MAINHAND; // This is a hand-held weapon
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    // Makes it equipable like armor/trinket
    @Override
    public TypedActionResult<ItemStack> equipAndSwap(Item item, net.minecraft.world.World world,
                                                     net.minecraft.entity.player.PlayerEntity user,
                                                     net.minecraft.util.Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = net.minecraft.entity.mob.MobEntity.getPreferredEquipmentSlot(itemStack);
        ItemStack itemStack2 = user.getEquippedStack(equipmentSlot);

        if (!net.minecraft.enchantment.EnchantmentHelper.hasBindingCurse(itemStack2) &&
                !ItemStack.areEqual(itemStack, itemStack2)) {
            if (!world.isClient()) {
                user.incrementStat(net.minecraft.stat.Stats.USED.getOrCreateStat(item));
            }

            ItemStack itemStack3 = itemStack2.isEmpty() ? itemStack : itemStack2.copyAndEmpty();
            ItemStack itemStack4 = itemStack.copyAndEmpty();
            user.equipStack(equipmentSlot, itemStack4);
            return TypedActionResult.success(itemStack3, world.isClient());
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    // Prevent enchantment glint from appearing
    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
}