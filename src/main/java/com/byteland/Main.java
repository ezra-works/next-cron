package com.byteland;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main extends Application {

    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger LOGGER = Logger.getLogger("confLogger");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/cron.fxml"));
        primaryStage.setTitle("Next Cron");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon4.png")));

        Scene scene  = new Scene(root, 800, 600);

        scene.getStylesheets().addAll(getClass().getResource("/style/style.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            logManager.readConfiguration(Main.class.getResourceAsStream("/conf/logger.properties"));
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error in loading configuration",exception);
        }
        launch(args);
    }
}
