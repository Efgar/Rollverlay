package com.efgh.avraelayout.ui.tabs.skills;

import com.efgh.avraelayout.entities.Attribute;
import com.jfoenix.controls.JFXComboBox;

public class SkillModifierCombo extends JFXComboBox<Attribute> {
    Attribute defaultValue;
    public SkillModifierCombo(Attribute defaultValue){
        this.defaultValue = defaultValue;
        addSkillModifierValue(Attribute.STR);
        addSkillModifierValue(Attribute.DEX);
        addSkillModifierValue(Attribute.CON);
        addSkillModifierValue(Attribute.INT);
        addSkillModifierValue(Attribute.WIS);
        addSkillModifierValue(Attribute.CHA);
        reset();
    }

    private void addSkillModifierValue(Attribute attribute){
        getItems().add(attribute);
    }

    void reset(){
        getSelectionModel().select(defaultValue);
    }

    String getSelectedValue(){
        return getValue().getCode();
    }
}
