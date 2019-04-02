package com.efgh.avraelayout;

import com.efgh.avraelayout.persistence.ConfigGateway;
import com.efgh.avraelayout.ui.css.Themes;
import com.efgh.avraelayout.ui.sections.Header;
import com.efgh.avraelayout.ui.sections.footer.RollBar;
import com.efgh.avraelayout.ui.tabs.Attacks;
import com.efgh.avraelayout.ui.tabs.RollableTab;
import com.efgh.avraelayout.ui.tabs.Spells;
import com.efgh.avraelayout.ui.tabs.attributes.AttributesTab;
import com.efgh.avraelayout.ui.tabs.custom.CustomExpressionTab;
import com.efgh.avraelayout.ui.tabs.diceroller.DiceRollerTab;
import com.efgh.avraelayout.ui.tabs.skills.SkillsTab;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static javafx.stage.Screen.getPrimary;

public class Rollverlay extends Application {

    private static JFXTabPane tabPane = new JFXTabPane();
    private static RollBar footerBar = new RollBar();
    private static BorderPane APP_CONTAINER = new BorderPane();

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
        setParentWindowProperties(APP_CONTAINER, primaryStage);

        APP_CONTAINER.setTop(new Header(primaryStage));
        try {
            ConfigGateway.initializeConfiguration();
            selectedTheme = ConfigGateway.getConfiguredTheme();
        } catch (IOException e) {
            showSnackBar("ERROR READING SAVED CONFIGURATION", true);
        }

        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(getContentPane());
        APP_CONTAINER.setCenter(centerPane);

        Scene scene = new Scene(APP_CONTAINER, SCREEN_WIDTH, SCREEN_HEIGHT);
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
        contentPane.add(footerBar, 0, 1, 1, 1);

        return contentPane;
    }

    private void fillApplicationTabs() {
        tabPane.getTabs().add(new DiceRollerTab());
        tabPane.getTabs().add(new AttributesTab());
        tabPane.getTabs().add(new SkillsTab());
        tabPane.getTabs().add(new CustomExpressionTab());
        tabPane.getTabs().add(new Attacks());
        tabPane.getTabs().add(new Spells());
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

    public static String getRollExpression(String selectableModifiers, String manualModifiers) {
        return ((RollableTab) tabPane.getSelectionModel().getSelectedItem()).getRollExpression(selectableModifiers, manualModifiers);
    }

    public static void showSnackBar(String message, boolean isWarning) {
        HBox snackBarContent = new HBox();
        snackBarContent.getStyleClass().add("snackBar");
        if (isWarning) {
            final GlyphIcon heartIcon = GlyphsBuilder.create(FontAwesomeIconView.class)
                    .glyph(FontAwesomeIcon.WARNING)
                    .size("55px")
                    .styleClass("snack-bar")
                    .build();

            snackBarContent.getChildren().add(heartIcon);
        }
        Text snackBarText = new Text(StringUtils.left(message, 50));
        snackBarText.getStyleClass().add("snack-bar");
        snackBarContent.getChildren().add(snackBarText);

        JFXSnackbar snackBar = new JFXSnackbar((Pane) APP_CONTAINER.getTop());
        snackBar.enqueue(new JFXSnackbar.SnackbarEvent(snackBarContent, Duration.seconds(2), null));
    }

    public static StackPane getDialogPane() {
        return (StackPane) APP_CONTAINER.getCenter();
    }
}