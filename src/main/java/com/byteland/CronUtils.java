package com.byteland;

import com.byteland.model.Cron;
import com.byteland.model.DefaultCron;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CronUtils {

    public static Cron selectedCron;

    public static List<File> getCronFileList() {
        try (Stream<Path> paths = Files.walk(Paths.get("/Users/ezmoses/IdeaProjects/next-cron/db"))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DefaultCron getDefaultCron() {
        Gson gson = new Gson();
        DefaultCron defaultCron = new DefaultCron();

        try {
            defaultCron = gson.fromJson(
                    IOUtils.toString(getClass().getResourceAsStream("/json/sample_cron.json")
                            , Charset.defaultCharset())
                    ,   DefaultCron.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultCron;
    }

    public void writeCronToDB(Cron cron) {
        AtomicBoolean isMatched = new AtomicBoolean(false);
        AtomicReference<File> wfile = new AtomicReference<File>();
        getCronFileList().forEach(file -> {
            String baseName = FilenameUtils.getBaseName(file.getName());
            if(Integer.parseInt(baseName) == cron.getId()) {
                isMatched.set(true);
                wfile.set(file);
            }
        });

        if(! isMatched.get()) {
            System.out.println("No file found, so creating a new one");
            String fileName = cron.getId() + ".json";
            File nFile = new File("/Users/ezmoses/IdeaProjects/next-cron/db/" + fileName);
            try {
                nFile.createNewFile();
                wfile.set(nFile);
            } catch (IOException e) {
                System.out.println("Error: cannot create new file");
            }
        }

        DefaultCron defaultCron = new DefaultCron();
        defaultCron.setCron(cron);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileUtils.writeStringToFile(wfile.get(), gson.toJson(defaultCron), Charset.defaultCharset());
        } catch (IOException e) {
            System.out.println("Sorry cannot save the file to db");
        }
    }

    public void deleteCronFromDB(Cron cron) {
        getCronFileList().forEach(file -> {
            String baseName = FilenameUtils.getBaseName(file.getName());
            if(Integer.parseInt(baseName) == cron.getId()) {
                file.delete();
            }
        });
    }

    public void tidyArgumentList(ComboBox<String> argumentComboBox) {
        argumentComboBox.getItems().removeIf(Objects::isNull);
        argumentComboBox.getItems().removeIf(s -> s.equalsIgnoreCase(CronConstants.ARGUEMENT_STG));
        System.out.println("tidyArgumentList: " + argumentComboBox.getItems());
//        return argumentComboBox.getItems();
    }

    public ObservableList<String> getTimeZoneList() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // Allowed zone names
        Set<String> allowedTimeZoneNames = new HashSet<>();
        allowedTimeZoneNames.add("UTC");
        allowedTimeZoneNames.add("Asia/Calcutta");
        allowedTimeZoneNames.add("Australia/Sydney");

        final List<String> zoneLists = ZoneId.getAvailableZoneIds()
                .stream()
                .filter(zoneName -> allowedTimeZoneNames.contains(zoneName))
                .map(ZoneId::of)
                .map(zoneId -> {
                    final String zones = String.format("%s (UTC%s)", zoneId.toString(),
                            localDateTime.atZone(zoneId)
                                    .getOffset()
                                    .getId()
                                    .replaceAll("Z", "+00:00"));
                    return zones;
                })
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        final ObservableList<String> zoneIds = FXCollections.observableArrayList(zoneLists);
        System.out.println("listZoneIds: " + zoneIds);
        return zoneIds;
    }
}
