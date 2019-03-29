package com.efgh.avraelayout.ui.tabs.diceroller;

import com.efgh.avraelayout.ui.components.ShortTextField;

import java.util.regex.Pattern;

class DiceNumberTextField extends ShortTextField {

    DiceNumberTextField(String modifierName, String modifierPlaceHolder) {
        super(modifierName, modifierPlaceHolder);
    }

    public Pattern getValidationPattern() {
        return Pattern.compile("(\\d{0,2})|");
    }
}
