package com.efgh.avraelayout.ui.tabs.attributes;

import com.efgh.avraelayout.entities.Attribute;
import com.efgh.avraelayout.ui.tabs.RollableWithModsTab;
import com.efgh.avraelayout.ui.tabs.expresionbuilders.AttributesRollingExpressionStrategy;
import com.efgh.avraelayout.ui.tabs.expresionbuilders.RollingExpressionStrategy;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class AttributesTab extends RollableWithModsTab {
    private AttributePanelGroup attributes = new AttributePanelGroup();

    public AttributesTab() {
        setText("Attribute");
        fillAttributeButtons();
        initialize();
    }

    private void fillAttributeButtons() {
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Strength.png")), Attribute.STR);
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Dexterity.png")), Attribute.DEX);
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Constitution.png")), Attribute.CON);
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Intelligence.png")), Attribute.INT);
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Wisdom.png")), Attribute.WIS);
        attributes.addAttributeButton(new Image(getClass().getResourceAsStream("/img/attr/Charisma.png")), Attribute.CHA);
    }

    @Override
    protected Pane getTabContentPane() {
        HBox attributesBox = new HBox();
        attributesBox.getStyleClass().add("hbox");
        attributesBox.getChildren().addAll(attributes.getAttributePanels());
        return attributesBox;
    }

    protected String getBaseRollExpression() {
        return attributes.getRollExpression();
    }

    protected RollingExpressionStrategy getRollingExpressionStrategy() {
        return new AttributesRollingExpressionStrategy();
    }
}
