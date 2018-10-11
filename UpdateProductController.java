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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class UpdateProductController implements Initializable 
{

    @FXML
    private Label lbl_update_product;
    @FXML
    private Label lbl_supname_in_updateproduct;
    @FXML
    private Label lbl_brand_in_updateproduct;
    @FXML
    private Label lbl_category_in_updateproduct;
    @FXML
    private Label lbl_rma_in_updateproduct;
    @FXML
    private Label lbl_date_in_updateproduct;
    @FXML
    private Label lbl_productid_in_updateproduct;
    @FXML
    private Label lbl_productname_in_updateproduct;
    @FXML
    private Label lbl_pprice_in_updateproduct;
    @FXML
    private Label lbl_quantity_in_updateproduct;
    @FXML
    private Label lbl_sprice_in_updateproduct;
    @FXML
    private TextField  tf_enter_productid_toupdateproducts;
    @FXML
    private TextField tf_productid_in_updatesupplier;
    @FXML
    private TextField tf_productname_in_updatesupplier;
    @FXML
    private TextField tf_quantity_in_updatesupplier;
    @FXML
    private TextField tf_pprice_in_updatesupplier;
    @FXML
    private TextField tf_sprice_in_updatesupplier;
    @FXML
    private TextField tf_rma_in_updateproduct;
    @FXML
    private ComboBox<String> combo_supname_in_updateproduct;
    @FXML
    private ComboBox<String> combo_brand_in_updateproduct;
    @FXML
    private ComboBox<String> combo_category_in_updateproduct;
    @FXML
    private DatePicker datepicker_date_in_updateproduct;
    @FXML
    private Button btn_updateproduct;
    @FXML
    private Button btn_back_in_updateproduct;

    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList();
    private DBConnector db;
    Connection conn;
    PreparedStatement pst ;
    ResultSet rs ;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
     db = new DBConnector();
     //add suplier's details to combobox
     try
     {
    conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk","root","");
    pst = conn.prepareStatement("select*from addsuppliertable");
    rs = pst.executeQuery();
    while(rs.next())
    {
    list1.add(rs.getString("supname"));
    }
    }
    catch(SQLException e)
    {
         System.out.println(e);
    }
    combo_supname_in_updateproduct.setItems(list1);

    //add brand names for combobox
    try
    {
    conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk","root","");
    pst = conn.prepareStatement("select*from addbrandstable");
    rs = pst.executeQuery();
    
    while(rs.next())
    {
    list2.add(rs.getString("brandname"));
    }
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    combo_brand_in_updateproduct.setItems(list2);
    
    //add category for combobox
    try
    {
    conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk","root","");
    pst = conn.prepareStatement("select*from addcategorytable");
    rs = pst.executeQuery();
    while(rs.next())
    {
    list3.add(rs.getString("categoryname"));
    }
    }
    catch(SQLException e)
    {
        System.out.println(e);
    }
    combo_category_in_updateproduct.setItems(list3);
    }
    
    //load product details
    public class Function 
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
       System.err.println("Error" + ex);
       }
            return rs;
        }
    }
    @FXML
    public void btnLoadProductDetailsToUpdate(ActionEvent event)
    {
    UpdateProductController.Function f = new UpdateProductController.Function();
    String productid = "productid";
    String productname = "productname";
    String quantity = "quantity";
    String pprice = "pprice";
    String sprice = "sprice";
    String supname = "supname";
    String brandname= "brandname";
    String categoryname = "categoryname";
    String rma = "rma";
    String date = "date";
    
    rs = f.find(tf_enter_productid_toupdateproducts.getText());
    
    try
    {
      if(rs.next())
      {
      tf_productid_in_updatesupplier.setText(rs.getString("productid"));
      tf_productname_in_updatesupplier.setText(rs.getString("productname"));
      tf_quantity_in_updatesupplier.setText(rs.getString("quantity"));
      tf_pprice_in_updatesupplier.setText(rs.getString("pprice"));
      tf_sprice_in_updatesupplier.setText(rs.getString("sprice"));
      combo_supname_in_updateproduct.setValue(rs.getString("supname"));
      combo_brand_in_updateproduct.setValue(rs.getString("brand"));
      combo_category_in_updateproduct.setValue(rs.getString("category"));
      tf_rma_in_updateproduct.setText(rs.getString("rma"));
      }
      else
      {
       JOptionPane.showMessageDialog(null, "No data for this productid");
      }
    
    }
    catch(SQLException ex)
    {
       JOptionPane.showMessageDialog(null, ex.getMessage());
    
    }
    }
    //update product
    @FXML
    public void btnUpdateProduct(ActionEvent event) throws SQLException
    {
        try
        {
            String productid = tf_productid_in_updatesupplier.getText();
            String productname = tf_productname_in_updatesupplier.getText();
            String quantity = tf_quantity_in_updatesupplier.getText();
            String pprice = tf_pprice_in_updatesupplier.getText();
            String sprice = tf_sprice_in_updatesupplier.getText();
            String supname = combo_supname_in_updateproduct.getSelectionModel().getSelectedItem();
            String brandname = combo_brand_in_updateproduct.getSelectionModel().getSelectedItem();
            String categoryname = combo_category_in_updateproduct.getSelectionModel().getSelectedItem();
            String rma = tf_rma_in_updateproduct.getText();
            String date = ((TextField)datepicker_date_in_updateproduct.getEditor()).getText();
            
            String sql = "update addproducttable set productid='"+productid+"',productname='"+productname+"',quantity='"+quantity+"',"
                    + "pprice='"+pprice+"',sprice='"+sprice+"',supname='"+supname+"',brand='"+brandname+"',category='"+categoryname+"',"
                    + "rma='"+rma+"',date='"+date+"' where productid='"+productid+"'";
            pst = conn.prepareStatement(sql);
            AlertDialogInsert.display("Connection", "Data Updated Successfully");
        }
        catch (SQLException ex)
        {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally
        {
        pst.execute();
        pst.close();
        }
    }
    
    //go back to product overview
     public void btnBackInUpdateProduct(ActionEvent event) throws IOException
   {
     Parent productUpdateGoBacj = FXMLLoader.load(getClass().getResource("ProductOverview.fxml"));
     Scene product2 = new Scene(productUpdateGoBacj);
     Stage showProduct2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
     showProduct2.setScene(product2);
     showProduct2.show();
     
    }
}
       
    
    
    


