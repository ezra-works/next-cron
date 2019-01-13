package com.byteland;

import com.byteland.model.Cron;
import com.byteland.model.DefaultCron;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class CronLists {

    ObservableList<Cron> cronObservableList = FXCollections.observableArrayList();

    public CronLists() { setCronList(CronUtils.getCronFileList()); }

    private ObservableList<Cron> setCronList(List<File> files) {
        Gson gson;
        DefaultCron defaultCron = null;

//        System.out.println(files.get(0).getName());
        for (File file : files) {
            gson = new Gson();
            defaultCron = new DefaultCron();

            try {
                defaultCron = gson.fromJson(
                        IOUtils.toString(file.toURI(), Charset.defaultCharset())
                        , DefaultCron.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cronObservableList.add(defaultCron.getCron());
        }

        if(files.size() == 0) {
            gson = new Gson();
            defaultCron = new DefaultCron();

            try {
                defaultCron = gson.fromJson(
                        IOUtils.toString(getClass().getResourceAsStream("/json/sample_cron.json")
                                , Charset.defaultCharset())
                        ,   DefaultCron.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cronObservableList.add(0, defaultCron.getCron());
        }
        System.out.println("cronObservableList " + cronObservableList.sorted());
        return cronObservableList;
    }
}
