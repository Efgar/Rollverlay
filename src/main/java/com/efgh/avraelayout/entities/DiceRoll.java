package com.efgh.avraelayout.entities;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class DiceRoll {
    private String rollName;

    private String d2;
    private String d4;
    private String d6;
    private String d8;
    private String d10;
    private String d12;
    private String d20;

    /**
     * Initialize a dice roll given a string with the format "Rollname,d2,d4,d6,d8,d10,d12,d20"
     *
     * @param savedRoll String containing the dice roll to initialize
     */
    public DiceRoll(String savedRoll) {
        String splitSavedRoll[] = savedRoll.split(",", -1);
        this.rollName = splitSavedRoll[0];
        this.d2 = splitSavedRoll[1];
        this.d4 = splitSavedRoll[2];
        this.d6 = splitSavedRoll[3];
        this.d8 = splitSavedRoll[4];
        this.d10 = splitSavedRoll[5];
        this.d12 = splitSavedRoll[6];
        this.d20 = splitSavedRoll[7];
    }

    public DiceRoll(String rollName, String d2, String d4, String d6, String d8, String d10, String d12, String d20){
        this.rollName = rollName;
        this.d2 = d2;
        this.d4 = d4;
        this.d6 = d6;
        this.d8 = d8;
        this.d10 = d10;
        this.d12 = d12;
        this.d20 = d20;
    }

    /**
     * Dice roll in the format: Rollname,d2,d4,d6,d8,d10,d12,d20
     * @return String containing the dice roll
     */
    public String getSavedRollString(){
        return String.join(",", rollName, d2, d4, d6, d8, d10, d12, d20);
    }

    public String getRollExpression(){
        List<String> diceToRoll = new ArrayList<>();
        if(isNotEmpty(getD2())) {
            diceToRoll.add(d2 + "d2");
        }
        if(isNotEmpty(getD4())) {
            diceToRoll.add(d4 + "d4");
        }
        if(isNotEmpty(getD6())) {
            diceToRoll.add(d6 + "d6");
        }
        if(isNotEmpty(getD8())) {
            diceToRoll.add(d8 + "d8");
        }
        if(isNotEmpty(getD10())) {
            diceToRoll.add(d10 + "d10");
        }
        if(isNotEmpty(getD12())) {
            diceToRoll.add(d12 + "d12");
        }
        if(isNotEmpty(getD20())) {
            diceToRoll.add(d20 + "d20");
        }
        return String.join("+", diceToRoll);
    }

    public void setRollName(String rollName){
        this.rollName = rollName;
    }

    public String getRollName() {
        return this.rollName;
    }

    public String getD2() {
        return d2;
    }

    public String getD4() {
        return d4;
    }

    public String getD6() {
        return d6;
    }

    public String getD8() {
        return d8;
    }

    public String getD10() {
        return d10;
    }

    public String getD12() {
        return d12;
    }

    public String getD20() {
        return d20;
    }
}
