package com.technesis.syrovatko;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class FxApp extends Application {
    private AnchorPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FxApp.class.getResource("/view/main.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        InputStream iconStream = getClass().getResourceAsStream("/icons/image.png");
        Image image = new Image(iconStream);
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Technesis Notes");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
 }
