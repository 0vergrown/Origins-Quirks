package dev.overgrown.quirks.client.keybind;

import io.github.apace100.apoli.ApoliClient;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class PowerKeybinds {
    private static final String[] KEY_NAMES = {
            "Primary", "Secondary", "Ternary", "Quaternary", "Quinary",
            "Senary", "Septenary", "Octonary", "Nonary", "Denary",
            "Undenary", "Duodenary", "Tredenary", "Quattuordenary", "Quindenary",
            "Sexdenary", "Septendenary", "Octodenary", "Novemdenary", "Vigenary"
    };

    private static final Map<String, KeyBinding> keyBindings = new HashMap<>();

    public static void register() {
        for (int i = 0; i < KEY_NAMES.length; i++) {
            String name = KEY_NAMES[i].toLowerCase();
            String translationKey = "key.quirks." + name;
            KeyBinding keyBinding = new KeyBinding(
                    translationKey,
                    InputUtil.Type.KEYSYM,
                    i == 0 ? GLFW.GLFW_KEY_G : InputUtil.UNKNOWN_KEY.getCode(),
                    "category.quirks"
            );

            KeyBindingHelper.registerKeyBinding(keyBinding);
            keyBindings.put(name, keyBinding);

            // Register with ApoliClient for power activation
            ApoliClient.registerPowerKeybinding(name, keyBinding);
            ApoliClient.registerPowerKeybinding(translationKey, keyBinding);
        }

        // Register "none" as primary key
        ApoliClient.registerPowerKeybinding("none", keyBindings.get("primary"));
    }

    public static KeyBinding getKeyBinding(int index) {
        if (index < 0 || index >= KEY_NAMES.length) {
            return keyBindings.get("primary");
        }
        return keyBindings.get(KEY_NAMES[index].toLowerCase());
    }
}