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
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane pane_to_login;
    @FXML
    private Button btn_minimizeInLogin;
    @FXML
    private Button btn_closeLogin;

    private DBConnector db;
    private Connection conn = null;
    private PreparedStatement pst = null;
    
    ResultSet rs = null;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBConnector();
       
    }
 
    @FXML
    public void handlebtn_admin(ActionEvent event) throws IOException {
        String usernameAdmin = tf_username.getText();
        String passwordAdmin = pwf_pass.getText();
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select username,password from adminregtable where "
                    + "username='" + tf_username.getText() + "' and password = '" + pwf_pass.getText() + "'");
            rs = pst.executeQuery();  
            
            if (rs.next()) {

                    if (usernameAdmin.equals(rs.getString("username")) && passwordAdmin.equals(rs.getString("password"))) {
                    Parent auditorView = FXMLLoader.load(getClass().getResource("SummaryView.fxml"));
                    Scene auditor_scene = new Scene(auditorView);

                    Stage auditor_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    auditor_stage.setScene(auditor_scene);
                } 
            }
            else{
            AlertDialogInsert.display("connection", "Sorry!!..\n You are not granted");
            } 
            
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null, ex);
        }
        
        
        
        
    } 

    //for auditor view
    @FXML
    public void handlebtn_auditor(ActionEvent event) throws IOException {
        String username = tf_username.getText();
        String password = pwf_pass.getText();
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select username,password from addauditorstable where "
                    + "username='" + tf_username.getText() + "' and password = '" + pwf_pass.getText() + "'");
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
            }
        catch(SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
   
    //minimize btn
    @FXML
    private void minimizeInLogin(ActionEvent event){
      MiniProject.getStageObj().setIconified(true);
    }
    //close btn
     @FXML
    private void closeInLogin(ActionEvent event){
    System.exit(0);
    }
    
  

}
