import com.efgh.avraelayout.ui.sections.Header;
import com.efgh.avraelayout.ui.tabs.Attributes;
import com.efgh.avraelayout.ui.tabs.DiceRoller;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloWorld extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane border = new BorderPane();
        border.setTop(new Header());
        setParentWindowProperties(border, primaryStage);

        JFXTabPane tabpane = new JFXTabPane();
        tabpane.getTabs().add(new DiceRoller());
        tabpane.getTabs().add(new Attributes());
        tabpane.getStylesheets().add(getClass().getResource("/css/custom.css").toExternalForm());
        border.setCenter(tabpane);

        Scene scene = new Scene(border ,300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
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

        primaryStage.setTitle("Avrae Layout");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setAlwaysOnTop(true);


    }
}