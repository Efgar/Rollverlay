package com.efgh.avraelayout.ui.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.efgh.avraelayout.Rollverlay.getDialogPane;

public class ConfirmationDialog {
    private JFXDialog jfxDialog;
    private JFXButton okay;

    public ConfirmationDialog(String dialogTitle, String dialogText, boolean hasCancelOption){
        List<JFXButton> actionBtns = new ArrayList<>();

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setHeading(new Text(dialogTitle));
        jfxDialogLayout.setBody(new Text(dialogText));
        jfxDialog = new JFXDialog(getDialogPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        if(hasCancelOption){
            JFXButton cancel = new JFXButton("CANCEL");
            cancel.getStyleClass().add("secondary-action");
            cancel.setButtonType(JFXButton.ButtonType.RAISED);
            cancel.setOnAction(event -> jfxDialog.close());
            actionBtns.add(cancel);
        }

        okay = new JFXButton("OK");
        okay.getStyleClass().add("secondary-action");
        okay.setButtonType(JFXButton.ButtonType.RAISED);
        setOnAcceptAction(event -> jfxDialog.close());
        actionBtns.add(okay);

        jfxDialogLayout.setActions(actionBtns);
    }

    public void setOnAcceptAction(EventHandler<ActionEvent> action) {
        okay.setOnAction(action);
        okay.setOnMouseClicked(event -> jfxDialog.close());
    }

    public void show(){
        jfxDialog.show();
    }
}
