package com.efgh.avraelayout.ui.tabs;

import com.efgh.avraelayout.ui.tabs.expresionbuilders.RollingExpressionStrategy;
import javafx.scene.control.Tab;

public abstract class RollableTab extends Tab {
    public String getRollExpression(String selectableModifiers, String manualModifiers) {
        return getRollingExpressionStrategy().getRollExpression(getBaseRollExpression(), selectableModifiers, manualModifiers);
    }

    protected abstract String getBaseRollExpression();

    protected abstract RollingExpressionStrategy getRollingExpressionStrategy();
}
