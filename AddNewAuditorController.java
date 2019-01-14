

package main;


import Dialog.AlertDialogInsert;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



public class AddNewAuditorController implements Initializable {

    @FXML
    private AnchorPane AddNewAuditorOverview;
    @FXML
    private Label lbl_addNewAuditor;
    @FXML
    private Label lbl_auditorId;
    @FXML
    private Label lbl_auditorFname;
    @FXML
    private Label lbl_auditorLname;
    @FXML
    private Label lbl_auditorAdd;
    @FXML
    private Label lbl_auditorNic;
    @FXML
    private Label lbl_auditorDob;
    @FXML
    private Label lbl_auditorConnum;
    @FXML
    private Label lbl_auditorUname;
    @FXML
    private Label lbl_auditorPassword;
    @FXML
    private TextField tf_auditorId;
    @FXML
    private TextField tf_auditorFname;
    @FXML
    private TextField tf_auditorLname;
    @FXML
    private TextField tf_auditorNic;
    @FXML
    private DatePicker datepicker_auditorDob;
    @FXML
    private TextField tf_auditorConnum;
    @FXML
    private TextField tf_auditorUname;
    @FXML
    private TextField tf_date_inaddauditor;
    @FXML
    private PasswordField pwf_auditorPassword;
    @FXML
    private Button btn_load_auditorDetails;
    @FXML
    private Button btn_addAuditor;
    @FXML
    private Button btn_updateAuditor;
    @FXML
    private Button btn_deleteAuditor;
    @FXML
    private TextArea ta_auditorAdd;

    private DBConnector db;
    private Connection conn = null;
    private PreparedStatement pst = null;
    ResultSet rs = null;
    @FXML
    private Button btn_summary_inaddauditor;
    @FXML
    private Button btn_back_inaddauditor;
    @FXML
    private Button btnCloseInAddNewAuditor;
    @FXML
    private Button btnMinimizeInAddNewAuditor;
    @FXML
    private Label lbl_vlidateAuditorFname;
    @FXML
    private Label lbl_validateAuditorLname;
    @FXML
    private Label lbl_validateAuditorAddress;
    @FXML
    private Label lbl_validateAuditorConnum;
    @FXML
    private Label lbl_validateAuditorId;
    @FXML
    private Label lbl_validateAuditorNic;
    @FXML
    private Label lbl_validateAuditorPass;
    @FXML
    private Label lbl_validateAuditorUname;
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      db = new DBConnector();
    }    
    
    
public class Function{
    
