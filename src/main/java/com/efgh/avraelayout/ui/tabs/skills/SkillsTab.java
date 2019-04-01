package com.efgh.avraelayout.ui.tabs.skills;

import com.efgh.avraelayout.entities.Attribute;
import com.efgh.avraelayout.ui.tabs.Rollable;
import javafx.geometry.Orientation;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;

public class SkillsTab extends Tab implements Rollable {
    private SkillPanelGroup skills = new SkillPanelGroup();

    public SkillsTab() {
        setText("Skills");

        skills.addSkillButton("Athletics", Attribute.STR);

        skills.addSkillButton("Acrobatics", Attribute.DEX);
        skills.addSkillButton("Sleight of hand", Attribute.DEX);
        skills.addSkillButton("Stealth", Attribute.DEX);

        skills.addSkillButton("Arcana", Attribute.INT);
        skills.addSkillButton("History", Attribute.INT);
        skills.addSkillButton("Investigation", Attribute.INT);
        skills.addSkillButton("Nature", Attribute.INT);
        skills.addSkillButton("Religion", Attribute.INT);

        skills.addSkillButton("Animal Handling", Attribute.WIS);
        skills.addSkillButton("Insight", Attribute.WIS);
        skills.addSkillButton("Medicine", Attribute.WIS);
        skills.addSkillButton("Perception", Attribute.WIS);
        skills.addSkillButton("Survival", Attribute.WIS);

        skills.addSkillButton("Deception", Attribute.CHA);
        skills.addSkillButton("Intimidation", Attribute.CHA);
        skills.addSkillButton("Performance", Attribute.CHA);
        skills.addSkillButton("Persuasion", Attribute.CHA);

        FlowPane attributesBox = new FlowPane();
        attributesBox.getStyleClass().add("attributes-box");
        attributesBox.setOrientation(Orientation.VERTICAL);
        attributesBox.getChildren().addAll(skills.getAttributePanels());

        setContent(attributesBox);
    }

    @Override
    public String getRollExpression() {
        return skills.getRollExpression();
    }
}
