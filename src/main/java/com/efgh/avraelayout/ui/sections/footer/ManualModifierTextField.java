package com.efgh.avraelayout.ui.sections.footer;

import com.efgh.avraelayout.ui.components.ShortTextField;

import java.util.regex.Pattern;

class ManualModifierTextField extends ShortTextField {

    ManualModifierTextField(String modifierName, String modifierPlaceHolder) {
        super(modifierName, modifierPlaceHolder);
    }

    public Pattern getValidationPattern() {
        return Pattern.compile("((-|\\+)(\\d{0,2})|)");
    }
}
