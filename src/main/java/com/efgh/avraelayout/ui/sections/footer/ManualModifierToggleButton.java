package com.efgh.avraelayout.ui.sections.footer;

import com.jfoenix.controls.JFXToggleNode;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphIcons;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Tooltip;


class ManualModifierToggleButton extends JFXToggleNode {
    private boolean startSelected;
    private String modifierExpression;

    ManualModifierToggleButton(String modifierName, String modifierExpression, GlyphIcons icon) {
        this(modifierName, modifierExpression, icon, false);
    }

    ManualModifierToggleButton(String modifierName, String modifierExpression, GlyphIcons icon, boolean startSelected) {
        getStyleClass().add("manual-modifiers");
        setTooltip(new Tooltip(modifierName));
        setIcon(icon);

        this.startSelected = startSelected;
        this.modifierExpression = modifierExpression;
    }

    private void setIcon(GlyphIcons icon) {
        final GlyphIcon heartIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(icon)
                .size("55px")
                .build();
        heartIcon.setStyleClass("jfx-toggle-node-icon");
        setGraphic(heartIcon);
    }

    String getModifierExpression() {
        if (isSelected()) {
            return modifierExpression;
        }
        return "";
    }

    void reset() {
        setSelected(startSelected);
    }
}
