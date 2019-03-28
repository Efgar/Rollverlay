package com.efgh.avraelayout.ui.tabs.attributes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class AttributePanel extends VBox {
    private String attributeExpression;
    private boolean isSelected = true;
    private JFXToggleButton toggleButton = new JFXToggleButton();
    private JFXButton attributeButton = new JFXButton();

    AttributePanel(Image image, String attributeName, String attributeExpression) {
        this.attributeExpression = attributeExpression;
        setSelected(false);
        getChildren().add(new Text(attributeName));
        getChildren().add(createAttributeButton(image));
        toggleButton.setText("SAVE");
        toggleButton.setSize(5);
    }

    private JFXButton createAttributeButton(Image image) {
        ImageView imageAux = new ImageView(image);
        imageAux.setFitWidth(65);
        imageAux.setFitHeight(65);

        attributeButton.setGraphic(imageAux);
        attributeButton.setOnMouseClicked(e -> setSelected(e.getButton() == MouseButton.PRIMARY));
        return attributeButton;
    }

    void setOnClickAction(EventHandler<ActionEvent> action) {
        attributeButton.setOnAction(action);
    }

    void setSelected(boolean isSelected) {
        if (isSelected) {
            setAlignment(Pos.TOP_CENTER);
            getChildren().add(toggleButton);
            this.isSelected = true;
        } else {
            setAlignment(Pos.CENTER);
            getChildren().remove(toggleButton);
            this.isSelected = false;
        }
    }

    boolean isSelected() {
        return isSelected;
    }

    String getRollExpression(){
        return getRollType() + attributeExpression;
    }

    private String getRollType(){
        if(toggleButton.isSelected()){
            return "!save ";
        }
        return "!check ";
    }
}
