package dev.overgrown.quirks;

import dev.overgrown.quirks.client.sound.ModSounds;
import dev.overgrown.quirks.compat.icarae_origin.IcaraeOriginIntegration;
import dev.overgrown.quirks.compat.trinkets.TrinketsIntegration;
import dev.overgrown.quirks.effect.invisibility.blinded.BlindedStatusEffect;
import dev.overgrown.quirks.entity.ModEntities;
import dev.overgrown.quirks.item.ModItems;
import dev.overgrown.quirks.particle.registry.ModParticles;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.fabricmc.loader.api.FabricLoader;
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

	public static final StatusEffect BLINDED = registerEffect(new BlindedStatusEffect());

    public static final boolean ICARAE_COMPAT_ENABLED = FabricLoader.getInstance().isModLoaded("icarae_origin");

	private static StatusEffect registerEffect(StatusEffect effect) {
		return Registry.register(Registries.STATUS_EFFECT, identifier("blinded"), effect);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Origins: Quirks mod initialized!");

		ModItems.registerItems();
		ModParticles.registerParticles();
		ModEntities.registerEntities();
		ModSounds.initialize();

		registerFallDamageHandler();

		// Load Trinkets integration if available
		if (FabricLoader.getInstance().isModLoaded("trinkets")) {
			TrinketsIntegration.init();
		}

        // Load Icarae Origin integration if available
        if (ICARAE_COMPAT_ENABLED) {
            IcaraeOriginIntegration.init();
        }
	}

	private void registerFallDamageHandler() {
		ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
			if (source.isOf(DamageTypes.FALL)) {
                return !isWearingHoledBoots(entity);
			}
			return true;
		});
	}

	private boolean isWearingHoledBoots(LivingEntity entity) {
		ItemStack boots = entity.getEquippedStack(EquipmentSlot.FEET);
		return boots.isOf(ModItems.HOLED_BOOTS);
	}
}
