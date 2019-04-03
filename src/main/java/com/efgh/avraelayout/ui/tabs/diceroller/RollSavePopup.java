package com.efgh.avraelayout.ui.tabs.diceroller;

import com.efgh.avraelayout.entities.DiceRoll;
import com.efgh.avraelayout.persistence.ConfigGateway;
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
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.efgh.avraelayout.Rollverlay.getDialogPane;

class RollSavePopup extends JFXDialog {
    private JFXTextField rollName = new JFXTextField();
    private JFXButton okay;
    private DiceRoll rollToSave;

    RollSavePopup(DiceRoll rollToSave) {
        super(getDialogPane(), new JFXDialogLayout(), JFXDialog.DialogTransition.CENTER);
        List<JFXButton> actionBtns = new ArrayList<>();
        this.rollToSave = rollToSave;


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
            if (newVal.length() > 20) {
                rollName.setText(StringUtils.left(newVal, 20));
            }
            okay.setDisable(!rollName.validate());
        });

        VBox saveBody = new VBox();
        saveBody.getChildren().add(new Text(String.format("Please assign a name for the dice roll (%s)\n", rollToSave.getRollExpression())));
        saveBody.getChildren().add(rollName);

        JFXButton cancel = new JFXButton("CANCEL");
        cancel.getStyleClass().add("secondary-action");
        cancel.setButtonType(JFXButton.ButtonType.RAISED);
        cancel.setOnAction(event -> close());
        actionBtns.add(cancel);

        okay = new JFXButton("OK");
        okay.getStyleClass().add("secondary-action");
        okay.setButtonType(JFXButton.ButtonType.RAISED);
        okay.setDisable(true);
        actionBtns.add(okay);

        ((JFXDialogLayout) getContent()).setHeading(new Text("Save dice roll"));
        ((JFXDialogLayout) getContent()).setBody(saveBody);
        ((JFXDialogLayout) getContent()).setActions(actionBtns);
    }

    void setOnAcceptAction(EventHandler<ActionEvent> action) {
        okay.setOnAction(action);
        okay.setOnMousePressed(event -> {
            rollToSave.setRollName(rollName.getText());
            ConfigGateway.addDiceRoll(rollToSave);
            close();
        });
    }
}
