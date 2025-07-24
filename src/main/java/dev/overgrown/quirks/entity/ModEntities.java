package dev.overgrown.quirks.entity;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.entity.blueflame.VanishingFistEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static final EntityType<VanishingFistEntity> VANISHING_FIST =
            FabricEntityTypeBuilder.<VanishingFistEntity>create(SpawnGroup.MISC, VanishingFistEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(4)
                    .trackedUpdateRate(10)
                    .build();

    public static void registerEntities() {
        Registry.register(Registries.ENTITY_TYPE,
                Quirks.identifier("vanishing_fist"), VANISHING_FIST);
    }
}