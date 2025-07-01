package dev.overgrown.quirks;

import dev.overgrown.quirks.client.sound.ModSounds;
import dev.overgrown.quirks.item.ModItems;
import dev.overgrown.quirks.particle.ModParticles;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quirks implements ModInitializer {
	public static final String MOD_ID = "quirks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Helper method to create identifiers using MOD_ID as namespace
	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Origins: Quirks mod initialized!");

		ModItems.registerItems();
		ModParticles.registerParticles();
		ModSounds.initialize();
	}
}