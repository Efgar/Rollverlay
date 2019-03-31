package com.efgh.avraelayout.ui.tabs.skills;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SkillPanel extends VBox {
    private Text skillName = new Text();
    private JFXComboBox<String> attributeOptions = new JFXComboBox<>();
    private static String SELECTED_CLASS = "clickable-pane-selected";
    private boolean isSelected = true;

    public SkillPanel(String skill) {
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
            getChildren().add(attributeOptions);
            this.isSelected = true;
            skillName.setEffect(new DropShadow(5, Color.BLACK));
        } else {
            getStyleClass().remove(SELECTED_CLASS);
            getChildren().remove(attributeOptions);
            this.isSelected = false;
            skillName.setEffect(null);
        }
    }

    boolean isSelected() {
        return isSelected;
    }

    String getRollExpression() {
        return String.join(" ", "!check", skillName.getText(), attributeOptions.getValue());
    }

}
