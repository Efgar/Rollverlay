package com.efgh.avraelayout.ui.tabs.attributes;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class AttributesTab extends Tab {

    List<AttributePane> attributePanes = new ArrayList<>();

    public AttributesTab() {
        setText("Attributes");

        attributePanes.add(new AttributePane(new Image(getClass().getResourceAsStream("/img/die/d20.png")), "Strength"));
        attributePanes.add(new AttributePane(new Image(getClass().getResourceAsStream("/img/die/d20.png")), "Dexterity"));
        attributePanes.add(new AttributePane(new Image(getClass().getResourceAsStream("/img/die/d20.png")), "Constitution"));
        attributePanes.add(new AttributePane(new Image(getClass().getResourceAsStream("/img/die/d20.png")), "Intelligence"));
        attributePanes.add(new AttributePane(new Image(getClass().getResourceAsStream("/img/die/d20.png")), "Wisdom"));
        attributePanes.add(new AttributePane(new Image(getClass().getResourceAsStream("/img/die/d20.png")), "Charisma"));

        HBox attributes = new HBox();
        attributes.getStyleClass().add("hbox");
        attributes.getChildren().addAll(attributePanes);

        setContent(attributes);
    }
}
