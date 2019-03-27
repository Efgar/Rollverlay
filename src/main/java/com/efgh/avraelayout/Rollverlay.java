package com.efgh.avraelayout;

import com.efgh.avraelayout.ui.css.Themes;
import com.efgh.avraelayout.ui.sections.Header;
import com.efgh.avraelayout.ui.sections.RollingGui;
import com.efgh.avraelayout.ui.tabs.*;
import com.efgh.avraelayout.ui.tabs.DiceRoller.DiceRoller;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.stage.Screen.getPrimary;

public class Rollverlay extends Application {

    public static StackPane DIALOG_PANE = new StackPane();
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
        DIALOG_PANE.getChildren().add(getContentPane());
        border.setTop(new Header(primaryStage));
        border.setCenter(DIALOG_PANE);

        Scene scene = new Scene(border, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().addAll(selectedTheme.getCssList());

        primaryStage.setX(getPrimary().getVisualBounds().getMinX() + getPrimary().getVisualBounds().getWidth() - SCREEN_WIDTH);
        primaryStage.setY(getPrimary().getVisualBounds().getMinY() + getPrimary().getVisualBounds().getHeight() - SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane getContentPane(){
        GridPane contentPane = new GridPane();

        ColumnConstraints column0 = new ColumnConstraints(100,100, Double.MAX_VALUE);
        column0.setHgrow(Priority.ALWAYS);

        RowConstraints row0 = new RowConstraints(100, 100, Double.MAX_VALUE);
        row0.setVgrow(Priority.ALWAYS);

        contentPane.getColumnConstraints().addAll(column0);
        contentPane.getRowConstraints().addAll(row0);

        contentPane.add(getApplicationTabPane(),0,0,2,1);
        contentPane.add(new RollingGui(), 0,1,1,1);

        return contentPane;
    }

    private JFXTabPane getApplicationTabPane(){
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.getTabs().add(new DiceRoller());
        tabPane.getTabs().add(new Attributes());
        tabPane.getTabs().add(new Skills());
        tabPane.getTabs().add(new Attacks());
        tabPane.getTabs().add(new Spells());
        tabPane.getTabs().add(new Custom());
        tabPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return tabPane;
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
}