package com.byteland;

import com.byteland.model.Cron;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CronExecute {

    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger("confLogger");

    static List<Cron> executeList = new ArrayList<>();
    static int initialCronsHash = 0;

    public static void main(String[] args) {

        try {
            logManager.readConfiguration(CronExecute.class.getResourceAsStream("/conf/logger.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration",exception);
        }

        ScheduledExecutorService builder = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> handle= builder.scheduleWithFixedDelay(() ->
                scanAndBuild(), (60 - LocalTime.now().getSecond()), 60, TimeUnit.SECONDS);
        try {
            handle.get();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private static void executeCron() {
//        CronLists cronLists = new CronLists();
//        executeList = cronLists.cronObservableList;
        executeList.parallelStream()
                .forEach(cron -> {
                    ProcessBuilder builder = new ProcessBuilder();
                    if (cron.getScript().endsWith("bat")) {
                        System.out.println("executing " + cron.getScript());
                        builder.command("cmd.exe", "/c", cron.getScript());
                    }
                    else if(cron.getScript().endsWith("ps1")) {
                        System.out.println("executing " + cron.getScript());
                        builder.command("powershell.exe", "-Command", cron.getScript());
                    }
                    else if(cron.getScript().endsWith("sh")) {
                        System.out.println("executing " + cron.getScript());
                        builder.command(cron.getScript());
                    }
                    else {
//                        builder.command("sh", "-c", cron.getScript());
                        LOGGER.log(Level.WARNING,"nothing to execute");
                        builder = null;
                    }

                    try {
                        if(builder!=null) {
                            builder.directory(new File(CronConstants.CMD_PATH));
                            cron.setStatus(CronConstants.CRON_RUNNING);
                            CronUtils cronUtils = new CronUtils(false);
                            cronUtils.writeCronToDB(cron);
                            Process process = builder.start();
                            final int i = process.waitFor();
                            if(i==0) {
                                LOGGER.log(Level.INFO, cron.getName() + " ran " + CronConstants.CRON_SUCCESS);
                                cron.setStatus(CronConstants.CRON_ENABLED);
                            }
                            else {
                                LOGGER.log(Level.SEVERE, cron.getName() + " " + CronConstants.CRON_FAILED);
                                cron.setStatus(CronConstants.CRON_FAILED);
                            }
                            cronUtils.writeCronToDB(cron);
                        }
                    } catch (IOException | InterruptedException e) {
                        LOGGER.log(Level.SEVERE, "cron execution " + e.getMessage());
                    }
                });
    }

    private static void scanAndBuild() {
        final ZoneId localZoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(localZoneId);

        LOGGER.log(Level.FINEST, "scanAndBuild " + zonedDateTime.getDayOfMonth()
                + "," + zonedDateTime.getMonthValue()
                + "," + zonedDateTime.getMonth()
                + "," + zonedDateTime.getDayOfWeek()
                + "," + zonedDateTime.getHour()
                + "," + zonedDateTime.getMinute()
                + "," + localZoneId);

        CronLists cronLists = new CronLists();
//        if(cronLists.cronObservableList.hashCode() != initialCronsHash) {

        final List<Cron> collect = cronLists.cronObservableList.parallelStream()
                .filter(cron -> cron.getStatus().equalsIgnoreCase(CronConstants.CRON_ENABLED))
                .filter(cron ->
                        ZonedDateTime.parse(cron.getStartDate()).withZoneSameInstant(localZoneId)
                                .getYear() <= zonedDateTime.getYear())
                .filter(cron ->
                        ZonedDateTime.parse(cron.getStartDate()).withZoneSameInstant(localZoneId)
                                .getMonthValue() <= zonedDateTime.getMonthValue())
                .filter(cron ->
                        ZonedDateTime.parse(cron.getStartDate()).withZoneSameInstant(localZoneId)
                                .getDayOfMonth() <= zonedDateTime.getDayOfMonth())
                .filter(cron -> {
                    if (cron.getEndDate().isEmpty())
                        return true;
                    else
                        return ZonedDateTime.parse(cron.getEndDate()).withZoneSameInstant(localZoneId)
                                .getYear() >= zonedDateTime.getYear();
                })
                .filter(cron -> {
                    if (cron.getEndDate().isEmpty())
                        return true;
                    else
                        return ZonedDateTime.parse(cron.getEndDate()).withZoneSameInstant(localZoneId)
                                .getMonthValue() >= zonedDateTime.getMonthValue();
                })
                .filter(cron -> {
                    if (cron.getEndDate().isEmpty())
                        return true;
                    else
                        return ZonedDateTime.parse(cron.getEndDate()).withZoneSameInstant(localZoneId)
                                .getDayOfMonth() >= zonedDateTime.getDayOfMonth();
                })
                .filter(cron -> cron.getMonths().getOn().contains(zonedDateTime.getMonth().toString()))
                .filter(cron -> cron.getWeekdays().getOn().contains(zonedDateTime.getDayOfWeek().toString()))
                .filter(cron -> cron.getHours().getOn().contains(zonedDateTime.getHour()))
                .filter(cron -> cron.getMinutes().getOn().contains(zonedDateTime.getMinute()))
                .collect(Collectors.toList());

        executeList.clear();
        if (collect.size() > 0) {
            executeList.addAll(collect);
            LOGGER.log(Level.FINEST, "executeList updated : " + executeList);
            executeCron();
        } else
            LOGGER.log(Level.INFO, "no crons to execute");
    }

}
