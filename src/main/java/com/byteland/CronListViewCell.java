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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class CronListViewCell extends ListCell<Cron> {

    private static final Logger log = LogManager.getLogger(CronListViewCell.class);

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
        log.info( "cron name renamed as " + CronUtils.selectedCron.getName());
        event.consume();
    }

    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(Cron cron, boolean empty) {

        super.updateItem(cron, empty);

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
                    log.debug("cannot load cronListCell.fxml" + e.getMessage());
                }
            }
            cronTextField.setText(String.valueOf(cron.getName()));
            fxIconCron.setIcon(MaterialDesignIcon.ALARM);

            setText(null);
            setGraphic(cronAnchorPane);
        }
    }
}
