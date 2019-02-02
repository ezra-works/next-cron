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
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CronUtils {

    private static final Logger LOGGER = Logger.getLogger(CronUtils.class.getName());
    static Cron selectedCron;

    public CronUtils(Boolean bool) {
        if(bool)
            setupFolders();
    }

    static List<File> getCronFileList() {
        try (Stream<Path> paths = Files.walk(Paths.get(CronConstants.DATA_PATH))) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No data files at " + CronConstants.DATA_PATH);
            return null;
        }
    }

    DefaultCron getDefaultCron() {
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

    void writeCronToDB(Cron cron) {
        AtomicBoolean isMatched = new AtomicBoolean(false);
        AtomicReference<File> wfile = new AtomicReference<>();
        Objects.requireNonNull(getCronFileList()).forEach(file -> {
            String baseName = FilenameUtils.getBaseName(file.getName());
            if(Integer.parseInt(baseName) == cron.getId()) {
                isMatched.set(true);
                wfile.set(file);
            }
        });

        if(! isMatched.get()) {
            LOGGER.log(Level.INFO, "No file found, so creating a new one");
            String fileName = cron.getId() + ".json";
            File nFile = new File(CronConstants.DATA_PATH + "/" + fileName);
            try {
                nFile.createNewFile();
                wfile.set(nFile);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error: cannot create new file");
            }
        }

        DefaultCron defaultCron = new DefaultCron();
        defaultCron.setCron(cron);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileUtils.writeStringToFile(wfile.get(), gson.toJson(defaultCron), Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Sorry cannot save the file to db");
        }
    }

    void deleteCronFromDB(Cron cron) {
        Objects.requireNonNull(getCronFileList()).forEach(file -> {
            String baseName = FilenameUtils.getBaseName(file.getName());
            if(Integer.parseInt(baseName) == cron.getId()) {
                file.delete();
            }
        });
    }

    void tidyArgumentList(ComboBox<String> argumentComboBox) {
        argumentComboBox.getItems().removeIf(Objects::isNull);
        argumentComboBox.getItems().removeIf(s -> s.equalsIgnoreCase(CronConstants.ARGUEMENT_STG));
        LOGGER.log(Level.FINEST, "tidyArgumentList: " + argumentComboBox.getItems());
//        return argumentComboBox.getItems();
    }

    ObservableList<String> getTimeZoneList() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // Allowed zone names
        Set<String> allowedTimeZoneNames = new HashSet<>();
        allowedTimeZoneNames.add("UTC");
        allowedTimeZoneNames.add("Asia/Calcutta");
        allowedTimeZoneNames.add("Australia/Sydney");

        final List<String> zoneLists = ZoneId.getAvailableZoneIds()
                .stream()
                .filter(allowedTimeZoneNames::contains)
                .map(ZoneId::of)
                .map(zoneId -> {
                    return String.format("%s (UTC%s)", zoneId.toString(),
                            localDateTime.atZone(zoneId)
                                    .getOffset()
                                    .getId()
                                    .replaceAll("Z", "+00:00"));
                })
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        final ObservableList<String> zoneIds = FXCollections.observableArrayList(zoneLists);
        LOGGER.log(Level.INFO, "available ZoneIds: " + zoneIds);
        return zoneIds;
    }

    ZoneId getCronZoneId(String cronTimezone) {
//        "Asia/Calcutta (UTC+05:30)"
         String zone = cronTimezone.substring(0, cronTimezone.indexOf("(")).trim();
         return ZoneId.of(zone);
    }

    ZoneOffset getCronZoneOffset(String cronTimezone) {
//        "Asia/Calcutta (UTC+05:30)"
        String zoneOffset = cronTimezone.substring(cronTimezone.indexOf("+"), cronTimezone.indexOf(")")).trim();
        return ZoneOffset.of(zoneOffset);
    }

    private void setupFolders() {

        Path path = Paths.get(CronConstants.DATA_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                if(Files.exists(path)) {
                    LOGGER.log(Level.INFO, "path created " + path);
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "data folder cannot be created" + e.getMessage());
            }
        }
        path = Paths.get(CronConstants.LOG_PATH);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                if(Files.exists(path)) {
                    LOGGER.log(Level.INFO, "path created " + path);
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "log folder cannot be created" + e.getMessage());
            }
        }
    }
}
