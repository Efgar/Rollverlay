package com.efgh.avraelayout.ui.tabs.skills;

import com.efgh.avraelayout.entities.Attribute;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillPanelGroup {
    private List<SkillPanel> panelGroup = new ArrayList<>();

    void addSkillButton(String skillName, Attribute defaultAttribute) {
        SkillPanel skill = new SkillPanel(skillName, defaultAttribute);
        skill.setOnClickAction(e -> {
            panelGroup.forEach(skl -> skl.setSelected(false));
            skill.setSelected(e.getButton() == MouseButton.PRIMARY);
        });
        panelGroup.add(skill);
    }

    void sortSkills() {
        Collections.sort(panelGroup);
    }

    String getRollExpression() {
        for (SkillPanel skill : panelGroup) {
            if (skill.isSelected()) {
                return skill.getRollExpression();
            }
        }
        return "";
    }

    List<SkillPanel> getAttributePanels() {
        return panelGroup;
    }
}
