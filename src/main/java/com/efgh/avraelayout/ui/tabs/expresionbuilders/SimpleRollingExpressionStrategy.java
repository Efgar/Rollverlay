package com.efgh.avraelayout.ui.tabs.expresionbuilders;

import org.apache.commons.lang3.StringUtils;

public class SimpleRollingExpressionStrategy implements RollingExpressionStrategy {
    @Override
    public String getRollExpression(String baseRollExpression, String selectableModifiers, String manualModifiers) {
        String rollExpression = baseRollExpression;
        if (StringUtils.isNotEmpty(rollExpression)) {
            rollExpression = "!roll " + rollExpression;
            if (StringUtils.isNotBlank(manualModifiers)) {
                rollExpression += "+(" + manualModifiers + ")";
            }
            if (StringUtils.isNotBlank(selectableModifiers)) {
                rollExpression += " " + selectableModifiers;
            }
            return rollExpression.trim();
        }
        return "";
    }
}
