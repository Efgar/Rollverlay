package com.efgh.avraelayout.ui.tabs.DiceRoller;

import com.efgh.avraelayout.ui.components.ManualModifierTextField;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.ObjectUtils;

class DiePanel extends VBox {
    private Integer dieCount = null;
    private Integer dieValue;
    private ManualModifierTextField textField = new ManualModifierTextField("Dice amount", "");

    DiePanel(Image image, Integer dieValue) {
        this.dieValue = dieValue;
        setAlignment(Pos.TOP_CENTER);
        getChildren().add(createDieButton(image));
        getChildren().add(textField);
    }

    private JFXButton createDieButton(Image image) {
        ImageView imageAux = new ImageView(image);
        imageAux.setFitWidth(42);
        imageAux.setFitHeight(42);

        JFXButton dieButton = new JFXButton();
        dieButton.setGraphic(imageAux);
        dieButton.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                increaseDiceAmount();
            } else {
                decreaseDiceAmount();
            }
        });
        return dieButton;
    }

    private void increaseDiceAmount() {
        dieCount = ObjectUtils.defaultIfNull(dieCount, 0) + 1;
        textField.setText(String.valueOf(ObjectUtils.defaultIfNull(dieCount, "")));
    }

    private void decreaseDiceAmount() {
        dieCount = ObjectUtils.defaultIfNull(dieCount, 0) - 1;
        if (dieCount <= 0) {
            dieCount = null;
        }
        textField.setText(String.valueOf(ObjectUtils.defaultIfNull(dieCount, "")));
    }

    void setDiceAmount(Integer amount) {
        dieCount = amount <= 0 ? null : amount;
        textField.setText(String.valueOf(ObjectUtils.defaultIfNull(dieCount, "")));
    }

    void reset() {
        dieCount = null;
        textField.setText("");
    }

    String getDiceToRollExpression() {
        if (dieCount != null && dieCount >= 0) {
            return dieCount + "d" + dieValue;
        }
        return null;
    }

    String getDiceCount() {
        return String.valueOf(ObjectUtils.defaultIfNull(dieCount, ""));
    }
}
