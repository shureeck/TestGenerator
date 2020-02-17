package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TestGenerator extends Application {
    private Stage primaryStage = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TestGenerator.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        primaryStage.setTitle("CLI Tests Generator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
