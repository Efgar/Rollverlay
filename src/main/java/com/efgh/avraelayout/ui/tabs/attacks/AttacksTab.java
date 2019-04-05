package com.efgh.avraelayout.ui.tabs.attacks;

import com.efgh.avraelayout.entities.CustomExpression;
import com.efgh.avraelayout.persistence.ConfigGateway;
import com.efgh.avraelayout.ui.Utils;
import com.efgh.avraelayout.ui.components.ConfirmationDialog;
import com.efgh.avraelayout.utils.ClipboardHelper;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class AttacksTab extends Tab {
    private FlowPane attacksPannel = new FlowPane();
    private List<CustomExpression> savedAttacks = ConfigGateway.getAttackRollExpressions();

    public AttacksTab() {
        setText("Attacks");

        HBox attackContainer = new HBox();
        attackContainer.getStyleClass().add("hbox");
        attackContainer.setPrefHeight(Double.MAX_VALUE);

        JFXButton saveAttackButton = new JFXButton("Add");
        saveAttackButton.getStyleClass().add("secondary-action");
        saveAttackButton.setOnMouseClicked(e -> createCustomExpression());
        saveAttackButton.setMaxWidth(Double.MAX_VALUE);
        saveAttackButton.setPrefWidth(60);

        VBox optionButtons = new VBox();
        optionButtons.getStyleClass().add("vbox");
        optionButtons.getChildren().addAll(saveAttackButton);

        attackContainer.getChildren().add(getAttacksListPanel());
        attackContainer.getChildren().add(optionButtons);
        setContent(attackContainer);
    }

    private void createCustomExpression() {
        CustomRollSavePopup savePopup = new CustomRollSavePopup();
        savePopup.setOnAcceptAction(e -> populateSavedExpressions());
        savePopup.show();
    }

    private ScrollPane getAttacksListPanel() {
        populateSavedExpressions();
        return Utils.buildBaseScrollablePane(attacksPannel);
    }

    private void populateSavedExpressions() {
        attacksPannel.getChildren().clear();
        savedAttacks.forEach(customExpression -> {
            JFXButton savedRollBtn = createSavedExpressionBtn(customExpression);
            attacksPannel.getChildren().add(savedRollBtn);
        });
    }

    private JFXButton createSavedExpressionBtn(CustomExpression customExpression) {
        JFXButton savedExpressionBtn = new JFXButton(customExpression.getExpressionName());
        savedExpressionBtn.getStyleClass().add("main-action");
        savedExpressionBtn.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                ClipboardHelper.copyTextToClipBoard(customExpression.getExpression());
            } else {
                removeCustomExpressionFromSaved(customExpression, savedExpressionBtn);
            }
        });
        return savedExpressionBtn;
    }

    private void removeCustomExpressionFromSaved(CustomExpression customExpression, JFXButton savedExpressionBtn) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(String.format("Delete saved expression (%s)", customExpression.getExpressionName()), "Are you sure you want to delete this saved expression?", true);
        confirmationDialog.setOnAcceptAction(e -> {
            savedAttacks.remove(customExpression);
            attacksPannel.getChildren().remove(savedExpressionBtn);
            ConfigGateway.updateConfigurationFile();
        });
        confirmationDialog.show();
    }
}
