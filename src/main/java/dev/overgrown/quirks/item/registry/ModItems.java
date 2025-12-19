package dev.overgrown.quirks.item.registry;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.item.chainsaw.ChainsawHand;
import dev.overgrown.quirks.item.chainsaw.ChainsawHead;
import dev.overgrown.quirks.item.dark_shadow.DarkShadowClaw;
import dev.overgrown.quirks.item.dark_shadow.DarkShadowHead;
import dev.overgrown.quirks.item.fierce_wings.FeatherBlade;
import dev.overgrown.quirks.item.grenadier_bracer.GrenadierBracer;
import dev.overgrown.quirks.item.overhaul.PlagueMask;
import dev.overgrown.quirks.item.weather_manipulation.LifeSupportTubes;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class ModItems {

    public static void registerItems() {
        Registry.register(Registries.ITEM, Quirks.identifier("lemilliomet"), LEMILLIOMET);
        Registry.register(Registries.ITEM, Quirks.identifier("lemilliosuit_chestplate"), LEMILLIOSUIT_CHESTPLATE);
        Registry.register(Registries.ITEM, Quirks.identifier("lemilliosuit_leggings"), LEMILLIOSUIT_LEGGINGS);
        Registry.register(Registries.ITEM, Quirks.identifier("lemilliosuit_boots"), LEMILLIOSUIT_BOOTS);
        Registry.register(Registries.ITEM, Quirks.identifier("grenadier_bracer"), GRENADIER_BRACER);
        Registry.register(Registries.ITEM, Quirks.identifier("dark_shadow_head"), DARK_SHADOW_HEAD);
        Registry.register(Registries.ITEM, Quirks.identifier("dark_shadow_claw"), DARK_SHADOW_CLAW);
        Registry.register(Registries.ITEM, Quirks.identifier("holed_boots"), HOLED_BOOTS);
        Registry.register(Registries.ITEM, Quirks.identifier("life_support_tubes"), LIFE_SUPPORT_TUBES);
        Registry.register(Registries.ITEM, Quirks.identifier("fierce_wings_feather"), FIERCE_WINGS_FEATHER);
        Registry.register(Registries.ITEM, Quirks.identifier("feather_blade"), FEATHER_BLADE);
        Registry.register(Registries.ITEM, Quirks.identifier("chainsaw_hand"), CHAINSAW_HAND);
        Registry.register(Registries.ITEM, Quirks.identifier("chainsaw_head"), CHAINSAW_HEAD);
        Registry.register(Registries.ITEM, Quirks.identifier("plague_mask"), PLAGUE_MASK);
    }

    // Feather Blade Item
    public static final Item FEATHER_BLADE = new FeatherBlade(
            FeatherBlade.MATERIAL,
            3, // Attack damage - middle ground between stone (3) and iron (4)
            -2.4F, // Attack speed - same as gold sword for fast attacks
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item GRENADIER_BRACER = new GrenadierBracer(
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item DARK_SHADOW_HEAD = new DarkShadowHead(
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item DARK_SHADOW_CLAW = new DarkShadowClaw(
            ToolMaterials.NETHERITE,
            3,
            -2.4F,
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
    );

    public static final ArmorItem LEMILLIOMET = new ArmorItem(
            ModArmorMaterials.LEMILLIOSUIT,
            ArmorItem.Type.HELMET,
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final ArmorItem LEMILLIOSUIT_CHESTPLATE = new ArmorItem(
            ModArmorMaterials.LEMILLIOSUIT,
            ArmorItem.Type.CHESTPLATE,
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final ArmorItem LEMILLIOSUIT_LEGGINGS = new ArmorItem(
            ModArmorMaterials.LEMILLIOSUIT,
            ArmorItem.Type.LEGGINGS,
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );
    public static final ArmorItem LEMILLIOSUIT_BOOTS = new ArmorItem(
            ModArmorMaterials.LEMILLIOSUIT,
            ArmorItem.Type.BOOTS,
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final ArmorItem HOLED_BOOTS = new ArmorItem(
            ModArmorMaterials.HOLED_BOOTS,
            ArmorItem.Type.BOOTS,
            new Item.Settings()
                    .maxCount(1)
    );

    public static final Item LIFE_SUPPORT_TUBES = new LifeSupportTubes(
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item FIERCE_WINGS_FEATHER = new Item(
            new Item.Settings()
                    .maxCount(64)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item CHAINSAW_HAND = new ChainsawHand(
            ChainsawHand.CHAINSAW_HAND_MATERIAL,
            3, // Attack damage bonus
            -2.4F, // Attack speed
            new Item.Settings()
                    .maxDamage(350)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item CHAINSAW_HEAD = new ChainsawHead(
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
    );

    public static final Item PLAGUE_MASK = new PlagueMask(
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.COMMON)
    );
}