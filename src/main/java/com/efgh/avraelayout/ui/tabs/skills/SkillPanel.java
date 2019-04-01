package com.efgh.avraelayout.ui.tabs.skills;

import com.efgh.avraelayout.entities.Attribute;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class SkillPanel extends VBox {
    private static String SELECTED_CLASS = "clickable-pane-selected";

    private Text skillName = new Text();
    private SkillModifierCombo skillModifierCombo;
    private boolean isSelected = true;

    SkillPanel(String skill, Attribute defaultAttribute) {
        setSkillPaneSize();
        skillModifierCombo = new SkillModifierCombo(defaultAttribute);
        getStyleClass().add("clickable-pane");
        getStyleClass().add("skill-pane");
        skillName.setText(skill);
        skillName.getStyleClass().add("skill_label");
        getChildren().add(skillName);
        setSelected(false);
    }

    private void setSkillPaneSize(){
        setPrefWidth(110);
        setMaxWidth(110);
    }

    void setOnClickAction(EventHandler<? super MouseEvent> action) {
        setOnMouseClicked(action);
    }

    void setSelected(boolean isSelected) {
        if (isSelected) {
            skillModifierCombo.reset();
            getStyleClass().add(SELECTED_CLASS);
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
