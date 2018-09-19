package main;

import Dialog.AlertDialogInsert;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddSupplierController implements Initializable {

    @FXML
    private AnchorPane AddSupplierOverview;
    @FXML
    private Label lbl_addsup;
    @FXML
    private Label lbl_supid;
    @FXML
    private Label lbl_supname;
    @FXML
    private Label lbl_supconnum;
    @FXML
    private Label lbl_supadd;
    @FXML
    private Label lbl_supdes;
    @FXML
    private TextField tf_supid;
    @FXML
    private TextField tf_supname;
    @FXML
    private TextField tf_supconnum;
    @FXML
    private TextField tf_supadd;
    @FXML
    private TextField tf_supdes;
    @FXML
    private Button btn_add_supplier1;

    private DBConnector db;
    private PreparedStatement pst = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
     db = new DBConnector();
    }

    @FXML
    public void AddSupplier() throws SQLException {
        Connection conn = db.Connect();
        String sql = "INSERT INTO addsuppliertable(supid,supname, supconnum, supadd, des) VALUES (?,?,?,?,?)"; 
        String supid = tf_supid.getText();
        String supname = tf_supname.getText();
        String supconnum = tf_supconnum.getText();
        String supadd = tf_supadd.getText();
        String des = tf_supdes.getText();

        try 
          {
            pst = conn.prepareStatement(sql);
            pst.setString(1, supid);
            pst.setString(2, supname);
            pst.setString(3, supconnum);
            pst.setString(4, supadd);
            pst.setString(5, des);
            
            AlertDialogInsert.display("Connection", "Data Inserted Successfully");
           } catch (SQLException ex) {
            Logger.getLogger(AddSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.execute();
            pst.close();
        }
        tf_supid.clear();
        tf_supname.clear();
        tf_supconnum.clear();
        tf_supadd.clear();
        tf_supdes.clear();
    }
    @FXML
    public void btnBackAddSupplier(ActionEvent event) throws IOException
    {
    
    Parent BackAddSupplier = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
    Scene supplier = new Scene(BackAddSupplier);
    
    Stage showSupplier= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplier.setScene(supplier);
    showSupplier.show();
    }
}
