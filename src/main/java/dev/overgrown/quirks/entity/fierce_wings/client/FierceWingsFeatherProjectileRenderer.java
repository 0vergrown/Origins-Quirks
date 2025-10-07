package dev.overgrown.quirks.entity.fierce_wings.client;

import dev.overgrown.quirks.Quirks;
import dev.overgrown.quirks.entity.fierce_wings.FierceWingsFeatherProjectileEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class FierceWingsFeatherProjectileRenderer extends ProjectileEntityRenderer<FierceWingsFeatherProjectileEntity> {
    public static final Identifier TEXTURE = Quirks.identifier("textures/entity/fierce_wings/feather.png");

    public FierceWingsFeatherProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(FierceWingsFeatherProjectileEntity entity) {
        return TEXTURE;
    }
}