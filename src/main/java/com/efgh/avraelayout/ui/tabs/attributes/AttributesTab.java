package com.efgh.avraelayout.ui.tabs.attributes;

import com.efgh.avraelayout.ui.tabs.Rollable;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

public class AttributesTab extends Tab implements Rollable {
    private AttributePanelGroup attributes = new AttributePanelGroup();
    public AttributesTab() {
        setText("Attributes");

        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Strength.png")), "Strength", "STR");
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Dexterity.png")), "Dexterity", "DEX");
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Constitution.png")), "Constitution", "CON");
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Intelligence.png")), "Intelligence", "INT");
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Wisdom.png")), "Wisdom", "WIS");
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Charisma.png")), "Charisma", "CHA");

        HBox attributesBox = new HBox();
        attributesBox.getStyleClass().add("hbox");
        attributesBox.getChildren().addAll(attributes.getAttributePanels());

        setContent(attributesBox);
    }

    @Override
    public String getRollExpression() {
        return attributes.getRollExpression();
    }
}
