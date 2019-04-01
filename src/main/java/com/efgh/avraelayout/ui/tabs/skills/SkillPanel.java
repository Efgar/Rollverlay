package com.efgh.avraelayout.ui.tabs.skills;

import com.efgh.avraelayout.entities.Attribute;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SkillPanel extends VBox {
    private Text skillName = new Text();
    private SkillModifierCombo skillModifierCombo;
    private static String SELECTED_CLASS = "clickable-pane-selected";
    private boolean isSelected = true;

    public SkillPanel(String skill, Attribute defaultAttribute) {
        skillModifierCombo = new SkillModifierCombo(defaultAttribute);
        getStyleClass().add("clickable-pane");
        skillName.setText(skill);
        getChildren().add(skillName);
        setSelected(false);
    }

    void setOnClickAction(EventHandler<? super MouseEvent> action) {
        setOnMouseClicked(action);
    }

    void setSelected(boolean isSelected) {
        if (isSelected) {
            getStyleClass().add(SELECTED_CLASS);
            skillModifierCombo.reset();
            getChildren().add(skillModifierCombo);
            this.isSelected = true;
            skillName.setEffect(new DropShadow(5, Color.BLACK));
        } else {
            getStyleClass().remove(SELECTED_CLASS);
            getChildren().remove(skillModifierCombo);
            this.isSelected = false;
            skillName.setEffect(null);
        }
    }

    boolean isSelected() {
        return isSelected;
    }

    String getRollExpression() {
        return String.join(" ", "!check", skillName.getText(), skillModifierCombo.getSelectedValue());
    }

}
