package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Edit implements Initializable {

    DictionaryManagement dm = new DictionaryManagement();

    @FXML
    private Button back = new Button();

    @FXML
    private Button add = new Button();

    @FXML
    private Button remove = new Button();

    @FXML
    private Button fix = new Button();

    @FXML
    private Button confirm = new Button();

    @FXML
    private Text targetDisplay = new Text();

    @FXML
    private Text explainDisplay = new Text();

    @FXML
    private TextField editTarget = new TextField();

    @FXML
    private TextField editExplain = new TextField();

    List<Word> database = new ArrayList<>();
    int i=0;

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    String dbURL = "jdbc:mysql://localhost:3306/?user=root";

    String username = "root";
    String password = "123456";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        targetDisplay.setVisible(false);
        explainDisplay.setVisible(false);
        editTarget.setVisible(false);
        editExplain.setVisible(false);
        confirm.setVisible(false);
        ketnoi();
    }

    public void ketnoi() {
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM database_19020424.tbl_edict");
            while (rs.next()) {
                database.add(new Word(rs.getString(2),rs.getString(3)));
            }
            conn.close();
        } catch (SQLException throwables) { }
    }

    @FXML
    public void quayLai(ActionEvent e) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("switch.fxml"));
        File file = new File("doggo.jpg");
        root.setStyle("-fx-background-image: url('"+file.toURI().toString()+"'); ");
        Stage stage = (Stage) back.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void them(ActionEvent e) {
        targetDisplay.setVisible(true);
        explainDisplay.setVisible(true);
        editTarget.setVisible(true);
        editExplain.setVisible(true);
        confirm.setVisible(true);
        targetDisplay.setText("Từ cần thêm:");
        explainDisplay.setText("Nghĩa:");
    }

    @FXML
    public void xoa(ActionEvent e) {
        targetDisplay.setVisible(true);
        explainDisplay.setDisable(true);
        editTarget.setVisible(true);
        editExplain.setDisable(true);
        confirm.setVisible(true);
        targetDisplay.setText("Từ cần xóa:");
        explainDisplay.setDisable(false);
        editExplain.setDisable(false);
        explainDisplay.setVisible(false);
        editExplain.setVisible(false);
    }

    @FXML
    public void sua(ActionEvent e) {
        targetDisplay.setVisible(true);
        explainDisplay.setVisible(true);
        editTarget.setVisible(true);
        editExplain.setVisible(true);
        confirm.setVisible(true);
        targetDisplay.setText("Từ cần sửa:");
        explainDisplay.setText("Nghĩa:");

    }

    public void remove(String word) {
        database.remove(i);
    }

    public void add(String _word_target, String _word_explain) {
        database.add(new Word(_word_target, _word_explain));
    }

    public void set(String _word_target, String _word_explain) {
        database.set(i, new Word(_word_target, _word_explain));
    }

    @FXML
    public void handleConfirm(ActionEvent e) {
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
            st = conn.createStatement();
            String temp = targetDisplay.getText();
            if (temp.equals("Từ cần thêm:")) {
                String _word_target = editTarget.getText();
                String _word_explain = editExplain.getText();
                if (!check(_word_target)) {
                    add(_word_target, _word_explain);
                    st.executeUpdate("INSERT INTO database_19020424.tbl_edict (idx, word, detail) VALUES ('"
                            + i++  + "', '" + _word_target + "', '<C><F><I><N><Q>" + _word_explain + "</Q></N></I></F></C>')");
                }
                System.out.println("Success");
            } else if (temp.equals("Từ cần xóa:")) {
                String _word_target = editTarget.getText();
                if (check(_word_target)) {
                    remove(_word_target);
                    st.executeUpdate("DELETE FROM database_19020424.tbl_edict WHERE word = '" + _word_target + "'");
                }
                System.out.println("Success");
            } else if (temp.equals("Từ cần sửa:")) {
                String _word_target = editTarget.getText();
                String _word_explain = editExplain.getText();
                if (check(_word_target)) {
                    set(_word_target, _word_explain);
                    st.executeUpdate("UPDATE database_19020424.tbl_edict SET detail = '<C><F><I><N><Q>" + _word_explain + "</Q></N></I></F></C>' WHERE word = '" + _word_target + "'");
                }
                System.out.println("Success");
            }
        } catch (Exception exception) {}
    }

    public boolean check(String word) {
        for(i = 0; i < database.size(); i++) {
            if(word.equals(database.get(i).getWord_target())) return true;
        }
        return false;
    }

}
