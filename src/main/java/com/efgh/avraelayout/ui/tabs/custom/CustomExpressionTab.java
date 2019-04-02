package com.efgh.avraelayout.ui.tabs.custom;

import com.efgh.avraelayout.entities.CustomExpression;
import com.efgh.avraelayout.persistence.ConfigGateway;
import com.efgh.avraelayout.ui.components.ConfirmationDialog;
import com.efgh.avraelayout.ui.tabs.RollableTab;
import com.efgh.avraelayout.ui.tabs.expresionbuilders.RollingExpressionStrategy;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class CustomExpressionTab extends RollableTab {
    private FlowPane savedExpressionsButtons = new FlowPane();
    private List<CustomExpression> savedExpressions = ConfigGateway.getDiceRolls();

    public CustomExpressionTab() {
        setText("Custom");
        VBox tabContent = new VBox();
        tabContent.setPrefHeight(Double.MAX_VALUE);
        addSavedExpressions(tabContent);
        setContent(tabContent);
    }

    private void addSavedExpressions(VBox tabContent) {
        populateSavedExpressions();

        savedExpressionsButtons.getStyleClass().add("flowPane");
        savedExpressionsButtons.prefWrapLengthProperty().bind(tabContent.widthProperty());

        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("hbox");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(savedExpressionsButtons);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        tabContent.getChildren().add(scrollPane);
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
        savedExpressionBtn.getStyleClass().add("support-actions");
        savedExpressionBtn.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                //TODO
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
        });
        confirmationDialog.show();
    }

    @Override
    protected String getBaseRollExpression() {
        return null;
    }

    @Override
    protected RollingExpressionStrategy getRollingExpressionStrategy() {
        return null;
    }
}
