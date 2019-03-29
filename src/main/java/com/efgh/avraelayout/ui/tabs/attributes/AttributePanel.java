package com.efgh.avraelayout.ui.tabs.attributes;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

class AttributePanel extends VBox {
    private JFXToggleButton toggleButton = new JFXToggleButton();
    Text attributeText = new Text();
    ImageView imageAux;
    private static String SELECTED_CLASS = "clickable-pane-selected";
    private String attributeExpression;
    private boolean isSelected = true;

    AttributePanel(Image image, String attributeName, String attributeExpression) {
        getStyleClass().add("clickable-pane");
        this.attributeExpression = attributeExpression;
        toggleButton.setText("SAVE");
        toggleButton.setSize(5);
        toggleButton.setSelected(true);

        setAlignment(Pos.CENTER);

        attributeText.setText(attributeName);
        attributeText.getStyleClass().add("image-button-label");

        getChildren().add(attributeText);
        getChildren().add(getImageContainer(image));

        setSelected(false);
    }

    private StackPane getImageContainer(Image image) {
        imageAux = new ImageView(image);
        imageAux.setFitWidth(69);
        imageAux.setFitHeight(69);

        StackPane imageContainer = new StackPane();
        imageContainer.getChildren().add(imageAux);
        imageContainer.getStyleClass().add("image-container");

        return imageContainer;
    }

    void setOnClickAction(EventHandler<? super MouseEvent> action) {
        setOnMouseClicked(action);
    }

    void setSelected(boolean isSelected) {
        if (isSelected) {
            getStyleClass().add(SELECTED_CLASS);
            getChildren().add(toggleButton);
            this.isSelected = true;
            attributeText.setEffect(new DropShadow(5, Color.BLACK));
            imageAux.setEffect(new DropShadow(10, Color.BLACK));
        } else {
            getStyleClass().remove(SELECTED_CLASS);
            getChildren().remove(toggleButton);
            this.isSelected = false;
            attributeText.setEffect(null);
            imageAux.setEffect(null);
        }
    }

    boolean isSelected() {
        return isSelected;
    }

    String getRollExpression() {
        return getRollType() + attributeExpression;
    }

    private String getRollType() {
        if (toggleButton.isSelected()) {
            return "!save ";
        }
        return "!check ";
    }
}
