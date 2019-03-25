package com.efgh.avraelayout.ui.tabs.DiceRoller;

import com.efgh.avraelayout.utils.ClipboardHelper;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DiceRoller extends Tab {
    private HBox savedRollsButtons = new HBox();
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

    private void fillDiceButtons() {
        dice.add(d2);
        dice.add(d4);
        dice.add(d6);
        dice.add(d8);
        dice.add(d10);
        dice.add(d12);
        dice.add(d20);
    }

    private HBox getDiceButtonsPane(){
        fillDiceButtons();

        HBox diceButtons = new HBox();
        diceButtons.getStyleClass().add("hbox");
        diceButtons.getChildren().addAll(dice);

        return diceButtons;
    }

    private HBox getDiceOptionsPane(){
        JFXButton resetDiceButton = new JFXButton("Reset dice");
        resetDiceButton.getStyleClass().add("secondary-action");
        resetDiceButton.setOnMouseClicked(e -> resetDice());

        JFXButton saveRollButton = new JFXButton("Save roll");
        saveRollButton.getStyleClass().add("secondary-action");
        saveRollButton.setOnMouseClicked(e -> ClipboardHelper.copyTextToClipBoard(""));

        VBox optionButtons = new VBox();
        optionButtons.getStyleClass().add("vbox");
        optionButtons.getChildren().addAll(resetDiceButton, saveRollButton);

        savedRollsButtons.getStyleClass().add("hbox");

        HBox diceOptions = new HBox();
        diceOptions.getStyleClass().add("hbox");

        final Pane spacer = new Pane();
        spacer.setMinSize(5, 1);
        diceOptions.setHgrow(spacer, Priority.ALWAYS);
        diceOptions.getChildren().addAll(savedRollsButtons, spacer, optionButtons);

        return diceOptions;
    }
    private void populateSavedRolls(){
        savedRolls.forEach(savedRoll -> {
            JFXButton savedRollBtn = new JFXButton(savedRoll.rollName);
            savedRollBtn.getStyleClass().add("support-actions");
            savedRollBtn.setOnMouseClicked(e -> fillDiceFromSaved(savedRoll));
            savedRollsButtons.getChildren().add(savedRollBtn);
        });
    }

    private void fillDiceFromSaved(SavedRoll savedRoll){

    }

    private void resetDice() {
        dice.forEach(DieSection::reset);
    }

    public String getDiceToRoll() {
        dice.forEach(e -> {
            String diceAux = e.getDiceToRoll();
            if (!StringUtils.isEmpty(diceAux)) {
                diceToRoll = String.join(" + ", diceToRoll, diceAux);
            }
        });
        return diceToRoll;
    }
}
