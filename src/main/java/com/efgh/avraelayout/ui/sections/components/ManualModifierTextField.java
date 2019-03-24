package com.efgh.avraelayout.ui.sections.components;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import org.apache.commons.lang3.StringUtils;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ManualModifierTextField extends JFXTextField {
    Integer defaultValue;
    Pattern validInteger = Pattern.compile("(-|\\+)?(\\d{0,2})");

    public ManualModifierTextField(String modifierName) {
        this(modifierName, StringUtils.left(modifierName, 3), null);
    }

    public ManualModifierTextField(String modifierName, String modifierPlaceHolder) {
        this(modifierName, modifierPlaceHolder, null);
    }

    public ManualModifierTextField(String modifierName, String modifierPlaceHolder, Integer defaultValue) {
        setTextFormatter();
        getStyleClass().add("manual-modifiers");
        setPromptText(modifierPlaceHolder);
        setTooltip(new Tooltip(modifierName));
        this.defaultValue = defaultValue;
    }

    public void reset() {
        if (defaultValue == null) {
            setText("");
        } else {
            setText(String.valueOf(defaultValue));
        }
    }

    private void setTextFormatter() {
        UnaryOperator<TextFormatter.Change> filter = getFilter();
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        setTextFormatter(textFormatter);
    }

    private UnaryOperator<TextFormatter.Change> getFilter() {
        return change -> {
            String text = change.getControlNewText();
            if (validInteger.matcher(text).matches()) {
                return change;
            }
            return null;
        };
    }

}
