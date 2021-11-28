package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        File file = new File("doggo.jpg");
        Parent root = FXMLLoader.load(getClass().getResource("Switch.fxml"));
        root.setStyle("-fx-background-image: url('"+file.toURI().toString()+"'); ");
        primaryStage.setTitle("Dictionary 19020242");
        primaryStage.setScene(new Scene(root, 456, 337));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
