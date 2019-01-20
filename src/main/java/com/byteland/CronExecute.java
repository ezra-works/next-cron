package com.byteland;

import com.byteland.model.Cron;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CronExecute {

    static List<Cron> executeList = new ArrayList<>();
    static int initialCronsHash = 0;
    public static void main(String[] args) {

        ScheduledExecutorService builder = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> handle= builder.scheduleWithFixedDelay(() ->
                scanAndBuild(), 0, 60, TimeUnit.SECONDS);
        try {
            handle.get();
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
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
                        System.out.println("nothing to execute");
                        builder = null;
                    }

                    try {
                        if(builder!=null) {
                            builder.directory(new File(CronConstants.CMD_PATH));
                            cron.setStatus(CronConstants.CRON_RUNNING);
                            CronUtils cronUtils = new CronUtils();
                            cronUtils.writeCronToDB(cron);
                            Process process = builder.start();
                            final int i = process.waitFor();
                            if(i==0) {
                                System.out.println(CronConstants.CRON_SUCCESS);
                                cron.setStatus(CronConstants.CRON_ENABLED);
                            }
                            else {
                                System.out.println(CronConstants.CRON_FAILED);
                                cron.setStatus(CronConstants.CRON_FAILED);
                            }
                            cronUtils.writeCronToDB(cron);
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void scanAndBuild() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        System.out.println("scanAndBuild " + localDate.getDayOfMonth()
                + "," + localDate.getMonthValue()
                + "," + localDate.getMonth()
                + "," + localDate.getDayOfWeek()
                + "," + localTime.getHour()
                + "," + localTime.plusMinutes(1).getMinute());

        CronLists cronLists = new CronLists();
        if(cronLists.cronObservableList.hashCode() != initialCronsHash) {

            final List<Cron> collect = cronLists.cronObservableList.parallelStream()
                    .filter(cron -> cron.getStatus().equalsIgnoreCase(CronConstants.CRON_ENABLED))
                    .filter(cron ->
                            LocalDate.parse(cron.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE).getYear()
                                    <= localDate.getYear())
                    .filter(cron ->
                            LocalDate.parse(cron.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE).getMonthValue()
                                    <= localDate.getMonthValue())
                    .filter(cron ->
                            LocalDate.parse(cron.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE).getDayOfMonth()
                                    <= localDate.getDayOfMonth())
                    .filter(cron -> {
                        if (cron.getEndDate().isEmpty())
                            return true;
                        else
                            return LocalDate.parse(cron.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE).getYear()
                                    >= localDate.getYear();
                    })
                    .filter(cron -> {
                        if (cron.getEndDate().isEmpty())
                            return true;
                        else
                            return LocalDate.parse(cron.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE).getMonthValue()
                                    >= localDate.getMonthValue();
                    })
                    .filter(cron -> {
                        if (cron.getEndDate().isEmpty())
                            return true;
                        else
                            return LocalDate.parse(cron.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE).getDayOfMonth()
                                    >= localDate.getDayOfMonth();
                    })
                    .filter(cron -> cron.getMonths().getOn().contains(localDate.getMonth().toString()))
                    .filter(cron -> cron.getWeekdays().getOn().contains(localDate.getDayOfWeek().toString()))
                    .filter(cron -> cron.getHours().getOn().contains(localTime.getHour()))
                    .filter(cron -> cron.getMinutes().getOn().contains(localTime.plusMinutes(1).getMinute()))
                    .collect(Collectors.toList());

//        System.out.println("collect " + collect);
            if (collect.size() > 0) {
                executeList.clear();
                executeList.addAll(collect);
            }
            System.out.println("executeList " + executeList);
            System.out.println("setting initialCronsHash");
            initialCronsHash = cronLists.cronObservableList.hashCode();
        }

        executeCron();
    }
}
