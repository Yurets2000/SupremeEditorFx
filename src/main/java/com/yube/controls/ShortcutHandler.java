package com.yube.controls;

import com.yube.configuration.models.settings.ShortcutConfig;
import com.yube.configuration.processors.settings.ShortcutConfigProcessor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShortcutHandler {

    private static class ShortcutConfigMapper {

        public static KeyCodeCombination map(ShortcutConfig shortcutConfig) {
            KeyCode keyCode = KeyCode.getKeyCode(shortcutConfig.getCode());
            List<KeyCodeCombination.Modifier> modifiers = new ArrayList<>();
            String alt = shortcutConfig.getAlt();
            String control = shortcutConfig.getControl();
            String meta = shortcutConfig.getMeta();
            String shift = shortcutConfig.getShift();
            String shortcut = shortcutConfig.getShortcut();
            if (keyCode == null || alt == null || control == null || meta == null || shift == null || shortcut == null)
                throw new IllegalArgumentException();

            if (alt.equals("DOWN")) {
                modifiers.add(KeyCodeCombination.ALT_DOWN);
            }
            if (control.equals("DOWN")) {
                modifiers.add(KeyCodeCombination.CONTROL_DOWN);
            }
            if (meta.equals("DOWN")) {
                modifiers.add(KeyCodeCombination.META_DOWN);
            }
            if (shift.equals("DOWN")) {
                modifiers.add(KeyCodeCombination.SHIFT_DOWN);
            }
            if (shortcut.equals("DOWN")) {
                modifiers.add(KeyCodeCombination.SHORTCUT_DOWN);
            }
            KeyCodeCombination.Modifier[] modifierArr = modifiers.toArray(new KeyCodeCombination.Modifier[modifiers.size()]);
            return new KeyCodeCombination(keyCode, modifierArr);
        }
    }

    private ShortcutConfigProcessor shortcutConfigProcessor;

    public ShortcutHandler(ShortcutConfigProcessor shortcutConfigProcessor) {
        this.shortcutConfigProcessor = shortcutConfigProcessor;
    }

    public KeyCodeCombination getKeyCodeCombination(String action) {
        ShortcutConfig shortcut = shortcutConfigProcessor.getShortcutConfig(action);
        return shortcut == null ? null : ShortcutConfigMapper.map(shortcut);
    }

}
