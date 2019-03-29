package com.efgh.avraelayout.ui.tabs.attributes;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;
import java.util.List;

class AttributePanelGroup {
    private List<AttributePanel> panelGroup = new ArrayList<>();

    void addAttributeButton(Image image, String attributeName, String attributeExpression) {
        AttributePanel attribute = new AttributePanel(image, attributeName, attributeExpression);
        attribute.setOnClickAction(e -> {
            panelGroup.forEach(attr -> attr.setSelected(false));
            attribute.setSelected(e.getButton() == MouseButton.PRIMARY);
        });
        panelGroup.add(attribute);
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
