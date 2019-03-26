package com.efgh.avraelayout.ui.tabs.DiceRoller;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class DiceRoller extends Tab {
    private FlowPane savedRollsButtons = new FlowPane();
    private List<SavedRoll> savedRolls = new ArrayList<>();

    private List<DieSection> dice = new ArrayList<>();
    private String diceToRoll = "";

    private DieSection d2 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d2.png")), 2);
    private DieSection d4 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d4.png")), 4);
    private DieSection d6 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d6.png")), 6);
    private DieSection d8 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d8.png")), 8);
    private DieSection d10 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d10.png")), 10);
    private DieSection d12 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d12.png")), 12);
    private DieSection d20 = new DieSection(new Image(getClass().getResourceAsStream("/img/die/d20.png")), 20);

    public DiceRoller() {
        setText("Dice Roller");

        VBox tabContent = new VBox();
        tabContent.getChildren().addAll(getDiceButtonsPane(), getDiceOptionsPane());

        setContent(tabContent);
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

    private HBox getDiceButtonsPane(){
        createDiceButtons();
        HBox diceButtons = new HBox();
        diceButtons.getStyleClass().add("hbox");
        diceButtons.getChildren().addAll(dice);

        return diceButtons;
    }

    private HBox getDiceOptionsPane(){
        for (int i = 0; i < 10; i++) {
            savedRolls.add(new SavedRoll("Test,,4,6,8,10,12,20"));
        }
        populateSavedRolls();

        JFXButton resetDiceButton = new JFXButton("Reset dice");
        resetDiceButton.getStyleClass().add("secondary-action");
        resetDiceButton.setOnMouseClicked(e -> resetDice());

        JFXButton saveRollButton = new JFXButton("Save roll");
        saveRollButton.getStyleClass().add("secondary-action");
        saveRollButton.setOnMouseClicked(e -> saveDiceRoll());

        VBox optionButtons = new VBox();
        optionButtons.getStyleClass().add("vbox");
        optionButtons.getChildren().addAll(resetDiceButton, saveRollButton);

        savedRollsButtons.getStyleClass().add("flowPane");

        HBox diceOptions = new HBox();
        diceOptions.getStyleClass().add("hbox");

        final Pane spacer = new Pane();
        spacer.setMinSize(5, 1);
        diceOptions.setHgrow(spacer, Priority.ALWAYS);
        diceOptions.getChildren().addAll(savedRollsButtons, spacer, optionButtons);

        return diceOptions;
    }

    private void saveDiceRoll() {
        String rollName = "savedRoll";
        savedRolls.add(new SavedRoll(rollName,
                d2.getDiceCount(),
                d4.getDiceCount(),
                d6.getDiceCount(),
                d8.getDiceCount(),
                d10.getDiceCount(),
                d12.getDiceCount(),
                d20.getDiceCount()));
        populateSavedRolls();
    }

    private void populateSavedRolls(){
        savedRollsButtons.getChildren().clear();
        savedRolls.forEach(savedRoll -> {
            JFXButton savedRollBtn = new JFXButton(savedRoll.rollName);
            savedRollBtn.getStyleClass().add("support-actions");
            savedRollBtn.setOnMouseClicked(e -> fillDiceFromSaved(savedRoll));
            savedRollsButtons.getChildren().add(savedRollBtn);
        });
    }

    private void fillDiceFromSaved(SavedRoll savedRoll){
        d2.setDiceAmount(NumberUtils.toInt(savedRoll.d2));
        d4.setDiceAmount(NumberUtils.toInt(savedRoll.d4));
        d6.setDiceAmount(NumberUtils.toInt(savedRoll.d6));
        d8.setDiceAmount(NumberUtils.toInt(savedRoll.d8));
        d10.setDiceAmount(NumberUtils.toInt(savedRoll.d10));
        d12.setDiceAmount(NumberUtils.toInt(savedRoll.d12));
        d20.setDiceAmount(NumberUtils.toInt(savedRoll.d20));
    }

    private void resetDice() {
        dice.forEach(DieSection::reset);
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
