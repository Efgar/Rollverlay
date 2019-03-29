package com.efgh.avraelayout;

import com.efgh.avraelayout.ui.css.Themes;
import com.efgh.avraelayout.ui.sections.Header;
import com.efgh.avraelayout.ui.sections.footer.RollingGui;
import com.efgh.avraelayout.ui.tabs.*;
import com.efgh.avraelayout.ui.tabs.attributes.AttributesTab;
import com.efgh.avraelayout.ui.tabs.diceroller.DiceRollerTab;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.stage.Screen.getPrimary;

public class Rollverlay extends Application {

    private static JFXTabPane tabPane = new JFXTabPane();
    private static StackPane CONTENT_PANE = new StackPane();

    private Themes selectedTheme = Themes.TRADITIONAL;

    private double xOffset = 0;
    private double yOffset = 0;

    private int SCREEN_WIDTH = 500;
    private int SCREEN_HEIGHT = 250;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        setParentWindowProperties(border, primaryStage);

        border.setTop(new Header(primaryStage));

        CONTENT_PANE.getChildren().add(getContentPane());
        border.setCenter(CONTENT_PANE);

        Scene scene = new Scene(border, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().addAll(selectedTheme.getCssList());

        primaryStage.setX(getPrimary().getVisualBounds().getMinX() + getPrimary().getVisualBounds().getWidth() - SCREEN_WIDTH);
        primaryStage.setY(getPrimary().getVisualBounds().getMinY() + getPrimary().getVisualBounds().getHeight() - SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane getContentPane(){
        fillApplicationTabs();
        GridPane contentPane = new GridPane();

        ColumnConstraints column0 = new ColumnConstraints(100,100, Double.MAX_VALUE);
        column0.setHgrow(Priority.ALWAYS);

        RowConstraints row0 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row0.setVgrow(Priority.ALWAYS);

        contentPane.getColumnConstraints().addAll(column0);
        contentPane.getRowConstraints().addAll(row0);

        contentPane.add(tabPane, 0, 0, 2, 1);
        contentPane.add(new RollingGui(), 0,1,1,1);

        return contentPane;
    }

    private void fillApplicationTabs() {
        tabPane.getTabs().add(new DiceRollerTab());
        tabPane.getTabs().add(new AttributesTab());
        tabPane.getTabs().add(new Skills());
        tabPane.getTabs().add(new Attacks());
        tabPane.getTabs().add(new Spells());
        tabPane.getTabs().add(new Custom());
        tabPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    private void setParentWindowProperties(Pane root, Stage primaryStage){
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);
    }

    public static String getRollExpresion() {
        return ((Rollable) tabPane.getSelectionModel().getSelectedItem()).getRollExpression();
    }

    public static StackPane getDialogPane() {
        return CONTENT_PANE;
    }
}