
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


public class AddBrandController implements Initializable {

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
    private Label lbl_date;
    @FXML
    private Label lbl_desinaddbrand;
    @FXML
    private TextField tf_brandid;
    @FXML
    private TextField tf_brandname;
    @FXML
    private ComboBox<String> combo_supname;
    @FXML
    private DatePicker datepicker_date;
    @FXML
    private TextField tf_desinaddbrand;
    @FXML
    private Button btn_add_brand;
    @FXML
    private Button btn_back_in_addbrand;

ObservableList<String> list = FXCollections.observableArrayList();
private DBConnector db;
Connection conn=null;
PreparedStatement pst=null;
ResultSet rs = null;


@Override
    public void initialize(URL url, ResourceBundle rb)
    {
          
       db=new DBConnector();
           try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addsuppliertable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list.add(rs.getString("supname"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        combo_supname.setItems(list);
    
     
    }
      //add new brands
 @FXML
    public void btnAddBrand(ActionEvent event) throws SQLException, IOException
           
    {
          Connection conn = db.Connect();
          String bid = tf_brandid.getText();
          String bname = tf_brandname.getText();
          String supname = combo_supname.getValue();
          String newdate = datepicker_date.getEditor().getText();
          String des = tf_desinaddbrand.getText();
       try{
           String sql = "INSERT INTO addbrandstable (brandid,brandname,supname,date,des) VALUES (?,?,?,?,?)";
           pst = conn.prepareStatement(sql);
           pst.setString(1,bid);
           pst.setString(2,bname);
           pst.setString(3,supname);
           pst.setString(4,newdate);
           pst.setString(5,des);
           
           AlertDialogInsert.display("Connection", "Data Inserted Successfully");
          }
       catch(Exception ex){
           Logger.getLogger(AddSupplierController.class.getName()).log(Level.SEVERE, null, ex);
         }
        finally {
            pst.execute();
            pst.close();
        }
        tf_brandid.clear();
        tf_brandname.clear();
        tf_desinaddbrand.clear();
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

}
   
    
    
  
  

    
   
    
    

