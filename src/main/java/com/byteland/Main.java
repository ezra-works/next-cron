package com.byteland;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/cron.fxml"));
        primaryStage.setTitle("Next Cron");
//        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon4.png")));
        primaryStage.getIcons().add(new Image("/images/icon4.png"));

        Scene scene  = new Scene(root, 800, 600);

        scene.getStylesheets().add(getClass().getResource("/style/style.css").toString());
//        scene.setUserAgentStylesheet("style.css");
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
