package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;

public class Switch implements Initializable {

    @FXML private Button main = new Button();
    @FXML private Button edit = new Button();

    Stage stage;
    Parent root;
    File file = new File("doggo.jpg");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void changeMainButton() throws Exception {
        root = FXMLLoader.load(getClass().getResource("main.fxml"));
        root.setStyle("-fx-background-image: url('"+file.toURI().toString()+"'); ");
        Stage stage = (Stage) main.getScene().getWindow();
        Scene scene = new Scene(root, 600, 496);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void changeEditButton() throws Exception {
        root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Stage stage = (Stage) main.getScene().getWindow();
        Scene scene = new Scene(root, 300, 218);
        stage.setScene(scene);
        stage.show();
    }
}
