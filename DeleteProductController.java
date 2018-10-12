
package main;

import java.io.IOException;
import java.net.URL;



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
import javafx.stage.Stage;
import javax.swing.JOptionPane;



public class DeleteProductController implements Initializable {

    @FXML
    private Label lbl_supname_in_deleteproduct;
    @FXML
    private Label lbl_brand_in_deleteproduct;
    @FXML
    private Label lbl_category_in_deleteproduct;
    @FXML
    private Label lbl_rma_in_deleteproduct;
    @FXML
    private Label lbl_date_in_deleteproduct;
    @FXML
    private Label lbl_productid_in_deleteproduct;
    @FXML
    private Label lbl_productname_in_deleteproduct;
    @FXML
    private Label lbl_quantity_in_deleteproduct;
    @FXML
    private Label lbl_pprice_in_deleteproduct;
    @FXML
    private Label lbl_sprice_in_deleteproduct;
    @FXML
    private Label lbl_delete_product;
    @FXML
    private TextField tf_productid_in_deleteproduct;
    @FXML
    private TextField tf_productname_in_deleteproduct;
    @FXML
    private TextField tf_quantity_in_deleteproduct;
    @FXML
    private TextField tf_pprice_in_deleteproduct;
    @FXML
    private TextField tf_sprice_in_deleteproduct;
    @FXML
    private TextField tf_rma_in_deleteproduct;
    @FXML
    private ComboBox<String> combo_supname_in_deleteproduct;
    @FXML
    private ComboBox<String> combo_brand_in_deleteproduct;
    @FXML
    private ComboBox<String> combo_category_in_deleteproduct;
    @FXML
    private DatePicker datepicker_date_in_deleteproduct;
    @FXML
    private Button btn_load_productdetails_todelete;
    @FXML
    private Button btn_deleteproduct;
    @FXML
    private Button btn_back_in_deleteproduct;
    @FXML
    private TextField tf_enter_productid_todeleteproducts;
    @FXML
    private TextField tf_date_in_deleteproduct;

    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList();
    private DBConnector db;
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs = null;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    db = new DBConnector();
     
         
    //add supplier's names for combobox
      try {
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
        
         combo_supname_in_deleteproduct.setItems(list1); 
         
         //add brand names for combobox
         try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addbrandstable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list2.add(rs.getString("brandname"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        
         combo_brand_in_deleteproduct.setItems(list2);
         
         //add brand names for combobox
         try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addcategorytable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list3.add(rs.getString("categoryname"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        
         combo_category_in_deleteproduct.setItems(list3);
    }
    
    //load product details to delete
public class Function3
  {
  @FXML
    public ResultSet find(String s)
    {
    
        try
        {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst = conn.prepareStatement("select*from addproducttable where productid = ?");
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
    public void btnLoadProductDetailsToDelete(ActionEvent event) {
      DeleteProductController.Function3 f3 = new DeleteProductController.Function3();
      
      rs = f3.find(tf_enter_productid_todeleteproducts.getText());
      
      try{
          if(rs.next()){
             tf_productid_in_deleteproduct.setText(rs.getString("productid"));
             tf_productname_in_deleteproduct.setText(rs.getString("productname"));
             tf_quantity_in_deleteproduct.setText(rs.getString("quantity"));
             tf_pprice_in_deleteproduct.setText(rs.getString("pprice"));
             tf_sprice_in_deleteproduct.setText(rs.getString("sprice"));
             combo_supname_in_deleteproduct.setValue(rs.getString("supname"));
             combo_brand_in_deleteproduct.setValue(rs.getString("brand"));
             combo_category_in_deleteproduct.setValue(rs.getString("category"));
             tf_rma_in_deleteproduct.setText(rs.getString("rma"));
             tf_date_in_deleteproduct.setText(rs.getString("date"));
            }
          else{
          JOptionPane.showMessageDialog(null, "No data for this index");
          }
        }
      catch(SQLException ex){
      JOptionPane.showMessageDialog(null,ex.getMessage());
      }
   }
//delete brand
    @FXML
    public void btnDeleteProduct(ActionEvent event) throws SQLException{
     
        String sql = "delete from addproducttable where productid = ?";
        try{
        pst=conn.prepareStatement(sql);
        pst.setString(1,tf_enter_productid_todeleteproducts.getText());
        pst.execute();
        JOptionPane.showMessageDialog(null,"Deleted");
        
        }
        catch(SQLException ex){
         JOptionPane.showMessageDialog(null, ex);
        
        }
        finally{
        pst.execute();
        pst.close();
        }
        tf_enter_productid_todeleteproducts.clear();
        tf_productid_in_deleteproduct.clear();
        tf_productname_in_deleteproduct.clear();
        tf_quantity_in_deleteproduct.clear();
        tf_pprice_in_deleteproduct.clear();
        tf_sprice_in_deleteproduct.clear();
        combo_supname_in_deleteproduct.setValue(null);
        combo_brand_in_deleteproduct.setValue(null);
        combo_category_in_deleteproduct.setValue(null);
        tf_rma_in_deleteproduct.clear();
        tf_date_in_deleteproduct.clear();
        
    }
    
    @FXML
    public void btnBackInDeleteProduct(ActionEvent event) throws IOException{
      
        Parent deleteProductGoBack = FXMLLoader.load(getClass().getResource("ProductOverview.fxml"));
        Scene product3 = new Scene(deleteProductGoBack);
        
        Stage showProduct3 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showProduct3.setScene(product3);
        showProduct3.show();
        
        
    }
    

    
    
}
