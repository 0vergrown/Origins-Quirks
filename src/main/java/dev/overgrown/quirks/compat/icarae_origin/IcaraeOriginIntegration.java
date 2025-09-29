package dev.overgrown.quirks.compat.icarae_origin;

import dev.overgrown.quirks.Quirks;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class IcaraeOriginIntegration {

    public static final Identifier WINGS_POWER_ID = Quirks.identifier("fierce_wings");

    public static void init() {
        ModContainer mod = FabricLoader.getInstance().getModContainer(Quirks.MOD_ID).orElseThrow();
        MutableText displayName = Text.translatable("datapack.quirks.compat.icarae_origin");
        ResourceManagerHelper.registerBuiltinResourcePack(Quirks.identifier("compat/icarae_origin"), mod, displayName, ResourcePackActivationType.DEFAULT_ENABLED);
    }
}
