package main;

import Dialog.AlertDialogInsert;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddSupplierController implements Initializable 
{

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
    private Label lbl_auditorIdAddSupplier;
    @FXML
    private TextField tf_supid;
    @FXML
    private TextField tf_supname;
    @FXML
    private TextField tf_supconnum;
 
    @FXML
    private Button btn_add_supplier1;
    @FXML
    private Button btn_back_inaddsupplier;
    @FXML
    private TextArea ta_supadd;
    @FXML
    private Button btnCloseInAddSupplier;
    @FXML
    private Button btnMinimizeInAddSupplier;
    @FXML
    private Label lbl_validateSupId;
    @FXML
    private Label lbl_validateSupname;
    @FXML
    private Label lbl_validateSupConnum;
    @FXML
    private Label lbl_validateSupAdd;
    @FXML
    private ComboBox<String> cmb_auditorIdAddSupplier;
    @FXML
    private TextField tf_dateAddSupplier;
    @FXML
    private Label lbl_dateInAddSupplier;
   
    
ObservableList<String> list = FXCollections.observableArrayList();
    
    private DBConnector db;
    private PreparedStatement pst = null;
    Connection conn=null;
    ResultSet rs = null;
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
    
         Date date = new Date (); 
         String date1 = date.toString();
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         tf_dateAddSupplier.setText(sdf.format(date));
         db = new DBConnector();
     
       
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addauditorstable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list.add(rs.getString("auditorid"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        cmb_auditorIdAddSupplier.setItems(list);
     
    }

    @FXML
    public void AddSupplier(ActionEvent event) throws SQLException 
    {
       
        boolean isSupIdEmpty = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_supid,lbl_validateSupId, "Supplier's ID is required." );
        boolean isSupNameEmpty = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_supname,lbl_validateSupname, "Supplier's name is required." );
        boolean isSupConnumEmpty = Validation.TextFieldValidation.isTextFieldTypePhoneNumber(tf_supconnum,lbl_validateSupConnum, "Supplier's contact number is required in integer format." );
        boolean isSupAddEmpty = Validation.TextFieldValidation.isTextAreaNotEmpty(ta_supadd,lbl_validateSupAdd, "Supplier's address is required." );
       
        if(isSupIdEmpty && isSupNameEmpty && isSupConnumEmpty && isSupAddEmpty ){
        Connection conn = db.Connect();
        String sql = "INSERT INTO addsuppliertable(supid,supname, supconnum, supadd, aid,date) VALUES (?,?,?,?,?,?)"; 
        String supid = tf_supid.getText();
        String supname = tf_supname.getText();
        String supconnum = tf_supconnum.getText();
        String supadd = ta_supadd.getText();
        String aidInAddSupplier = cmb_auditorIdAddSupplier.getValue();
        String date2 = tf_dateAddSupplier.getText();
       
    
        try 
          {
            pst = conn.prepareStatement(sql);
            pst.setString(1, supid);
            pst.setString(2, supname);
            pst.setString(3, supconnum);
            pst.setString(4, supadd);
            pst.setString(5,aidInAddSupplier );
            pst.setString(6,date2 );
           
            
            AlertDialogInsert.display("Connection", "Data Inserted Successfully");
           } 
          catch (SQLException ex) 
          {
            Logger.getLogger(AddSupplierController.class.getName()).log(Level.SEVERE, null, ex);
          } 
        finally 
        {
            pst.execute();
            pst.close();
        }
        }
        tf_supid.clear();
        tf_supname.clear();
        tf_supconnum.clear();
        ta_supadd.clear();
        cmb_auditorIdAddSupplier.setValue(null);
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
     //minimize btn
    @FXML
    private void minimizeInAddSupplierBtn(ActionEvent event){
      MiniProject.getStageObj().setIconified(true);
    }
    //close btn
     @FXML
    private void closeInAddSupplierBtn(ActionEvent event){
    System.exit(0);
    }
    
}
