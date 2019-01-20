package com.byteland;

import com.byteland.model.*;
import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="rootPane"
    private SplitPane rootPane; // Value injected by FXMLLoader

    @FXML // fx:id="addCronButton"
    private JFXButton addCronButton; // Value injected by FXMLLoader

    @FXML // fx:id="cronListView"
    private JFXListView<Cron> cronListView; // Value injected by FXMLLoader

    @FXML // fx:id="cronStatusButton"
    private JFXToggleButton cronStatusButton; // Value injected by FXMLLoader

    @FXML // fx:id="cronSaveButton"
    private JFXButton cronSaveButton; // Value injected by FXMLLoader

    @FXML // fx:id="cronDeleteButton"
    private JFXButton cronDeleteButton; // Value injected by FXMLLoader

    @FXML // fx:id="stopAllButton"
    private JFXToggleButton stopAllButton; // Value injected by FXMLLoader

    @FXML // fx:id="weekMonthGrid"
    private GridPane weekMonthGrid; // Value injected by FXMLLoader

    @FXML // fx:id="allWeekdayCheckBox"
    private JFXCheckBox allWeekdayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="saturdayCheckBox"
    private JFXCheckBox saturdayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="mondayCheckBox"
    private JFXCheckBox mondayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="tuesdayCheckBox"
    private JFXCheckBox tuesdayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="wednesdayCheckBox"
    private JFXCheckBox wednesdayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="thursdayCheckBox"
    private JFXCheckBox thursdayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="fridayCheckBox"
    private JFXCheckBox fridayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="sundayCheckBox"
    private JFXCheckBox sundayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="allMonthsCheckBox"
    private JFXCheckBox allMonthsCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="januaryCheckBox"
    private JFXCheckBox januaryCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="FebuaryCheckBox"
    private JFXCheckBox FebuaryCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="marchCheckBox"
    private JFXCheckBox marchCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="aprilCheckBox"
    private JFXCheckBox aprilCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="mayCheckBox"
    private JFXCheckBox mayCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="juneCheckBox"
    private JFXCheckBox juneCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="julyCheckBox"
    private JFXCheckBox julyCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="augustCheckBox"
    private JFXCheckBox augustCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="septemberCheckBox"
    private JFXCheckBox septemberCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="octoberCheckBox"
    private JFXCheckBox octoberCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="novemberCheckBox"
    private JFXCheckBox novemberCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="decemberCheckBox"
    private JFXCheckBox decemberCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minutesGrid"
    private GridPane minutesGrid; // Value injected by FXMLLoader

    @FXML // fx:id="minute00CheckBox"
    private JFXCheckBox minute00CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute01CheckBox"
    private JFXCheckBox minute01CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute02CheckBox"
    private JFXCheckBox minute02CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute03CheckBox"
    private JFXCheckBox minute03CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute04CheckBox"
    private JFXCheckBox minute04CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute05CheckBox"
    private JFXCheckBox minute05CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute06CheckBox"
    private JFXCheckBox minute06CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute07CheckBox"
    private JFXCheckBox minute07CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute08CheckBox"
    private JFXCheckBox minute08CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute09CheckBox"
    private JFXCheckBox minute09CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute10CheckBox"
    private JFXCheckBox minute10CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute11CheckBox"
    private JFXCheckBox minute11CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute12CheckBox"
    private JFXCheckBox minute12CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute13CheckBox"
    private JFXCheckBox minute13CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute14CheckBox"
    private JFXCheckBox minute14CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute15CheckBox"
    private JFXCheckBox minute15CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute16CheckBox"
    private JFXCheckBox minute16CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute17CheckBox"
    private JFXCheckBox minute17CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute18CheckBox"
    private JFXCheckBox minute18CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute19CheckBox"
    private JFXCheckBox minute19CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute20CheckBox"
    private JFXCheckBox minute20CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute21CheckBox"
    private JFXCheckBox minute21CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute22CheckBox"
    private JFXCheckBox minute22CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute23CheckBox"
    private JFXCheckBox minute23CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute24CheckBox"
    private JFXCheckBox minute24CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute25CheckBox"
    private JFXCheckBox minute25CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute26CheckBox"
    private JFXCheckBox minute26CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute27CheckBox"
    private JFXCheckBox minute27CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute28CheckBox"
    private JFXCheckBox minute28CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute29CheckBox"
    private JFXCheckBox minute29CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute30CheckBox"
    private JFXCheckBox minute30CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute31CheckBox"
    private JFXCheckBox minute31CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute32CheckBox"
    private JFXCheckBox minute32CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute33CheckBox"
    private JFXCheckBox minute33CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute34CheckBox"
    private JFXCheckBox minute34CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute35CheckBox"
    private JFXCheckBox minute35CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute36CheckBox"
    private JFXCheckBox minute36CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute37CheckBox"
    private JFXCheckBox minute37CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute38CheckBox"
    private JFXCheckBox minute38CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute39CheckBox"
    private JFXCheckBox minute39CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute40CheckBox"
    private JFXCheckBox minute40CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute41CheckBox"
    private JFXCheckBox minute41CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute42CheckBox"
    private JFXCheckBox minute42CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute43CheckBox"
    private JFXCheckBox minute43CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute44CheckBox"
    private JFXCheckBox minute44CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute45CheckBox"
    private JFXCheckBox minute45CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute46CheckBox"
    private JFXCheckBox minute46CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute47CheckBox"
    private JFXCheckBox minute47CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute48CheckBox"
    private JFXCheckBox minute48CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute49CheckBox"
    private JFXCheckBox minute49CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute50CheckBox"
    private JFXCheckBox minute50CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute51CheckBox"
    private JFXCheckBox minute51CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute52CheckBox"
    private JFXCheckBox minute52CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute53CheckBox"
    private JFXCheckBox minute53CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute54CheckBox"
    private JFXCheckBox minute54CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute55CheckBox"
    private JFXCheckBox minute55CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute56CheckBox"
    private JFXCheckBox minute56CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute57CheckBox"
    private JFXCheckBox minute57CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute58CheckBox"
    private JFXCheckBox minute58CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="minute59CheckBox"
    private JFXCheckBox minute59CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="everyMinuteComboBox"
    private ComboBox<Integer> everyMinuteComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="hoursGrid"
    private GridPane hoursGrid; // Value injected by FXMLLoader

    @FXML // fx:id="hour00CheckBox"
    private JFXCheckBox hour00CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour01CheckBox"
    private JFXCheckBox hour01CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour02CheckBox"
    private JFXCheckBox hour02CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour03CheckBox"
    private JFXCheckBox hour03CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour04CheckBox"
    private JFXCheckBox hour04CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour05CheckBox"
    private JFXCheckBox hour05CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour06CheckBox"
    private JFXCheckBox hour06CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour07CheckBox"
    private JFXCheckBox hour07CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour08CheckBox"
    private JFXCheckBox hour08CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour09CheckBox"
    private JFXCheckBox hour09CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour10CheckBox"
    private JFXCheckBox hour10CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour11CheckBox"
    private JFXCheckBox hour11CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour12CheckBox"
    private JFXCheckBox hour12CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour13CheckBox"
    private JFXCheckBox hour13CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour14CheckBox"
    private JFXCheckBox hour14CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour15CheckBox"
    private JFXCheckBox hour15CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour16CheckBox"
    private JFXCheckBox hour16CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour17CheckBox"
    private JFXCheckBox hour17CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour18CheckBox"
    private JFXCheckBox hour18CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour19CheckBox"
    private JFXCheckBox hour19CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour20CheckBox"
    private JFXCheckBox hour20CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour21CheckBox"
    private JFXCheckBox hour21CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour22CheckBox"
    private JFXCheckBox hour22CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour23CheckBox"
    private JFXCheckBox hour23CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="hour24CheckBox"
    private JFXCheckBox hour24CheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="everyHourComboBox"
    private ComboBox<Integer> everyHourComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="cronStartDate"
    private JFXDatePicker cronStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="cronEndDate"
    private JFXDatePicker cronEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="cronStartTime"
    private JFXTimePicker cronStartTime; // Value injected by FXMLLoader

    @FXML // fx:id="cronEndTime"
    private JFXTimePicker cronEndTime; // Value injected by FXMLLoader

    @FXML // fx:id="addScriptButton"
    private JFXButton addScriptButton; // Value injected by FXMLLoader

    @FXML // fx:id="scriptNameText"
    private Text scriptNameText; // Value injected by FXMLLoader

    @FXML // fx:id="argumentComboBox"
    private ComboBox<String> argumentComboBox; // Value injected by FXMLLoader

    @FXML // fx:id="argumentDeleteButton"
    private JFXButton argumentDeleteButton; // Value injected by FXMLLoader

    @FXML // fx:id="timezoneComboBox"
    private ComboBox<String> timezoneComboBox; // Value injected by FXMLLoader

    // Load all the crons from db
    private CronLists cronLists = new CronLists();
    private Random random = new Random();
    private CronUtils cronUtils = new CronUtils();
    private ObservableList<String> zoneLists = cronUtils.getTimeZoneList();
    private ObservableBooleanValue cStatus = new SimpleBooleanProperty();

    @FXML
    void addCronButtonAction(ActionEvent event) {
        System.out.println("random " + random.ints(500, 5000)
                .limit(1).findFirst().getAsInt());
        Cron addCron = cronUtils.getDefaultCron().getCron();
        addCron.setName("NewCron"
                        + random.ints(500, 5000)
                        .limit(1).findFirst().getAsInt());
        cronLists.cronObservableList.sort(Comparator.comparingInt(Cron::getId));
        int lastIndex = cronLists.cronObservableList.size() -1;
        int lastIndexCronId = 0;
        if(lastIndex != -1)
            lastIndexCronId = cronLists.cronObservableList.get(lastIndex).getId();
        System.out.println("lastIndexCronId " + lastIndexCronId);
        addCron.setId(lastIndexCronId + 1);
        cronLists.cronObservableList.add(0, addCron);
        cronListView.setItems(cronLists.cronObservableList);
        cronListView.getSelectionModel().selectFirst();

        timezoneComboBox.setItems(zoneLists);
        timezoneComboBox.getSelectionModel().select("Australia/Sydney (UTC+11:00)");


        event.consume();
    }

    @FXML
    void cronStatusButtonAction(ActionEvent event) {
        if(cronStatusButton.isSelected())
            stopAllButton.setSelected(false);
    }

    @FXML
    void cronSaveAction(ActionEvent event) {
        if(! cronStatusButton.isSelected()) {
            System.out.println("disabled");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cron is not enabled");
            alert.showAndWait();
        } else {
            GridUtils gridUtils = new GridUtils();
            Hours hours = new Hours();
            Minutes minutes = new Minutes();
            Weekdays weekdays = new Weekdays();
            Months months = new Months();

            List<Integer> selectedList = gridUtils.getSelectedHoursMinutes(hoursGrid, "hour");
            hours.setOn(selectedList);
            CronUtils.selectedCron.setHours(hours);

            selectedList = gridUtils.getSelectedHoursMinutes(minutesGrid, "minute");
            minutes.setOn(selectedList);
            CronUtils.selectedCron.setMinutes(minutes);

            List<String> selectedListStg = gridUtils.getSelectedWeeksMonths(weekMonthGrid, "week");
            weekdays.setOn(selectedListStg);
            CronUtils.selectedCron.setWeekdays(weekdays);

            selectedListStg = gridUtils.getSelectedWeeksMonths(weekMonthGrid, "month");
            months.setOn(selectedListStg);
            CronUtils.selectedCron.setMonths(months);

            //save cron's status
            CronUtils.selectedCron.setStatus(cronStatusButton.isSelected() ? CronConstants.CRON_ENABLED
                    : CronConstants.CRON_DISABLED);

            //save cron's script
            CronUtils.selectedCron.setScript(scriptNameText.getText());
            cronUtils.tidyArgumentList(argumentComboBox);
            CronUtils.selectedCron.setParameter(argumentComboBox.getItems());

            if ((cronStartDate.getValue() != null))
                CronUtils.selectedCron.setStartDate(cronStartDate.getValue().toString());
            else CronUtils.selectedCron.setStartDate("");
            if(cronEndDate.getValue() !=null)
                CronUtils.selectedCron.setEndDate(cronEndDate.getValue().toString());
            else CronUtils.selectedCron.setEndDate("");
            if(cronStartTime.getValue() !=null)
                CronUtils.selectedCron.setStartTime(cronStartTime.getValue().toString());
            else CronUtils.selectedCron.setStartTime("");
            if(cronEndTime.getValue() !=null)
                CronUtils.selectedCron.setEndTime(cronEndTime.getValue().toString());
            else CronUtils.selectedCron.setEndTime("");
            //save cron's timezone
            CronUtils.selectedCron.setTimeZone(timezoneComboBox.getSelectionModel().getSelectedItem());

            System.out.println("onSave " + CronUtils.selectedCron);
            cronUtils.writeCronToDB(CronUtils.selectedCron);

            Notifications.create()
//                    .title("New Cron ")
                    .text(CronUtils.selectedCron.getName() + " saved successfully ..")
                    .position(Pos.CENTER)
                    .darkStyle()
                    .showInformation();
        }
        event.consume();
    }

    @FXML
    void cronDeleteAction(ActionEvent event) {
        cronDeleteButton.setDisable(true);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Delete Cron");
        alert.setHeaderText("Deleting " + CronUtils.selectedCron.getName() + " ... ");
        alert.setContentText("Sure.. you won't regret this ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            // ... user chose OK
            System.out.println(result.get());
            cronDeleteButton.setDisable(false);

            cronUtils.deleteCronFromDB(CronUtils.selectedCron);
            cronLists.cronObservableList.remove(CronUtils.selectedCron);
            cronListView.setItems(cronLists.cronObservableList);

        } else {
            // ... user chose CANCEL or closed the dialog
            System.out.println(result.get());
            cronDeleteButton.setDisable(false);
        }
        event.consume();
    }

    @FXML
    void stopAllButtonAction(ActionEvent event) {
        if(stopAllButton.isSelected()) {
            cronListView.getItems().forEach(cron -> {
                cron.setStatus(CronConstants.CRON_DISABLED);
                cronUtils.writeCronToDB(cron);
                cronStatusButton.setSelected(false);
            });
            Notifications.create()
//                    .title("New Cron ")
                    .text("All crons disabled and saved successfully ..")
                    .position(Pos.CENTER)
                    .darkStyle()
                    .showInformation();
        }
    }

    @FXML
    void allMonthsCheckBoxAction(ActionEvent event) {
        if(allMonthsCheckBox.isSelected()) {
            weekMonthGrid.getChildren()
                    .filtered(node -> node instanceof JFXCheckBox)
                    .filtered(node -> node.getAccessibleText() != null)
                    .filtered(node -> node.getAccessibleText().contains("month"))
                    .forEach(node -> ((JFXCheckBox)node).setSelected(true));
        } else {
            weekMonthGrid.getChildren()
                    .filtered(node -> node instanceof JFXCheckBox)
                    .filtered(node -> node.getAccessibleText() != null)
                    .filtered(node -> node.getAccessibleText().contains("month"))
                    .forEach(node -> ((JFXCheckBox)node).setSelected(false));
        }
        event.consume();
    }

    @FXML
    void allWeekdayCheckBoxAction(ActionEvent event) {
        if (allWeekdayCheckBox.isSelected()) {
            weekMonthGrid.getChildren()
                    .filtered(node -> node instanceof JFXCheckBox)
                    .filtered(node -> node.getAccessibleText() != null)
                    .filtered(node -> node.getAccessibleText().contains("week"))
                    .forEach(node -> ((JFXCheckBox) node).setSelected(true));
        } else {
            weekMonthGrid.getChildren()
                    .filtered(node -> node instanceof JFXCheckBox)
                    .filtered(node -> node.getAccessibleText() != null)
                    .filtered(node -> node.getAccessibleText().contains("week"))
                    .forEach(node -> ((JFXCheckBox) node).setSelected(false));
        }
        event.consume();
    }

    @FXML
    void everyMinuteComboBoxAction(ActionEvent event) {
        try {
            int minSelected = everyMinuteComboBox.getSelectionModel().getSelectedItem();

            minutesGrid.getChildren()
                    .filtered(node -> node instanceof JFXCheckBox)
                    .filtered(node -> node.getId().contains("minute"))
                    .forEach(node -> {
                        ((JFXCheckBox)node).setSelected(false);
                        JFXCheckBox jfxCheckBox = ((JFXCheckBox)node);
                        int minute = Integer.parseInt(jfxCheckBox.getText());
                        boolean isDivisible = minute % minSelected == 0;
                        if(isDivisible)
                            jfxCheckBox.setSelected(true);
                    });
        } catch (Exception e) {
            System.out.println("ignoring second action event");
        }
        event.consume();
    }

    @FXML
    void everyHourComboBoxAction(ActionEvent event) {
        try {
            int hourSelected = everyHourComboBox.getSelectionModel().getSelectedItem();

            hoursGrid.getChildren()
                    .filtered(node -> node instanceof JFXCheckBox)
                    .filtered(node -> node.getId().contains("hour"))
                    .forEach(node -> {
                        ((JFXCheckBox)node).setSelected(false);
                        JFXCheckBox jfxCheckBox = ((JFXCheckBox)node);
                        int minute = Integer.parseInt(jfxCheckBox.getText());
                        boolean isDivisible = minute % hourSelected == 0;
                        if(isDivisible)
                            jfxCheckBox.setSelected(true);
                    });
        } catch (Exception e) {
            System.out.println("ignoring second action event");
        }
        event.consume();
    }

    @FXML
    void addScriptButtonAction(ActionEvent event) {

        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File("/Users/ezmoses/Downloads")
        );
        List<String> powershellList = new ArrayList<>();
        powershellList.add("*.ps1");
        powershellList.add("*..psm1");
        List<String> batchList = new ArrayList<>();
        batchList.add("*.bat");
        batchList.add("*.cmd");
        batchList.add("*.btm");

        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("powershell", powershellList),
                new FileChooser.ExtensionFilter("batch", batchList),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setSelectedExtensionFilter(fileChooser.getExtensionFilters().get(2));
        final File scriptFile = fileChooser.showOpenDialog(rootPane.getScene().getWindow());
        try {
            scriptNameText.setText(scriptFile.getPath());
            //Creating a fade transition
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), scriptNameText);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(2.0);
            fadeTransition.play();
            scriptNameText.setVisible(true);

            fadeTransition = new FadeTransition(Duration.millis(2000), argumentComboBox);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(2.0);
            fadeTransition.play();
            argumentComboBox.setVisible(true);

        } catch (NullPointerException npe) {
            System.out.println(" ba ba boo ba");
        }

        event.consume();
    }

    @FXML
    void argumentComboBoxAction(ActionEvent event) {
        try {
            String selectedItem = argumentComboBox.getSelectionModel().getSelectedItem();
            if(! argumentComboBox.getItems().contains(selectedItem) && ! selectedItem.toLowerCase().equals("null")) {
                argumentComboBox.getItems().add(selectedItem);
                Notifications.create()
                        .title("Parameter ")
                        .text(selectedItem + " parameter added")
                        .position(Pos.CENTER)
                        .showInformation();
            }


        } catch (NullPointerException npe) {
            System.out.println("npe");
        }
        event.consume();
    }

    @FXML
    void argumentDeleteButtonAction(ActionEvent event) {
        String selectedItem = argumentComboBox.getSelectionModel().getSelectedItem();
        argumentComboBox.getItems().remove(selectedItem);
        cronUtils.tidyArgumentList(argumentComboBox);
        Notifications.create()
                .title("Parameter ")
                .text(selectedItem + " parameter deleted")
                .position(Pos.CENTER)
                .darkStyle()
                .showInformation();
    }

    @FXML
    void timezoneComboBoxAction(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

        _init_();

        // Set Repeat every Minute
        ObservableList<Integer> minuteList = everyMinuteComboBox.getItems();
        minuteList.add(0, 1);
        minuteList.add(1, 2);
        minuteList.add(2, 5);
        minuteList.add(3, 10);
        minuteList.add(4, 15);
        minuteList.add(5, 20);

        // Set Repeat every Minute
        ObservableList<Integer> hourList = everyHourComboBox.getItems();
        hourList.add(0, 1);
        hourList.add(1, 2);
        hourList.add(2, 3);
        hourList.add(3, 4);
        hourList.add(4, 5);

        // Set default Cron
        cronListView.setItems(cronLists.cronObservableList.sorted());
//        System.out.println("cronListView : " + cronListView.getItems());
        cronListView.setCellFactory(cronListView -> new CronListViewCell());

        cronListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    if (newValue != null) {
                        cronLists = new CronLists();
                        CronUtils.selectedCron = cronLists.getCronById(newValue.getId()); //newValue;
//                        System.out.println("newValue " + CronUtils.selectedCron.getStatus());
                        cStatus = new SimpleBooleanProperty(CronUtils.selectedCron.getStatus()
                                .equalsIgnoreCase(CronConstants.CRON_ENABLED));
                        System.out.println("cStatus " + cStatus);
                        if (cStatus.getValue())
                            cronStatusButton.setSelected(true);
                        else
                            cronStatusButton.setSelected(false);

                        cronStatusButton.textProperty().bind(
                                new When(cronStatusButton.selectedProperty())
                                        .then(CronConstants.CRON_ENABLED)
                                        .otherwise(CronConstants.CRON_DISABLED));

                        GridUtils gridUtils = new GridUtils();
                        gridUtils.clearGridPaneChildren(hoursGrid);
                        gridUtils.clearGridPaneChildren(minutesGrid);
                        gridUtils.clearGridPaneChildren(weekMonthGrid);

                        CronUtils.selectedCron.getHours().getOn()
                                .forEach(hour -> gridUtils.setCronHours(hoursGrid, hour));

                        CronUtils.selectedCron.getMinutes().getOn()
                                .forEach(minute -> gridUtils.setCronMinutes(minutesGrid, minute));

                        CronUtils.selectedCron.getWeekdays().getOn()
                                .forEach(week -> gridUtils.setCronWeeksMonth(weekMonthGrid, week));

                        CronUtils.selectedCron.getMonths().getOn()
                                .forEach(month -> gridUtils.setCronWeeksMonth(weekMonthGrid, month));

                        // Setup selected cron's params
                        if(CronUtils.selectedCron.getParameter().size() == 0) {
                            argumentComboBox.setItems(null);
                            argumentComboBox.setItems(FXCollections.observableArrayList(
                                    CronConstants.ARGUEMENT_STG));
                        } else {
                            argumentComboBox.setItems(null);
                            scriptNameText.setText(null);

                            scriptNameText.setText(CronUtils.selectedCron.getScript());
                            System.out.println("---- " + CronUtils.selectedCron.getParameter());

                            argumentComboBox.setItems(FXCollections.observableArrayList(
                                        CronUtils.selectedCron.getParameter()));

                            cronUtils.tidyArgumentList(argumentComboBox);
                        }

                        // Setup selected cron's time
                        LocalDate startDate = (! CronUtils.selectedCron.getStartDate().isEmpty()) ?
                                (LocalDate.parse(CronUtils.selectedCron.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                                : LocalDate.now();
                        LocalDate endDate = (! CronUtils.selectedCron.getEndDate().isEmpty()) ?
                                (LocalDate.parse(CronUtils.selectedCron.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                                : null;
                        LocalTime startTime = (! CronUtils.selectedCron.getStartTime().isEmpty()) ?
                                (LocalTime.parse(CronUtils.selectedCron.getStartTime(), DateTimeFormatter.ISO_LOCAL_TIME))
                                : LocalTime.now();
                        LocalTime endTime = (! CronUtils.selectedCron.getEndTime().isEmpty()) ?
                                (LocalTime.parse(CronUtils.selectedCron.getEndTime(), DateTimeFormatter.ISO_LOCAL_TIME))
                                : null;
                        cronStartDate.setValue(startDate);
                        cronEndDate.setValue(endDate);
                        cronStartTime.setValue(startTime);
                        cronEndTime.setValue(endTime);

                        //Setup selected cron's timezone
                        timezoneComboBox.setItems(zoneLists);
                        timezoneComboBox.getSelectionModel().select(CronUtils.selectedCron.getTimeZone());
                    }
                });
        cronListView.getSelectionModel().selectFirst();
    }

    private void _init_() {

        final Tooltip saveCronTooltip = new Tooltip();
        saveCronTooltip.setText("\nSave cron\n");
        saveCronTooltip.setTextAlignment(TextAlignment.CENTER);
        cronSaveButton.setTooltip(saveCronTooltip);

        final Tooltip delCronTooltip = new Tooltip();
        delCronTooltip.setText("\nDelete cron\n");
        delCronTooltip.setTextAlignment(TextAlignment.CENTER);
        cronDeleteButton.setTooltip(delCronTooltip);

        final Tooltip scriptTooltip = new Tooltip();
        scriptTooltip.setText("Choose a script");
        scriptTooltip.setTextAlignment(TextAlignment.CENTER);
//        scriptNameText .setTooltip(delCronTooltip);

    }
}
