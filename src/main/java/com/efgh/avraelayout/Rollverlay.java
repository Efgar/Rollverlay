package com.efgh.avraelayout;

import com.efgh.avraelayout.persistence.ConfigGateway;
import com.efgh.avraelayout.ui.css.Themes;
import com.efgh.avraelayout.ui.sections.Header;
import com.efgh.avraelayout.ui.tabs.Spells;
import com.efgh.avraelayout.ui.tabs.attacks.AttacksTab;
import com.efgh.avraelayout.ui.tabs.attributes.AttributesTab;
import com.efgh.avraelayout.ui.tabs.custom.CustomExpressionTab;
import com.efgh.avraelayout.ui.tabs.diceroller.DiceRollerTab;
import com.efgh.avraelayout.ui.tabs.skills.SkillsTab;
import com.efgh.avraelayout.utils.LoggerConfigurationHelper;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.apache.commons.lang3.StringUtils;

import static javafx.stage.Screen.getPrimary;

public class Rollverlay extends Application {
    private static JFXTabPane tabPane = new JFXTabPane();
    private static BorderPane APP_CONTAINER = new BorderPane();

    private Themes selectedTheme = Themes.CLASSIC;

    private double xOffset = 0;
    private double yOffset = 0;

    private int SCREEN_WIDTH = 500;
    private int SCREEN_HEIGHT = 250;

    public static void main(String[] args) {
        LoggerConfigurationHelper.initializeLogger();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setParentWindowProperties(APP_CONTAINER, primaryStage);
        loadConfiguration();
        fillApplicationTabs();
        loadTheme();

        APP_CONTAINER.setTop(new Header(primaryStage));
        APP_CONTAINER.setCenter(getContentPane());

        Scene scene = new Scene(APP_CONTAINER, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().addAll(selectedTheme.getCssList());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private StackPane getContentPane() {
        StackPane contentPane = new StackPane();
        contentPane.getChildren().add(tabPane);
        return contentPane;
    }

    private void loadConfiguration() {
        ConfigGateway.initializeConfiguration();
    }

    private void loadTheme() {
        selectedTheme = Themes.getConfiguredTheme();
    }

    private void fillApplicationTabs() {
        tabPane.getTabs().add(new DiceRollerTab());
        tabPane.getTabs().add(new AttributesTab());
        tabPane.getTabs().add(new SkillsTab());
        tabPane.getTabs().add(new CustomExpressionTab());
        tabPane.getTabs().add(new AttacksTab());
        tabPane.getTabs().add(new Spells());
        tabPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    private void setParentWindowProperties(Pane root, Stage primaryStage) {
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
        primaryStage.setX(getPrimary().getVisualBounds().getMinX() + getPrimary().getVisualBounds().getWidth() - SCREEN_WIDTH);
        primaryStage.setY(getPrimary().getVisualBounds().getMinY() + getPrimary().getVisualBounds().getHeight() - SCREEN_HEIGHT);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/AppIcon.ico")));
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