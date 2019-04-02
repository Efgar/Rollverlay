package com.efgh.avraelayout.ui.tabs.expresionbuilders;

public interface RollingExpressionStrategy {
    String getRollExpression(String baseRollExpression, String selectableModifiers, String manualModifiers);
}
