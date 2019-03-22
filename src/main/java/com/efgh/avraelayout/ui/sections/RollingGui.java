package com.efgh.avraelayout.ui.sections;

import com.efgh.avraelayout.ui.Modifiers;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Cursor;
import javafx.scene.effect.Glow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class RollingGui extends HBox {

    public RollingGui() {
        getStylesheets().add(getClass().getResource("/css/modifiers.css").toExternalForm());
        getStyleClass().add("vbox");

        FontAwesomeIconView advantageIcon = createModifierIcon(FontAwesomeIcon.THUMBS_UP);
        advantageIcon.setOnMouseClicked(e -> activateButton(advantageIcon, Modifiers.switchAdvantage()));


        FontAwesomeIconView disadvantageIcon = createModifierIcon(FontAwesomeIcon.THUMBS_DOWN);
        disadvantageIcon.setOnMouseClicked(e -> activateButton(disadvantageIcon, Modifiers.switchDisadvantage()));

        getChildren().addAll(advantageIcon, disadvantageIcon);
    }

    private boolean activateButton(FontAwesomeIconView button, Boolean variable){
        if(variable){
            button.setFill(Color.WHITE);
            button.setEffect(new Glow(2.0));
        }else{
            button.setFill(Color.BLACK);
            button.setEffect(null);
        }
        return variable;
    }

    private FontAwesomeIconView createModifierIcon(FontAwesomeIcon icon) {
        FontAwesomeIconView createdIconButton = new FontAwesomeIconView(icon);
        createdIconButton.setCursor(Cursor.HAND);
        return createdIconButton;
    }
}
