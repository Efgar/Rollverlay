package com.efgh.avraelayout.ui.tabs.custom;

import com.efgh.avraelayout.Rollverlay;
import com.efgh.avraelayout.entities.CustomExpression;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.efgh.avraelayout.Rollverlay.getDialogPane;

class CustomRollSavePopup extends JFXDialog {
    private JFXTextField rollName = new JFXTextField();
    private JFXTextField rollExpression = new JFXTextField();
    private JFXButton okay = new JFXButton("OK");

    CustomRollSavePopup() {
        super(getDialogPane(), new JFXDialogLayout(), JFXDialog.DialogTransition.CENTER);

        List<JFXButton> actionBtns = new ArrayList<>();

        rollName.setLabelFloat(true);
        rollName.setPromptText("Roll name");

        rollExpression.setLabelFloat(true);
        rollExpression.setPromptText("Roll expression");

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .build());

        rollName.getValidators().add(validator);
        rollName.validate();

        rollExpression.getValidators().add(validator);
        rollExpression.validate();

        rollName.textProperty().addListener((o, oldVal, newVal) -> {
            if (newVal.length() > 20) {
                rollName.setText(StringUtils.left(newVal, 20));
            }
            okay.setDisable(isNotValidInput());
        });

        rollExpression.textProperty().addListener((o, oldVal, newVal) -> {
            if (newVal.length() > 100) {
                rollExpression.setText(StringUtils.left(newVal, 100));
            }
            okay.setDisable(isNotValidInput());
        });

        VBox saveBody = new VBox();
        saveBody.getChildren().add(new Text("Please enter a expression to save and a name for it.\n"));
        saveBody.getChildren().add(rollName);
        saveBody.getChildren().add(new Text(""));
        saveBody.getChildren().add(rollExpression);

        JFXButton cancel = new JFXButton("CANCEL");
        cancel.getStyleClass().add("secondary-action");
        cancel.setButtonType(JFXButton.ButtonType.RAISED);
        cancel.setOnAction(event -> close());
        actionBtns.add(cancel);

        okay.getStyleClass().add("secondary-action");
        okay.setButtonType(JFXButton.ButtonType.RAISED);
        okay.setDisable(true);
        actionBtns.add(okay);

        ((JFXDialogLayout) getContent()).setHeading(new Text("Save custom expression"));
        ((JFXDialogLayout) getContent()).setBody(saveBody);
        ((JFXDialogLayout) getContent()).setActions(actionBtns);
    }

    private boolean isNotValidInput() {
        return !(rollName.validate() && rollExpression.validate());
    }

    void setOnAcceptAction(EventHandler<ActionEvent> action) {
        okay.setOnMousePressed(event -> {
            try {
                ConfigGateway.addCustomRollExpression(new CustomExpression(rollName.getText(), rollExpression.getText()));
                okay.setOnAction(action);
            } catch (IOException e) {
                Rollverlay.showSnackBar("ERROR SAVING CONFIGURATION", true);
            }
            close();
        });
    }
}
