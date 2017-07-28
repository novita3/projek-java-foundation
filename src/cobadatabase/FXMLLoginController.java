/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobadatabase;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author vita
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField textUser;
    @FXML
    private TextField textPass;

    Stage stage = new Stage();
    ArrayList<String> user;
    ArrayList<String> password;

    @FXML
    public void login() {
        FXMLDocumentController baru = new FXMLDocumentController();
        if (textUser.getText().equalsIgnoreCase(null) || textPass.getText().equalsIgnoreCase(null)) {
            label.setText("Anda gagal");
        } else {
            for (int i = 0; i < user.size(); i++) {
                if (textUser.getText().equalsIgnoreCase(user.get(i)) && textPass.getText().equalsIgnoreCase(password.get(i))) {
                    label.setText("anda berhasil");
                    enterlog();
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Peringatan");
                    alert.setHeaderText(null);
                    alert.setContentText("username dan password salah");
                    alert.showAndWait();
                }
                break;
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user = new ArrayList<>();
        password = new ArrayList<>();
        user.add("admin");
        password.add("admin");
        user.add("novita");
        password.add("novita");
        textUser.clear();
        textPass.clear();
        // TODO
    }

    @FXML
    public void enterlog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.setTitle("Order here");
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
