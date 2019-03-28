package com.efgh.avraelayout.ui.tabs.attributes;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class AttributePanelGroup {
    private List<AttributePanel> panelGroup = new ArrayList<>();

    void addAttributeButton(Image image, String attributeName, String attributeExpresion) {
        AttributePanel attribute = new AttributePanel(image, attributeName, attributeExpresion);
        attribute.setOnMouseClicked(e-> panelGroup.forEach(attr -> attr.setSelected(false)));
        panelGroup.add(attribute);
    }

    String getRollExpression(){
        for (AttributePanel attribute: panelGroup){
            if(attribute.isSelected()){
                return attribute.getRollExpression();
            }
        }
        throw new IllegalStateException("No attribute selected");
    }

    List<AttributePanel> getAttributePanels() {
        return panelGroup;
    }
}
