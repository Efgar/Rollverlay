package com.efgh.avraelayout.ui.tabs.DiceRoller;

import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DiceRoller extends Tab {

    private List<DieSection> dice = new ArrayList<>();
    private String diceToRoll = "";

    public DiceRoller() {
        setText("Dice Roller");
        fillDiceButtons();
        HBox diceOptions = new HBox();
        diceOptions.getStyleClass().add("hbox");
        diceOptions.getChildren().addAll(dice);

        setContent(diceOptions);
    }

    private void fillDiceButtons() {
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d2.png")), 2));
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d4.png")), 4));
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d6.png")), 6));
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d8.png")), 8));
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d10.png")), 10));
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d12.png")), 12));
        dice.add(new DieSection(new Image(getClass().getResourceAsStream("/img/die/d20.png")), 20));
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
