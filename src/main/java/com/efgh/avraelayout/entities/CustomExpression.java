package com.efgh.avraelayout.entities;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class CustomExpression {
    private String expressionName;
    private String expressionValue;

    public CustomExpression(String expressionName, String expressionValue) {
        this.expressionName = expressionName;
        this.expressionValue = expressionValue;
    }

    public CustomExpression(String savedExpression) {
        String splitSavedExpression[] = savedExpression.split(",", -1);
        this.expressionName = splitSavedExpression[0];
        this.expressionValue = splitSavedExpression[1];
    }

    public String getExpressionName() {
        return expressionName;
    }

    public String getExpression() {
        return expressionValue;
    }

    public String getSavedExpressionString() {
        return String.join(",", expressionName, expressionValue);
    }

    public void validate() throws IOException {
        if (StringUtils.isBlank(expressionName) || StringUtils.isBlank(expressionValue)) {
            throw new IOException("Invalid values for the custom roll");
        }
    }
}
