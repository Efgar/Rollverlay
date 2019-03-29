package com.efgh.avraelayout.ui.components;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public abstract class ShortTextField extends JFXTextField {
    private Integer defaultValue;

    public ShortTextField(String modifierName, String modifierPlaceHolder) {
        this(modifierName, modifierPlaceHolder, null);
    }

    private ShortTextField(String modifierName, String modifierPlaceHolder, Integer defaultValue) {
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
            if (getValidationPattern().matcher(text).matches()) {
                return change;
            }
            return null;
        };
    }

    public abstract Pattern getValidationPattern();
}
