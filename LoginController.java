package main;

import Dialog.AlertDialogInsert;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private DBConnector db;
    private Connection conn = null;
    private PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBConnector();
    }

    @FXML
    private void handlebtn_admin(ActionEvent event) throws IOException {
        if (tf_username.getText().equals("") && pwf_pass.getText().equals("")) {
            Parent adminView = FXMLLoader.load(getClass().getResource("SummaryView.fxml"));
            Scene admin_scene = new Scene(adminView);

            Stage admin_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            admin_stage.setScene(admin_scene);
            admin_stage.show();
        } else {
            JOptionPane.showMessageDialog(null, "This username or password or both of Admin is Extremly Wrong!\nClick ok and try again.",
                    "Wrong pass", JOptionPane.ERROR_MESSAGE);
        }
    }

    //for auditor view
    @FXML
    public void handlebtn_auditor(ActionEvent event) throws IOException {
        //  DBConnector db = new DBConnector();
        Connection conn = db.Connect();

        String username = tf_username.getText();
        String password = pwf_pass.getText();
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select username,password from addauditorstable where username='" + tf_username.getText() + "' and password = '" + pwf_pass.getText() + "'");
            rs = pst.executeQuery();

            if (rs.next()) {

                    if (username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
                    Parent auditorView = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
                    Scene auditor_scene = new Scene(auditorView);

                    Stage auditor_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    auditor_stage.setScene(auditor_scene);
                } 
            }
            else{
            AlertDialogInsert.display("connection", "Sorry!!..\n You are not granted");
            }

        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, ex);

        }

    }

}
