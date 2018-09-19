package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DeleteSupplierController implements Initializable {

    @FXML
    private Label lbl_deletesupplier;
    @FXML
    private Label lbl_deletesupid;
    @FXML
    private Label lbl_deletesupname;
    @FXML
    private Label lbl_deletesupconnum;
    @FXML
    private Label lbl_deletesupadd;
    @FXML
    private Label lbl_deletesupdes;
    @FXML
    private TextField enter_supplierid_todelete;
    @FXML
    private Button btn_load_supdetails_todelete;
    @FXML
    private TextField tf_delete_supid;
    @FXML
    private TextField tf_delete_supname;
    @FXML
    private TextField tf_delete_supconnum;
    @FXML
    private TextField tf_delete_supadd;
    @FXML
    private TextField tf_delete_supdes;
    @FXML
    private Button btn_delete_suppliers;
  
    
    private DBConnector db;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
  public class Function1
  {
  @FXML
    public ResultSet find(String s)
    {
    
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst = conn.prepareStatement("select*from addsuppliertable where supid = ?");
        pst.setString(1, s);
        rs = pst.executeQuery();
        }
        catch(SQLException ex)
        {
         System.err.println("Error" + ex);
        }
         return rs;
    }
   }
  //load supplier details to delete
     @FXML
     public void btn_load_supdetails_todelete(ActionEvent event)
     {
       DeleteSupplierController.Function1 f = new DeleteSupplierController.Function1();
       ResultSet rs = null;
       String supid = "supid";
       String supname = "supname";
       String supconnum = "supconnum";
       String supadd = "supadd";
       String des = "des";
       rs=f.find(enter_supplierid_todelete.getText());
       try
       {
       if(rs.next())
       {
           tf_delete_supid.setText(rs.getString("supid"));
           tf_delete_supname.setText(rs.getString("supname"));
           tf_delete_supconnum.setText(rs.getString("supconnum"));
           tf_delete_supadd.setText(rs.getString("supadd"));
           tf_delete_supdes.setText(rs.getString("des"));
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
     //delete records
     
     @FXML
     public void btnDeleteSupplier(ActionEvent event){
         
         String sql="delete from addsuppliertable where supid=?";
         try
         {
         
        pst=conn.prepareStatement(sql);
        pst.setString(1,enter_supplierid_todelete.getText());
        pst.execute();
        JOptionPane.showMessageDialog(null,"Deleted");
             
         }
         catch(Exception e)
         {
          JOptionPane.showMessageDialog(null, e);
         }    
     
     }
     
      @FXML
    public void btnBackDeleteSupplier(ActionEvent event) throws IOException
    {
   Parent BackDeleteSupplier = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
    Scene supplier2 = new Scene(BackDeleteSupplier);
    
    Stage showSupplier2= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplier2.setScene(supplier2);
    showSupplier2.show();
    
    }

     
  
  }


