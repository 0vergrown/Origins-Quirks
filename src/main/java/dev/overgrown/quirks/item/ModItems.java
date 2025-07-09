package dev.overgrown.quirks.item;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.item.grenadier_bracer.GrenadierBracer;
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
    }

    public static final Item GRENADIER_BRACER = new GrenadierBracer(
            new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.UNCOMMON)
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
}