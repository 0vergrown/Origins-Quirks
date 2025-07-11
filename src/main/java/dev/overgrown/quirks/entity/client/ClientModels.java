package dev.overgrown.quirks.entity.client;

import dev.overgrown.quirks.Quirks;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ClientModels {
    public static final EntityModelLayer VANISHING_FIST_LAYER =
            new EntityModelLayer(new Identifier(Quirks.MOD_ID, "vanishing_fist"), "main");
}