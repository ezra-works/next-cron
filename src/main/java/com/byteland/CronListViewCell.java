package com.byteland;

import com.byteland.model.Cron;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CronListViewCell extends ListCell<Cron> {

    @FXML
    private AnchorPane cronAnchorPane;

    @FXML
    private JFXTextField cronTextField;

    @FXML
//    private FontAwesomeIconView fxIconCron;
    private MaterialDesignIconView fxIconCron;

    @FXML
    void cronTextFieldAction(ActionEvent event) {
        CronUtils.selectedCron.setName(cronTextField.getText());
        System.out.println("cronTextFieldAction " + CronUtils.selectedCron);
        event.consume();
    }

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(Cron cron, boolean empty) {
//        System.out.println("Cron set " + cron);
        super.updateItem(cron, empty);
//        System.out.println("Cron set2 " + cron);

        if(empty || cron == null) {

            setText(null);
            setGraphic(null);

        } else {

            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/cronListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    System.out.println("cannot load cronListCell.fxml" + e);
                }
            }
            cronTextField.setText(String.valueOf(cron.getName()));
            fxIconCron.setIcon(MaterialDesignIcon.ALARM);

            setText(null);
            setGraphic(cronAnchorPane);
        }
    }
}
