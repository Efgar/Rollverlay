package com.efgh.avraelayout.ui.tabs.DiceRoller;

public class SavedRoll {
    String rollName;
    String d2;
    String d4;
    String d6;
    String d8;
    String d10;
    String d12;
    String d20;

    public SavedRoll(String savedRoll){
        String splitSavedRoll[] = savedRoll.split(",");
        this.rollName = splitSavedRoll[0];
        this.d2 = splitSavedRoll[1];
        this.d4 = splitSavedRoll[2];
        this.d6 = splitSavedRoll[3];
        this.d8 = splitSavedRoll[4];
        this.d10 = splitSavedRoll[5];
        this.d12 = splitSavedRoll[6];
        this.d20 = splitSavedRoll[7];
    }

    public SavedRoll(String rollName, String d2, String d4, String d6, String d8, String d10, String d12, String d20){
        this.rollName = rollName;
        this.d2 = d2;
        this.d4 = d4;
        this.d6 = d6;
        this.d8 = d8;
        this.d10 = d10;
        this.d12 = d12;
        this.d20 = d20;
    }

    public String getSavedRollString(){
        return String.join(",", rollName, d2, d4, d6, d8, d10, d12, d20);
    }
}
