
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
     public void btnDeleteBrand(ActionEvent event) throws SQLException{
         
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
         finally {
            pst.execute();
            pst.close();
        }
         tf_enter_brandid_todelete.clear();
        tf_brandid_indeletebrand.clear();
        tf_brandname_indeletebrand.clear();
        datepicker_date_indeletebrand.setValue(null);
         tf_des_indeletebrand.clear();
     
     }
     
     //go backto brands overview
     @FXML
     public void btn_back_in_deletebrand(ActionEvent event) throws IOException
     {
     
         Parent goBackToBrand = FXMLLoader.load(getClass().getResource("BrandOverview.fxml"));
         Scene goBrand = new Scene(goBackToBrand);
         
         Stage setGoBrand = (Stage) ((Node) event.getSource()).getScene().getWindow();
         setGoBrand.setScene(goBrand);
         setGoBrand.show();
         
     
     }
    
}
    
    
    
