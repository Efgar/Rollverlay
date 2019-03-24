package com.efgh.avraelayout.ui.sections.components;

import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Tooltip;


public class ManualModifierToogleButton extends JFXToggleNode {
    boolean defaultValue;

    public ManualModifierToogleButton(String modifierName, GlyphIcons icon) {
        this(modifierName, icon, false);
    }

    public ManualModifierToogleButton(String modifierName, GlyphIcons icon, boolean defaultValue) {
        setTooltip(new Tooltip("Disadvantage"));
        getStyleClass().add("manual-modifiers");
        setTooltip(new Tooltip(modifierName));
        setIcon(icon);

        this.defaultValue = defaultValue;
    }

    private void setIcon(GlyphIcons icon) {
        final GlyphIcon heartIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(icon)
                .size("55px")
                .build();
        heartIcon.setStyleClass("jfx-toggle-node-icon");
        setGraphic(heartIcon);
    }

    public void reset() {
        setSelected(defaultValue);
    }
}
