package com.byteland;

import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

class GridUtils {

    void clearGridPaneChildren(GridPane gridPane) {
        gridPane.getChildren()
                .filtered(node -> node instanceof JFXCheckBox)
                .forEach(node -> ((JFXCheckBox) node).setSelected(false));
    }
    void setCronHours(GridPane hoursGrid, int hour) {
        hoursGrid.getChildren()
                .filtered(node -> node instanceof JFXCheckBox)
                .filtered(node -> node.getId().contains("hour" + String.format("%02d", hour)))
                .forEach(node -> ((JFXCheckBox) node).setSelected(true));
    }
    void setCronMinutes(GridPane minutesGrid, int minute) {
        minutesGrid.getChildren()
                .filtered(node -> node instanceof JFXCheckBox)
                .filtered(node -> node.getId().contains("minute" + String.format("%02d", minute)))
                .forEach(node -> ((JFXCheckBox) node).setSelected(true));
    }
    void setCronWeeksMonth(GridPane gridPage, String wkMth) {
        gridPage.getChildren()
                .filtered(node -> node instanceof JFXCheckBox)
                .filtered(node -> node.getId().contains(wkMth.toLowerCase()))
                .forEach(node -> ((JFXCheckBox) node).setSelected(true));
    }
    List<Integer> getSelectedHoursMinutes(GridPane gridPage, String hrMin) {
        List<Integer> selectList = new ArrayList<>();
        gridPage.getChildren()
                .filtered(node -> node instanceof JFXCheckBox)
                .filtered(node -> node.getId().contains(hrMin))
                .filtered(node -> ((JFXCheckBox)node).isSelected())
                .forEach(node -> {
                    JFXCheckBox jfxCheckBox = ((JFXCheckBox)node);
                    int selected = Integer.parseInt(jfxCheckBox.getText());
                    selectList.add(selected);
                });
        return selectList;
    }
    List<String> getSelectedWeeksMonths(GridPane gridPage, String wkMon) {
        List<String> selectList = new ArrayList<>();
        gridPage.getChildren()
                .filtered(node -> node instanceof JFXCheckBox)
                .filtered(node -> node.getAccessibleText() != null)
                .filtered(node -> node.getAccessibleText().contains(wkMon))
                .filtered(node -> ((JFXCheckBox)node).isSelected())
                .forEach(node -> {
                    JFXCheckBox jfxCheckBox = ((JFXCheckBox)node);
                    selectList.add(jfxCheckBox.getText().toUpperCase());
                });
        return selectList;
    }
}
