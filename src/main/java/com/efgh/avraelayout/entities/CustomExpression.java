package com.efgh.avraelayout.entities;

public class CustomExpression {
    private String expressionName;
    private String expressionValue;

    public CustomExpression(String expressionName, String expressionValue) {
        this.expressionName = expressionName;
        this.expressionValue = expressionValue;
    }

    public String getExpressionName() {
        return expressionName;
    }

    public void setExpressionName(String expressionName) {
        this.expressionName = expressionName;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }
}
