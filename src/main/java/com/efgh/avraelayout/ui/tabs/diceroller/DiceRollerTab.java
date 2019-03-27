package com.efgh.avraelayout.ui.tabs.diceroller;

import com.efgh.avraelayout.entities.DiceRoll;
import com.efgh.avraelayout.ui.components.ConfirmationDialog;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class DiceRollerTab extends Tab {
    private FlowPane savedRollsButtons = new FlowPane();
    private List<DiceRoll> diceRolls = new ArrayList<>();

    private List<DiePanel> dice = new ArrayList<>();
    private String diceToRoll = "";

    private DiePanel d2 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d2.png")), 2);
    private DiePanel d4 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d4.png")), 4);
    private DiePanel d6 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d6.png")), 6);
    private DiePanel d8 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d8.png")), 8);
    private DiePanel d10 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d10.png")), 10);
    private DiePanel d12 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d12.png")), 12);
    private DiePanel d20 = new DiePanel(new Image(getClass().getResourceAsStream("/img/die/d20.png")), 20);

    public DiceRollerTab() {
        setText("Dice Roller");
        VBox tabContent = new VBox();
        tabContent.setPrefHeight(Double.MAX_VALUE);
        tabContent.getStyleClass().add("test2");
        addDiceButtonsPane(tabContent);
        addDiceOptionsPane(tabContent);
        setContent(tabContent);
        getContent().getStyleClass().add("test2");
    }

    private void createDiceButtons() {
        dice.add(d2);
        dice.add(d4);
        dice.add(d6);
        dice.add(d8);
        dice.add(d10);
        dice.add(d12);
        dice.add(d20);
    }

    private void addDiceButtonsPane(VBox tabContent) {
        createDiceButtons();
        HBox diceButtons = new HBox();
        diceButtons.getStyleClass().add("hbox");

        JFXButton resetDiceButton = new JFXButton("Reset");
        resetDiceButton.getStyleClass().add("secondary-action");
        resetDiceButton.setOnMouseClicked(e -> resetDice());
        resetDiceButton.setMaxWidth(Double.MAX_VALUE);

        JFXButton saveRollButton = new JFXButton("Save");
        saveRollButton.getStyleClass().add("secondary-action");
        saveRollButton.setOnMouseClicked(e -> saveDiceRoll());
        saveRollButton.setMaxWidth(Double.MAX_VALUE);

        VBox optionButtons = new VBox();
        optionButtons.getStyleClass().add("vbox");
        optionButtons.getChildren().addAll(resetDiceButton, saveRollButton);

        diceButtons.getChildren().addAll(dice);
        diceButtons.getChildren().add(optionButtons);

        tabContent.getChildren().add(diceButtons);
    }

    private void addDiceOptionsPane(VBox tabContent) {
        populateSavedRolls();

        savedRollsButtons.getStyleClass().add("flowPane");
        savedRollsButtons.prefWrapLengthProperty().bind(tabContent.widthProperty());

        final ScrollPane scrollPane = new ScrollPane();
        scrollPane.getStyleClass().add("hbox");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(savedRollsButtons);

        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        tabContent.getChildren().add(scrollPane);
    }

    private void saveDiceRoll() {
        RollSavePopup savePopup = new RollSavePopup(diceRolls, getRollToSave());
        savePopup.setOnAcceptAction(e -> populateSavedRolls());
        savePopup.show();
    }

    private DiceRoll getRollToSave() {
        return new DiceRoll(null,
                d2.getDiceCount(),
                d4.getDiceCount(),
                d6.getDiceCount(),
                d8.getDiceCount(),
                d10.getDiceCount(),
                d12.getDiceCount(),
                d20.getDiceCount());
    }

    private void populateSavedRolls() {
        savedRollsButtons.getChildren().clear();
        diceRolls.forEach(diceRoll -> {
            JFXButton savedRollBtn = new JFXButton(diceRoll.getRollName());
            savedRollBtn.getStyleClass().add("support-actions");
            savedRollBtn.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    fillDiceFromSaved(diceRoll);
                } else {
                    removeDiceFromSaved(diceRoll);
                }
            });
            savedRollsButtons.getChildren().add(savedRollBtn);
        });
    }

    private void removeDiceFromSaved(DiceRoll diceRoll) {
        ConfirmationDialog confirmationDialog = new ConfirmationDialog(String.format("Delete saved dice roll (%s)", diceRoll.getRollName()), "Are you sure you want to delete this saved dice combination?", true);
        confirmationDialog.setOnAcceptAction(e -> {
            diceRolls.remove(diceRoll);
            populateSavedRolls();
        });
        confirmationDialog.show();
    }

    private void fillDiceFromSaved(DiceRoll diceRoll) {
        d2.setDiceAmount(NumberUtils.toInt(diceRoll.getD2()));
        d4.setDiceAmount(NumberUtils.toInt(diceRoll.getD4()));
        d6.setDiceAmount(NumberUtils.toInt(diceRoll.getD6()));
        d8.setDiceAmount(NumberUtils.toInt(diceRoll.getD8()));
        d10.setDiceAmount(NumberUtils.toInt(diceRoll.getD10()));
        d12.setDiceAmount(NumberUtils.toInt(diceRoll.getD12()));
        d20.setDiceAmount(NumberUtils.toInt(diceRoll.getD20()));
    }

    private void resetDice() {
        dice.forEach(DiePanel::reset);
    }

    public String getDiceToRoll() {
        dice.forEach(e -> {
            String diceAux = e.getDiceToRollExpression();
            if (!StringUtils.isEmpty(diceAux)) {
                diceToRoll = String.join(" + ", diceToRoll, diceAux);
            }
        });
        return diceToRoll;
    }
}