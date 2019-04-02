package com.efgh.avraelayout.ui.tabs.custom;

import com.efgh.avraelayout.ui.tabs.RollableTab;
import com.efgh.avraelayout.ui.tabs.expresionbuilders.RollingExpressionStrategy;

public class Custom extends RollableTab {

    public Custom() {
        setText("Custom");
    }

    @Override
    protected String getBaseRollExpression() {
        return null;
    }

    @Override
    protected RollingExpressionStrategy getRollingExpressionStrategy() {
        return null;
    }
}
