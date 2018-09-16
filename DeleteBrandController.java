
package main;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;


public class DeleteBrandController implements Initializable {

    @FXML
    private AnchorPane DeleteBrandOverview;
    @FXML
    private Label lbl_deletebrand;
    @FXML
    private Label lbl_brandid_indeletebrand;
    @FXML
    private Label lbl_brandname_indeletebrand;
    @FXML
    private Label lbl_supname_indeletebrand;
    @FXML
    private Label lbl_date_indeletebrand;
    @FXML
    private Label lbl_des_indeletebrand;
    @FXML
    private TextField tf_brandname_indeletebrand;
    @FXML
    private TextField tf_brandid_indeletebrand;
    @FXML
    private TextField tf_des_indeletebrand;
    @FXML
    private TextField tf_enter_brandid_todelete;
    @FXML
    private ComboBox<String> combo_supname_indeletebrand;
    @FXML
    private DatePicker datepicker_date_indeletebrand;
    @FXML
    private Button btn_load_branddetails_todelete;
    @FXML
    private Button btn_delete_brand;
    @FXML
    private Button btn_back_in_deletebrand;
    
    Connection conn = null;
     ResultSet rs = null;
    PreparedStatement pst = null;
    private DBConnector db;
    ObservableList<String> list = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) 
{
    //set combo box values
        db = new DBConnector();
        
           try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addbrandstable");
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
        combo_supname_indeletebrand.setItems(list);

}
    //load brand details to delete
 public class Function2
  {
  @FXML
    public ResultSet find(String s)
    {
    
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst = conn.prepareStatement("select*from addbrandstable where brandid = ?");
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
     public void btn_load_branddetails_todelete(ActionEvent event)
     {
       DeleteBrandController.Function2 f = new DeleteBrandController.Function2();
       ResultSet rs = null;
       String brandid= "brandid";
       String brandname = "brandname";
       String supname = "supname";
       String date = "date";
       String des = "des";
       rs=f.find(tf_enter_brandid_todelete.getText());
       try
       {
       if(rs.next())
       {
                tf_brandid_indeletebrand.setText(rs.getString("brandid")); 
                tf_brandname_indeletebrand.setText(rs.getString("brandname"));
                combo_supname_indeletebrand.setValue(rs.getString("supname"));
               
                tf_des_indeletebrand.setText(rs.getString("des"));
              
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
     
     //detele brand details
        @FXML
     public void btnDeleteBrand(ActionEvent event){
         
         String sql="delete from addbrandstable where brandid=?";
         try
         {
         
        pst=conn.prepareStatement(sql);
        pst.setString(1,tf_enter_brandid_todelete.getText());
        pst.execute();
        JOptionPane.showMessageDialog(null,"Deleted");
             
         }
         catch(Exception e)
         {
          JOptionPane.showMessageDialog(null, e);
         }    
     
     }

}
    
    
    