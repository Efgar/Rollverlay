package com.efgh.avraelayout.ui.tabs.DiceRoller;

import com.efgh.avraelayout.Rollverlay;
import com.efgh.avraelayout.entities.DiceRoll;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

class RollSavePopup {
    private JFXDialog jfxDialog;
    private JFXTextField rollName = new JFXTextField();
    private JFXButton okay;
    private DiceRoll rollToSave;
    private List<DiceRoll> diceRolls;

    RollSavePopup(List<DiceRoll> diceRolls, DiceRoll rollToSave) {
        List<JFXButton> actionBtns = new ArrayList<>();
        this.rollToSave = rollToSave;
        this.diceRolls = diceRolls;

        rollName.setLabelFloat(true);
        rollName.setPromptText("Roll name");

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .build());

        rollName.getValidators().add(validator);
        rollName.validate();
        rollName.textProperty().addListener((o, oldVal, newVal) -> {
            if(rollName.validate()){
                okay.setDisable(false);
            }else{
                okay.setDisable(true);
            }
        });

        VBox saveBody = new VBox();
        saveBody.getChildren().add(new Text(String.format("Please assign a name for the dice roll (%s)\n", rollToSave.getRollExpression())));
        saveBody.getChildren().add(rollName);

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setHeading(new Text("Save dice roll"));
        jfxDialogLayout.setBody(saveBody);
        jfxDialog = new JFXDialog(Rollverlay.DIALOG_PANE, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        JFXButton cancel = new JFXButton("CANCEL");
        cancel.getStyleClass().add("secondary-action");
        cancel.setButtonType(JFXButton.ButtonType.RAISED);
        cancel.setOnAction(event -> jfxDialog.close());
        actionBtns.add(cancel);

        okay = new JFXButton("OK");
        okay.getStyleClass().add("secondary-action");
        okay.setButtonType(JFXButton.ButtonType.RAISED);
        okay.setDisable(true);
        setOnAcceptAction(event -> jfxDialog.close());
        actionBtns.add(okay);

        jfxDialogLayout.setActions(actionBtns);
    }

    void setOnAcceptAction(EventHandler<ActionEvent> action) {
        okay.setOnAction(action);
        okay.setOnMousePressed(event -> {
            rollToSave.setRollName(rollName.getText());
            this.diceRolls.add(rollToSave);
            jfxDialog.close();
        });
    }

    void show() {
        jfxDialog.show();
    }
}
