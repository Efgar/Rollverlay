package com.efgh.avraelayout.ui.tabs.attributes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AttributePane extends VBox {

    public AttributePane(Image image, String attributeName) {
        setAlignment(Pos.CENTER);
        getChildren().add(new Text(attributeName));
        getChildren().add(createDieButton(image));
        JFXToggleButton toggleButton = new JFXToggleButton();
        toggleButton.setText("SAVE");
        toggleButton.setSize(5);
        getChildren().add(toggleButton);
    }

    private JFXButton createDieButton(Image image) {
        ImageView imageAux = new ImageView(image);
        imageAux.setFitWidth(65);
        imageAux.setFitHeight(65);

        JFXButton dieButton = new JFXButton();
        dieButton.setGraphic(imageAux);
        dieButton.setOnMouseClicked(e -> {
            setAlignment(Pos.TOP_CENTER);
            getChildren().add(new Text("ACAAAA"));
            System.out.println("ACAAAAAA");
        });
        return dieButton;
    }
}
