
package main;


import Dialog.AlertDialogInsert;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import static javafx.stage.StageStyle.UNDECORATED;


public class AddBrandController implements Initializable 
{
    @FXML
    private AnchorPane AddBrandOverview;
    @FXML
    private Label lbl_addbrand;
    @FXML
    private Label lbl_brandid;
    @FXML
    private Label lbl_brandname;
    @FXML
    private Label lbl_supnameinaddbrand;
    @FXML
    private Label lbl_aidnaddbrand;
    @FXML
    private Label lbl_date;
    @FXML
    private TextField tf_brandid;
    @FXML
    private TextField tf_brandname;
    @FXML
    private TextField tf_dateInAddBrand;
    @FXML
    private ComboBox<String> combo_supname;
   
    
    @FXML
    private ComboBox<String> cmb_auditorIdAddBrand;
    @FXML
    private Button btn_add_brand;
    @FXML
    private Button btn_back_in_addbrand;
    @FXML
    private Button btnMinimizeInAddBrand;
    @FXML
    private Button btnCloseInAddBrand;
    @FXML
    private Label lbl_validateBrandIdInAddBrand;
    @FXML
    private Label lbl_validateBrandNameInAddBrand;
    @FXML
    private Label lbl_validateSupplierInAddBrand;

ObservableList<String> list1 = FXCollections.observableArrayList();
ObservableList<String> list2 = FXCollections.observableArrayList();
        
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
         tf_dateInAddBrand.setText(sdf.format(date));
        db=new DBConnector();
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addsuppliertable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list1.add(rs.getString("supname"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        combo_supname.setItems(list1);
        
        
            
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addauditorstable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list2.add(rs.getString("auditorid"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        cmb_auditorIdAddBrand.setItems(list2);
        
        
    }
      //add new brands
     @FXML
     public void btnAddBrand(ActionEvent event) throws SQLException, IOException
    {
       boolean isBrandIDEmpty = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_brandid, lbl_validateBrandIdInAddBrand, "Brand Id Is required");
       boolean isBrandNameEmpty = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_brandname, lbl_validateBrandNameInAddBrand, "Brand Name Is required");
      
      
      if(isBrandIDEmpty && isBrandNameEmpty ){
      Connection conn = db.Connect();
          String bid = tf_brandid.getText();
          String bname = tf_brandname.getText();
          String supname = combo_supname.getSelectionModel().getSelectedItem();
          String newdate = tf_dateInAddBrand.getText();
          String aid = cmb_auditorIdAddBrand.getSelectionModel().getSelectedItem();
           try
           
           {
           String sql = "INSERT INTO addbrandstable (brandid,brandname,supname,date,aid) VALUES (?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1,bid);
           pst.setString(2,bname);
           pst.setString(3,supname);
           pst.setString(4,newdate);
           pst.setString(5,aid);
           
           AlertDialogInsert.display("Connection", "Data Inserted Successfully");
          }
       catch(Exception ex)
       {
          
            Logger.getLogger(AddBrandController.class.getName()).log(Level.SEVERE, null, ex);
       }
        finally 
           {
            pst.execute();
            pst.close();
           }
      }
        tf_brandid.clear();
        tf_brandname.clear();
        combo_supname.setValue(null);
        cmb_auditorIdAddBrand.setValue(null);
    }
     //go back to brand overview
    @FXML
    public void btnGoBackToBrandOverviewInAddBrand(ActionEvent event) throws IOException
    {
       
    Parent BackUpdateSupplier = FXMLLoader.load(getClass().getResource("BrandOverview.fxml"));
    Scene brand1 = new Scene(BackUpdateSupplier);
    
    Stage showBrand1= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showBrand1.setScene(brand1);
    showBrand1.show();
    
    }
    //minimize btn
    @FXML
    private void minimizeInAddBrandBtn(ActionEvent event){
      MiniProject.getStageObj().setIconified(true);
    }
    //close btn
     @FXML
    private void closeInAddBrandBtn(ActionEvent event){
    System.exit(0);
    }

}
   
    
    
  
  

    
   
    
    

