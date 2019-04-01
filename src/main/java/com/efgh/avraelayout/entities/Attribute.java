package com.efgh.avraelayout.entities;

public enum Attribute {
    STR("STR", "Strength"),
    DEX("DEX", "Dexterity"),
    CON("CON", "Constitution"),
    WIS("WIS", "Wisdom"),
    INT("INT", "Intellect"),
    CHA("CHA", "Charisma");

    private String key;
    private String value;

    Attribute(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getCode(){
        return key;
    }

    public String getDisplayValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
