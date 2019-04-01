package com.efgh.avraelayout.ui.tabs.attributes;

import com.efgh.avraelayout.entities.Attribute;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;
import java.util.List;

class AttributePanelGroup {
    private List<AttributePanel> panelGroup = new ArrayList<>();

    void addAttributeButton(Image image, Attribute attribute) {
        AttributePanel attributePanel = new AttributePanel(image, attribute);
        attributePanel.setOnClickAction(e -> {
            panelGroup.forEach(attr -> attr.setSelected(false));
            attributePanel.setSelected(e.getButton() == MouseButton.PRIMARY);
        });
        panelGroup.add(attributePanel);
    }

    String getRollExpression() {
        for (AttributePanel attribute : panelGroup) {
            if (attribute.isSelected()) {
                return attribute.getRollExpression();
            }
        }
        return "";
    }

    List<AttributePanel> getAttributePanels() {
        return panelGroup;
    }
}
