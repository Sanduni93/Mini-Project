
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javax.swing.JOptionPane;


public class DeleteCategoryController implements Initializable {

    @FXML
    private AnchorPane DeleteCategoryOverview;
    @FXML
    private Label lbl_deletcatg;
    @FXML
    private Label lbl_catgid_indeletecatg;
    @FXML
    private Label lbl_catgname_indeletecatg;
    @FXML
    private Label lbl_brand_indeletecatg;
    @FXML
    private Label lbl_supname_indeletecatg;
    @FXML
    private Label lbl_date_indeletecatg;
    @FXML
    private Label lbl_des_indeletecatg;
    @FXML
    private TextField tf_entercatgid_todelete;
    @FXML
    private TextField tf_catgid_indeletecatg;
    @FXML
    private TextField tf_catgname_in_deletecatg;
    @FXML
    private TextField tf_des_in_deletecatg;
    @FXML
    private ComboBox<String> combo_brandname_in_deletecatg;
    @FXML
    private ComboBox<String> combo_supname_in_deletecatg;
    @FXML
    private DatePicker datepicker_date_in_deletecatg;
    @FXML
    private Button btn_load_catgdetails_todelete;
    @FXML
    private Button btn_delete_catg;
    @FXML
    private Button btn_back_in_deletecatg;

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
        pst=conn.prepareStatement("select*from addcategorytable");
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
        
         combo_brandname_in_deletecatg.setItems(list1);
         
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
        
         combo_supname_in_deletecatg.setItems(list2); 
    }
//load category details to delete
public class Function2
  {
  @FXML
    public ResultSet find(String s)
    {
    
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst = conn.prepareStatement("select*from addcategorytable where categoryid = ?");
        pst.setString(1, s);
        rs = pst.executeQuery();
        }
        catch(SQLException ex)
        {
        JOptionPane.showMessageDialog(null,ex.getMessage());
        }
         return rs;
    }
   }

@FXML
     public void btn_load_categorydetails_todelete(ActionEvent event)
     {
       DeleteCategoryController.Function2 f = new DeleteCategoryController.Function2();
       ResultSet rs = null;
       String catgid = "categoryid";
        String catgname = "categoryname";
        String brandname = "brandname";
        String supname = "supname";
        String date = "date" ;
        String des = "des";
       rs=f.find(tf_entercatgid_todelete.getText());
       try
       {
       if(rs.next())
       {
                tf_catgid_indeletecatg.setText(rs.getString("categoryid")); 
                tf_catgname_in_deletecatg.setText(rs.getString("categoryname"));
                combo_brandname_in_deletecatg.setValue(rs.getString("brandname"));
                combo_supname_in_deletecatg.setValue(rs.getString("supname"));
               
                tf_des_in_deletecatg.setText(rs.getString("des"));
              
       }
        else
       {
        JOptionPane.showMessageDialog(null,"No data for this index");
           
       }
      }
       catch(SQLException ex)
      {
        JOptionPane.showMessageDialog(null,ex.getMessage());
      }
      }
     
     //delete brand
     
     @FXML
      public void btnDeleteBrand(ActionEvent event){
         
         String sql="delete from addcategorytable where categoryid=?";
         try
         {
         
        pst=conn.prepareStatement(sql);
        pst.setString(1,tf_entercatgid_todelete.getText());
        pst.execute();
        JOptionPane.showMessageDialog(null,"Deleted");
             
         }
         catch(Exception e)
         {
          JOptionPane.showMessageDialog(null, e);
         }    
     
     }
      
      //go backto category overview
       @FXML
    public void btnGoBackToCategoryOverviewInDeleteCatg(ActionEvent event) throws IOException
    {
    Parent catgGoBack = FXMLLoader.load(getClass().getResource("CategoryOverview.fxml"));
    Scene category3= new Scene(catgGoBack);
    
    Stage showCategory3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
    showCategory3.setScene(category3);
    showCategory3.show();
    }
     


    
    
}
