package com.efgh.avraelayout.ui.tabs.expresionbuilders;

import org.apache.commons.lang3.StringUtils;

public class AttributesRollingExpressionStrategy implements RollingExpressionStrategy {
    @Override
    public String getRollExpression(String baseRollExpression, String selectableModifiers, String manualModifiers) {
        String rollExpression = baseRollExpression;
        if (StringUtils.isNotBlank(rollExpression)) {
            if (StringUtils.isNotBlank(manualModifiers)) {
                rollExpression += " -b (" + manualModifiers + ")";
            }
            if (StringUtils.isNotBlank(selectableModifiers)) {
                rollExpression += " " + selectableModifiers;
            }
            return rollExpression;
        }
        return "";
    }
}