    @FXML
    public ResultSet find(String s){
    
    try{
    conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
    pst = conn.prepareStatement("select * from addauditorstable where auditorid = ?");
    pst.setString(1, s);
    rs = pst.executeQuery();
    
    }
    catch(SQLException ex){
      System.err.println("Error" + ex);
    
    }
    return rs;
    }
}
    
    @FXML
    public void btnLoadNewAuditorDetails(ActionEvent event)
    {
    AddNewAuditorController.Function f = new AddNewAuditorController.Function();
    String auditorid = "auditorid";
    String firstname = "firstname";
    String lastname = "lastname";
    String address = "address";
    String nic = "nic";
    String dob = "dob";
    String connum = "connum";
    String username = "username";
    String password = "password";
    rs = f.find(tf_auditorId.getText());
    try{
    
        if(rs.next()){
            
        tf_auditorFname.setText(rs.getString("firstname"));
        tf_auditorLname.setText(rs.getString("lastname"));
        ta_auditorAdd.setText(rs.getString("address"));
        tf_auditorNic.setText(rs.getString("nic"));
        tf_date_inaddauditor.setText(rs.getString("dob"));
        tf_auditorConnum.setText(rs.getString("connum"));
        tf_auditorUname.setText(rs.getString("username"));
        pwf_auditorPassword.setText(rs.getString("password"));
        
        
        }
        else{
         JOptionPane.showMessageDialog(null, "No data for this categoryid");
        }
    
    }
    catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex.getMessage()); 
        
   }
 }
    
   @FXML
   public void addNewAuditor(ActionEvent event) throws SQLException {
       
       boolean isauditorid = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_auditorId, lbl_validateAuditorId, "AudiorID is required.");
       boolean isfname = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_auditorFname, lbl_vlidateAuditorFname, "Auditor Firstname is required.");
       boolean islname = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_auditorLname, lbl_validateAuditorLname, "Auditor Lastname is required.");
       boolean isadressAuditor = Validation.TextFieldValidation.isTextAreaNotEmpty(ta_auditorAdd, lbl_validateAuditorAddress, "Auditor Address is required.");
       boolean isnic = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_auditorNic, lbl_validateAuditorNic, "Auditor NIC Number is required.");
       boolean isconnum = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_auditorConnum, lbl_validateAuditorConnum, "Auditor Contact number is required.");
       boolean isusername = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_auditorUname, lbl_validateAuditorUname, "Auditor Username is required.");
       boolean ispassword = Validation.TextFieldValidation.isPasswordFieldNotEmpty(pwf_auditorPassword, lbl_validateAuditorPass, "Auditor Password is required.");
       
       if(isauditorid && isfname && islname && isadressAuditor && isnic && isconnum && isusername && ispassword ){
       Connection conn = db.Connect();
            String auditorid = tf_auditorId.getText();
            String firstname = tf_auditorFname.getText();
            String lastname = tf_auditorLname.getText();
            String address = ta_auditorAdd.getText();
            String nic = tf_auditorNic.getText();
            String dob = datepicker_auditorDob.getEditor().getText();
            String connum = tf_auditorConnum.getText();
            String username = tf_auditorUname.getText();
            String password = pwf_auditorPassword.getText();
        try {
           String sql = "insert into addauditorstable (auditorid,firstname,lastname,address,nic,dob,connum,username,password)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, auditorid);
            pst.setString(2, firstname);
            pst.setString(3, lastname);
            pst.setString(4, address);
            pst.setString(5, nic);
            pst.setString(6, dob);
            pst.setString(7, connum);
            pst.setString(8, username);
            pst.setString(9,new String(password));
        
            AlertDialogInsert.display("Connection", "Data Inserted Successfully");
        } catch (SQLException ex) {
            Logger.getLogger(AddNewAuditorController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        finally{
        pst.execute();
        pst.close();
        }
       }
        tf_auditorId.clear();
        tf_auditorFname.clear();
        tf_auditorLname.clear();
        ta_auditorAdd.clear();
        tf_auditorNic.clear();
        datepicker_auditorDob.setValue(null);
        tf_auditorConnum.clear();
        tf_auditorUname.clear();
        pwf_auditorPassword.clear();
 }
   

 @FXML
    public void updateNewAuditorBtn(ActionEvent event) throws SQLException{
     try
     {
            String auditorid = tf_auditorId.getText();
            String firstname = tf_auditorFname.getText();
            String lastname = tf_auditorLname.getText();
            String address = ta_auditorAdd.getText();
            String nic = tf_auditorNic.getText();
            String dob = datepicker_auditorDob.getEditor().getText();
            String connum = tf_auditorConnum.getText();
            String username = tf_auditorUname.getText();
            String password = pwf_auditorPassword.getText();
            
    
    String sql = "update addauditorstable set auditorid='"+auditorid+"', firstname='"+firstname+"', lastname='"+lastname+"',"
            + "address='"+address+"',nic='"+nic+"',dob='"+dob+"',connum='"+connum+"',username='"+username+"', password='"+password+"' "
            + "where auditorid='"+auditorid+"'";
    pst = conn.prepareStatement(sql);
       AlertDialogInsert.display("Connection", "Data Updated Successfully");
    }
    catch(SQLException ex)
    {
     Logger.getLogger(UpdateCategoryController.class.getName()).log(Level.SEVERE, null, ex);
    
    }
     finally
     {
        pst.execute();
        pst.close();
     }
        tf_auditorId.clear();
        tf_auditorFname.clear();
        tf_auditorLname.clear();
        ta_auditorAdd.clear();
        tf_auditorNic.clear();
        datepicker_auditorDob.setValue(null);
        tf_date_inaddauditor.clear();
        tf_auditorConnum.clear();
        tf_auditorUname.clear();
        tf_date_inaddauditor.clear();
        pwf_auditorPassword.clear();
}
    
@FXML    
public void deleteNewAuditor(ActionEvent event){

    String sql = "delete from addauditorstable where auditorid = ?";
    try
    {
    pst = conn.prepareStatement(sql);
    pst.setString(1, tf_auditorId.getText());
    pst.execute();
     AlertDialogInsert.display("Connection", "Data Deleted Successfully");
    }
    catch(SQLException ex)
    {
     JOptionPane.showMessageDialog(null, ex);
    }
        tf_auditorId.clear();
        tf_auditorFname.clear();
        tf_auditorLname.clear();
        ta_auditorAdd.clear();
        tf_auditorNic.clear();
        datepicker_auditorDob.setValue(null);
        tf_date_inaddauditor.clear();
        tf_auditorConnum.clear();
        tf_auditorUname.clear();
}

@FXML
public void viewSummaryOfAuditors(ActionEvent event) throws IOException{
        Parent goAddNewAuditorByAdmin = FXMLLoader.load(getClass().getResource("AuditorSummary.fxml"));
        Scene addNewAuditorByAdmin = new Scene(goAddNewAuditorByAdmin);
    
        Stage showAddNewAuditorByAdmin= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showAddNewAuditorByAdmin.setScene(addNewAuditorByAdmin);
        showAddNewAuditorByAdmin.show();
}

@FXML
public void btnBackInAddNewAuditor(ActionEvent event) throws IOException{
        Parent goBackToSummaryViewFromAddNewAuditor = FXMLLoader.load(getClass().getResource("SummaryView.fxml"));
        Scene backToSummaryView = new Scene(goBackToSummaryViewFromAddNewAuditor);
    
        Stage showSummaryFromAddNewAuditor= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showSummaryFromAddNewAuditor.setScene(backToSummaryView);
        showSummaryFromAddNewAuditor.show();
}

 @FXML
    private void minimizeInAddNewAuditorBtn(ActionEvent event){
      MiniProject.getStageObj().setIconified(true);
    }
    //close btn
     @FXML
    private void closeInAddNewAuditorBtn(ActionEvent event){
    System.exit(0);
    }
}
