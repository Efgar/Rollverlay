package com.efgh.avraelayout.ui.tabs.skills;

import com.efgh.avraelayout.entities.Attribute;
import com.efgh.avraelayout.ui.tabs.Rollable;
import javafx.geometry.Orientation;
import javafx.scene.control.Tab;
import javafx.scene.layout.TilePane;

public class SkillsTab extends Tab implements Rollable {
    private SkillPanelGroup skills = new SkillPanelGroup();

    public SkillsTab() {
        setText("Skills");

        skills.addSkillButton("Athletics", Attribute.STR);
        skills.addSkillButton("Acrobatics", Attribute.DEX);

        TilePane  attributesBox = new TilePane();
        attributesBox.getStyleClass().add("attributes-box");
        attributesBox.setPrefRows(5);
        attributesBox.setOrientation(Orientation.VERTICAL);
        attributesBox.getChildren().addAll(skills.getAttributePanels());

        setContent(attributesBox);
    }

    @Override
    public String getRollExpression() {
        return skills.getRollExpression();
    }
}
