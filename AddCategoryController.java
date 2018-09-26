
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class AddCategoryController implements Initializable {

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
    private DatePicker datepicker_date_in_addcatg;
    @FXML
    private TextField tf_des_in_addcatg;
    @FXML
    private Button btn_add_catg;
    @FXML
    private Button btn_back_in_addcatg;

    ObservableList<String> list1 = FXCollections.observableArrayList();
     ObservableList<String> list2 = FXCollections.observableArrayList();
    private DBConnector db;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs = null;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    db=new DBConnector();
    //add brand names for combobox
         try {
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
      try {
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
        
}

@FXML
public void addCategoryBtn(ActionEvent event) throws SQLException{

 Connection conn = db.Connect();
          String catgid = tf_catgid_in_addcatg.getText();
          String catgname = tf_catgname_in_addcatg.getText();
          String brandname = combo_brandname_in_addcatg.getValue();
          String supname = combo_supname_in_addcatg.getValue();
          String date= datepicker_date_in_addcatg.getEditor().getText();
          String des = tf_des_in_addcatg.getText();
       try{
           String sql = "INSERT INTO addcategorytable (categoryid,categoryname,brandname,supname,date,des) VALUES (?,?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1,catgid);
           pst.setString(2,catgname);
           pst.setString(3,brandname);
           pst.setString(4,supname);
           pst.setString(5,date);
           pst.setString(6,des);
           AlertDialogInsert.display("Connection", "Data Inserted Successfully");
          }
       catch(Exception ex){
           Logger.getLogger(AddSupplierController.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally {
            pst.execute();
            pst.close();
        }
        tf_catgid_in_addcatg.clear();
        tf_catgname_in_addcatg.clear();
        tf_des_in_addcatg.clear();
}

@FXML
public void goBackToCategoryOverviewInAddCategory(ActionEvent event) throws IOException{
Parent BackUpdateSupplier = FXMLLoader.load(getClass().getResource("CategoryOverview.fxml"));
    Scene category1 = new Scene(BackUpdateSupplier);
    
    Stage showCategory1= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showCategory1.setScene(category1);
    showCategory1.show();
}
}
