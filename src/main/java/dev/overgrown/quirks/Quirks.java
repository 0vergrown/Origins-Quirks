package dev.overgrown.quirks;

import dev.overgrown.quirks.client.sound.ModSounds;
import dev.overgrown.quirks.effect.invisibility.blinded.BlindedStatusEffect;
import dev.overgrown.quirks.entity.ModEntities;
import dev.overgrown.quirks.item.ModItems;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Quirks implements ModInitializer {
	public static final String MOD_ID = "quirks";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}

	public static final StatusEffect BLINDED = registerEffect("blinded", new BlindedStatusEffect());

	private static StatusEffect registerEffect(String name, StatusEffect effect) {
		return Registry.register(Registries.STATUS_EFFECT, identifier(name), effect);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Origins: Quirks mod initialized!");

		ModItems.registerItems();
		ModParticles.registerParticles();
		ModEntities.registerEntities();
		ModSounds.initialize();
	}
}