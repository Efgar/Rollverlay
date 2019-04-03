package com.efgh.avraelayout.ui.tabs;

import com.efgh.avraelayout.ui.sections.footer.RollBar;
import com.efgh.avraelayout.ui.tabs.expresionbuilders.RollingExpressionStrategy;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;

public abstract class RollableWithModsTab extends Tab {
    private RollBar footerBar;

    protected void initialize() {
        footerBar = new RollBar(this);
        GridPane contentPane = new GridPane();

        ColumnConstraints column0 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        column0.setHgrow(Priority.ALWAYS);

        RowConstraints row0 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row0.setVgrow(Priority.ALWAYS);

        contentPane.getColumnConstraints().addAll(column0);
        contentPane.getRowConstraints().addAll(row0);

        contentPane.add(getTabContentPane(), 0, 0, 2, 1);
        contentPane.add(footerBar, 0, 1, 1, 1);
        setContent(contentPane);
    }

    protected abstract Pane getTabContentPane();

    public String getRollExpression(String selectableModifiers, String manualModifiers) {
        return getRollingExpressionStrategy().getRollExpression(getBaseRollExpression(), selectableModifiers, manualModifiers);
    }

    protected abstract String getBaseRollExpression();

    protected abstract RollingExpressionStrategy getRollingExpressionStrategy();
}
