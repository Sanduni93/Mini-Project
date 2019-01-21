
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddCategoryController implements Initializable 
{
    @FXML
    private AnchorPane AddCategoryOverview;
    @FXML
    private Label lbl_addcatg;
    @FXML
    private Label lbl_catgid_in_addcatg;
    @FXML
    private Label lbl_catgname_in_addcatg;
    @FXML
    private Label lbl_brandname_in_addcatg;
    @FXML
    private Label lbl_supname_in_addcatg;
    @FXML
    private Label lbl_date_in_addcatg;
    @FXML
    private Label lbl_des_in_addcatg;
    @FXML
    private TextField tf_catgid_in_addcatg;
    @FXML
    private TextField tf_catgname_in_addcatg;
    @FXML
    private ComboBox<String> combo_brandname_in_addcatg;
    @FXML
    private ComboBox<String> combo_supname_in_addcatg;
    
    @FXML
    private Button btn_add_catg;
    @FXML
    private Button btn_back_in_addcatg;
    @FXML
    private Button btnCloseInAddCategory;
    @FXML
    private Button btnMinimizeInAddCategory;

    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList();
    private DBConnector db;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    @FXML
    private Label lbl_validCatgIdAddCategory;
    @FXML
    private Label lbl_validCatgnameInAddCategory;
    @FXML
    private ComboBox<String> cmb_aidInAddCtg;
    @FXML
    private TextField tf_dateInAddCatg;
   

   
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
          Date date = new Date (); 
         String date1 = date.toString();
         SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
         tf_dateInAddCatg.setText(sdf.format(date));
        db=new DBConnector();
        //add brand names for combobox
        try 
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addbrandstable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list1.add(rs.getString("brandname"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        combo_brandname_in_addcatg.setItems(list1);
         
    //add supplier's names for combobox
      try 
      {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addsuppliertable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list2.add(rs.getString("supname"));
        }
      }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        
         combo_supname_in_addcatg.setItems(list2);
         
         //add aid from auditors
         
           try 
      {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addauditorstable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list3.add(rs.getString("auditorid"));
        }
      }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        
         cmb_aidInAddCtg.setItems(list3);
         
    }

    @FXML
      public void addCategoryBtn(ActionEvent event) throws SQLException{

          boolean isCatgId = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_catgid_in_addcatg, lbl_validCatgIdAddCategory, "Enter Category ID");
          boolean isCatgName = Validation.TextFieldValidation.isTextFieldNotEmpty(tf_catgname_in_addcatg, lbl_validCatgnameInAddCategory, "Enter Category Name");
          if(isCatgId && isCatgName){
          Connection conn = db.Connect();
          String catgid = tf_catgid_in_addcatg.getText();
          String catgname = tf_catgname_in_addcatg.getText();
          String brandname = combo_brandname_in_addcatg.getValue();
          String supname = combo_supname_in_addcatg.getValue();
          String date= tf_dateInAddCatg.getText();
          String aid = cmb_aidInAddCtg.getValue();
       try
       {
           String sql = "INSERT INTO addcategorytable (categoryid,categoryname,brandname,supname,date,aid) VALUES (?,?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1,catgid);
           pst.setString(2,catgname);
           pst.setString(3,brandname);
           pst.setString(4,supname);
           pst.setString(5,date);
           pst.setString(6,aid);
           AlertDialogInsert.display("Connection", "Add "+tf_catgname_in_addcatg.getText()+" Successfully");
       }
       catch(Exception ex)
       {
           Logger.getLogger(AddSupplierController.class.getName()).log(Level.SEVERE, null, ex);
       }
        finally
       {
            pst.execute();
            pst.close();
       }
      }
        tf_catgid_in_addcatg.clear();
        tf_catgname_in_addcatg.clear();
        combo_brandname_in_addcatg.setValue(null);
        combo_supname_in_addcatg.setValue(null);
  
        cmb_aidInAddCtg.setValue(null);
}

    @FXML
    public void goBackToCategoryOverviewInAddCategory(ActionEvent event) throws IOException{
      Parent BackUpdateSupplier = FXMLLoader.load(getClass().getResource("CategoryOverview.fxml"));
      Scene category1 = new Scene(BackUpdateSupplier);
    
      Stage showCategory1= (Stage) ((Node) event.getSource()).getScene().getWindow();
      showCategory1.setScene(category1);
      showCategory1.show();
}
    
         //minimize btn
    @FXML
    private void minimizeInAddCategoryBtn(ActionEvent event){
      MiniProject.getStageObj().setIconified(true);
    }
    //close btn
     @FXML
    private void closeInAddCategoryBtn(ActionEvent event){
    System.exit(0);
    }
}
