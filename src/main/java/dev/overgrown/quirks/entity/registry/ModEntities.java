package dev.overgrown.quirks.entity.registry;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.entity.blueflame.VanishingFistEntity;
import dev.overgrown.quirks.entity.fierce_wings.FierceWingsFeatherProjectileEntity;
import dev.overgrown.quirks.entity.gearshift.PointedBladeProjectileEntity;
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

    public static final EntityType<FierceWingsFeatherProjectileEntity> FIERCE_WINGS_FEATHER_PROJECTILE =
            FabricEntityTypeBuilder.<FierceWingsFeatherProjectileEntity>create(SpawnGroup.MISC, FierceWingsFeatherProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f))
                    .trackRangeBlocks(4)
                    .trackedUpdateRate(20)
                    .build();

    public static final EntityType<PointedBladeProjectileEntity> POINTED_BLADE_PROJECTILE =
            FabricEntityTypeBuilder.<PointedBladeProjectileEntity>create(SpawnGroup.MISC, PointedBladeProjectileEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.1f)) // Flat/thin like a dagger
                    .trackRangeBlocks(4)
                    .trackedUpdateRate(20)
                    .build();

    public static void registerEntities() {
        Registry.register(Registries.ENTITY_TYPE,
                Quirks.identifier("vanishing_fist"), VANISHING_FIST);

        Registry.register(Registries.ENTITY_TYPE,
                Quirks.identifier("fierce_wings_feather_projectile"), FIERCE_WINGS_FEATHER_PROJECTILE);

        Registry.register(Registries.ENTITY_TYPE,
                Quirks.identifier("pointed_blade_projectile"), POINTED_BLADE_PROJECTILE);
    }
}