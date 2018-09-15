package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pwf_pass;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Button btn_admin;
    @FXML
    private Button btn_auditor;

    @FXML
    private void handlebtn_admin(ActionEvent event) throws IOException {

        if (tf_username.getText().equals("admin") && pwf_pass.getText().equals("admin")) {
            Parent adminView = FXMLLoader.load(getClass().getResource("SummaryView.fxml"));
            Scene admin_scene = new Scene(adminView);

            Stage admin_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            admin_stage.setScene(admin_scene);
            admin_stage.show();
        }
        else {
            JOptionPane.showMessageDialog(null, "This username or password or both of Admin is Extremly Wrong!\nClick ok and try again.",
                    "Wrong pass", JOptionPane.ERROR_MESSAGE);
        }

    }

    //for auditor view
    @FXML
    private void handlebtn_auditor(ActionEvent event) throws IOException {

        if (tf_username.getText().equals("") && pwf_pass.getText().equals("")) {
            Parent auditorView = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
            Scene auditor_scene = new Scene(auditorView);

            Stage auditor_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            auditor_stage.setScene(auditor_scene);
            auditor_stage.show();

        } else {
            JOptionPane.showMessageDialog(null, "This username or password or both of Admin is Extremly Wrong!\nClick ok and try again.",
                    "Wrong pass", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
