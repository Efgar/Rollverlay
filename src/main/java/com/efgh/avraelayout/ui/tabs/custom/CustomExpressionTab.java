package com.efgh.avraelayout.ui.tabs.custom;

import com.efgh.avraelayout.entities.CustomExpression;
import com.efgh.avraelayout.persistence.ConfigGateway;
import com.efgh.avraelayout.ui.components.ConfirmationDialog;
import com.efgh.avraelayout.utils.ClipboardHelper;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class CustomExpressionTab extends Tab {
    private FlowPane savedExpressionsButtons = new FlowPane();
    private List<CustomExpression> savedExpressions = ConfigGateway.getCustomRollExpressions();

    public CustomExpressionTab() {
        setText("Custom");

        HBox expressionButtons = new HBox();
        expressionButtons.getStyleClass().add("hbox");
        expressionButtons.setPrefHeight(Double.MAX_VALUE);

        JFXButton saveRollButton = new JFXButton("Add");
        saveRollButton.getStyleClass().add("secondary-action");
        saveRollButton.setOnMouseClicked(e -> createCustomExpression());
        saveRollButton.setMaxWidth(Double.MAX_VALUE);
        saveRollButton.setPrefWidth(60);

        VBox optionButtons = new VBox();
        optionButtons.getStyleClass().add("vbox");
        optionButtons.getChildren().addAll(saveRollButton);

        expressionButtons.getChildren().add(getSavedExpressionsPanel());
        expressionButtons.getChildren().add(optionButtons);
        setContent(expressionButtons);
    }

    private void createCustomExpression() {
        CustomRollSavePopup savePopup = new CustomRollSavePopup();
        savePopup.setOnAcceptAction(e -> populateSavedExpressions());
        savePopup.show();
    }

    private ScrollPane getSavedExpressionsPanel() {
        savedExpressionsButtons.getStyleClass().add("flowPane");
        populateSavedExpressions();
        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("hbox");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(savedExpressionsButtons);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        return scrollPane;
    }

    private void populateSavedExpressions() {
        savedExpressionsButtons.getChildren().clear();
        savedExpressions.forEach(customExpression -> {
            JFXButton savedRollBtn = createSavedExpressionBtn(customExpression);
            savedExpressionsButtons.getChildren().add(savedRollBtn);
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
            savedExpressions.remove(customExpression);
            savedExpressionsButtons.getChildren().remove(savedExpressionBtn);
            ConfigGateway.updateConfigurationFile();
        });
        confirmationDialog.show();
    }
}
