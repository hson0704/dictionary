package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DictionaryApplication extends DictionaryCommandLine implements Initializable {

    @FXML
            private TextField nhap = new TextField();
    @FXML
            private ListView<String> out = new ListView<>();
    @FXML
            private TextArea print = new TextArea();
    @FXML
            private Button back = new Button();

    List<Word> dulieu = new ArrayList<>();
    List<Word> database = new ArrayList<>();

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    File file = new File("doggo.jpg");
    Image image = new Image(file.toURI().toString());
    ImageView imageView = new ImageView(image);


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ketnoi();
    }

    @FXML
    public void quayLai(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("switch.fxml"));
        root.setStyle("-fx-background-image: url('"+file.toURI().toString()+"'); ");
        Stage stage = (Stage) back.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleinput(KeyEvent e) {
        String search = nhap.getText();
        dulieu.clear();
        for(int i=0; i < database.size(); i++) {
            if(database.get(i).getWord_target().contains(search)) dulieu.add(database.get(i));
        }
        out.getItems().clear();
        for(Word temp : dulieu) {
            out.getItems().add(temp.getWord_target());
        }
    }

    @FXML
    public void display(MouseEvent event) {
        String target = out.getSelectionModel().getSelectedItem();
        for(Word temp : dulieu) {
            if(target.equals(temp.getWord_target())) {
                String word = "";
                String explain = temp.getWord_explain();
                for(int i=15;i<explain.length()-20;i++) {
                    if(explain.charAt(i)=='<') {
                        i += 6;
                        word += "\n";
                    }
                    if(explain.charAt(i)=='=') {
                        word += "   ";
                    } else if(explain.charAt(i)=='+') {
                        word += "\n\t";
                    } else if(explain.charAt(i)=='<') {
                        i += 6;
                        word += "\n";
                    }
                    word += temp.getWord_explain().charAt(i);
                }
                print.setText(word);
                break;
            }
        }
    }

    public void ketnoi() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/?user=root";
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(dbURL, username, password);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM database_19020424.tbl_edict");
            while (rs.next()) {
                database.add(new Word(rs.getString(2),rs.getString(3)));
            }
            } catch (SQLException throwables) { }
        }

}
