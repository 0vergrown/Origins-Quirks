package dev.overgrown.quirks.compat.trinkets;

import dev.emi.trinkets.api.*;
import dev.overgrown.quirks.item.registry.ModItems;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Set;

public class TrinketsIntegration {

    public static void init() {
        // Register items as trinkets
        registerTrinket(ModItems.DARK_SHADOW_HEAD);
        registerTrinket(ModItems.DARK_SHADOW_CLAW);

        // Register custom slots
        createSlots();
    }

    private static void registerTrinket(Item item) {
        TrinketsApi.registerTrinket(item, new Trinket() {
            // Implement any custom trinket behavior here
        });
    }

    private static void createSlots() {
        // Create custom head slot
        SlotType darkShadowHead = new SlotType("head", "dark_shadow_head",1, 1,
                new Identifier("quirks", "textures/gui/dark_shadow_head_slot.png"),
                Set.of(new Identifier("trinkets", "all")),  // Quick move predicates
                Set.of(new Identifier("trinkets", "tag")),  // Validator predicates
                Set.of(new Identifier("trinkets", "all")),  // Tooltip predicates
                TrinketEnums.DropRule.DEFAULT
        );

        // Create custom hand slots (both hands)
        SlotType darkShadowClaws = new SlotType("hand", "dark_shadow_claws", 2, 1,
                new Identifier("quirks", "textures/gui/dark_shadow_claws_slot.png"),
                Set.of(new Identifier("trinkets", "all")),
                Set.of(new Identifier("trinkets", "tag")),
                Set.of(new Identifier("trinkets", "all")),
                TrinketEnums.DropRule.DEFAULT
        );

        // Add slots to groups using the SlotGroup.Builder
        SlotGroup.Builder headGroupBuilder = new SlotGroup.Builder("head", 0, 0);
        SlotGroup.Builder handGroupBuilder = new SlotGroup.Builder("hand", 0, 0);

        headGroupBuilder.addSlot("dark_shadow_head", darkShadowHead);
        handGroupBuilder.addSlot("dark_shadow_claws", darkShadowClaws);

        // Build the slot groups
        SlotGroup headGroup = headGroupBuilder.build();
        SlotGroup handGroup = handGroupBuilder.build();
    }
}