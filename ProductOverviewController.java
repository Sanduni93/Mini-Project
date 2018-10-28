
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ProductOverviewController implements Initializable 
{
    @FXML
    private AnchorPane ProductOverview;
    @FXML
    private Label lbl_productinfo;
    @FXML
    private TableView<ModelTableProduct> tbl_productinfo;
    @FXML
    private TableColumn<ModelTableProduct,String> col_productid;
    @FXML
    private TableColumn<ModelTableProduct,String> col_productname;
    @FXML
    private TableColumn<ModelTableProduct,String> col_quantity;
    @FXML
    private TableColumn<ModelTableProduct,String> col_pprice;
    @FXML
    private TableColumn<ModelTableProduct,String> col_sprice;
    @FXML
    private TableColumn<ModelTableProduct,String> col_supname_inproduct;
    @FXML
    private TableColumn<ModelTableProduct,String> col_brand_inproduct;
    @FXML
    private TableColumn<ModelTableProduct,String> col_category_inproduct;
    @FXML
    private TableColumn<ModelTableProduct,String> col_rma;
    @FXML
    private TableColumn<ModelTableProduct,String> col_date_inproduct;
    @FXML
    private Button btn_load_productdetails;
    @FXML
    private Button btn_addproduct;
    @FXML
    private Button btn_updateproduct;
    @FXML
    private Button btn_deleteproduct;
    @FXML
    private Button btn_back_in_product;

    private DBConnector db;
    private PreparedStatement pst=null;
        private ObservableList<ModelTableProduct> data;
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       db = new DBConnector();
    }  
    
    //go to add product
    
    @FXML
    public void addProductBtn(ActionEvent event) throws IOException
    {
       Parent goProductCategory = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addproduct = new Scene(goProductCategory);
    
        Stage showAddProduct= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showAddProduct.setScene(addproduct);
        showAddProduct.show();
    }
    
      //go to update product
    
     @FXML
    public void updateProductBtn(ActionEvent event) throws IOException
    {
       Parent goUpdateProductCategory = FXMLLoader.load(getClass().getResource("UpdateProduct.fxml"));
        Scene updateproduct = new Scene(goUpdateProductCategory);
    
        Stage showUpdateProduct= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showUpdateProduct.setScene(updateproduct);
        showUpdateProduct.show();
    }
    
        //go to delete product
    
    @FXML
    public void deleteProductBtn(ActionEvent event) throws IOException
    {
       Parent goDeleteProductCategory = FXMLLoader.load(getClass().getResource("DeleteProduct.fxml"));
        Scene deleteproduct = new Scene(goDeleteProductCategory);
    
        Stage showDeleteProduct= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showDeleteProduct.setScene(deleteproduct);
        showDeleteProduct.show();
    }
    
           //go backto auditor view
    
     @FXML
    public void btnGoBackToSupplierOverviewFromProductView(ActionEvent event) throws IOException
    {
    Parent BackProductOverview = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
    Scene supplierfromproduct = new Scene(BackProductOverview);
    
    Stage showSupplierFromProduct= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplierFromProduct.setScene(supplierfromproduct);
    showSupplierFromProduct.show();
    }
    
    //load data into the table from dbtable
    
    @FXML
    private void loadProductData(ActionEvent event){
    
        try {
            Connection conn = db.Connect();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("select*from addproducttable");
            
            while (rs.next()) 
            {
                data.add(new ModelTableProduct(rs.getString("productid"),rs.getString("productname"), rs.getString("quantity"),
                        rs.getString("pprice"),rs.getString("sprice"),rs.getString("supname"),rs.getString("brand"),rs.getString("category"),
                        rs.getString("rma"),rs.getString("date")));
            }
        } catch (SQLException ex) {
           System.err.println("Error" + ex);
        }
    //set values for the table
    col_productid.setCellValueFactory(new PropertyValueFactory<>("productid"));
    col_productname.setCellValueFactory(new PropertyValueFactory<>("productname"));
    col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    col_pprice.setCellValueFactory(new PropertyValueFactory<>("pprice"));
    col_sprice.setCellValueFactory(new PropertyValueFactory<>("sprice"));
    col_supname_inproduct.setCellValueFactory(new PropertyValueFactory<>("supname"));
    col_brand_inproduct.setCellValueFactory(new PropertyValueFactory<>("brand"));
    col_category_inproduct.setCellValueFactory(new PropertyValueFactory<>("category"));
    col_rma.setCellValueFactory(new PropertyValueFactory<>("rma"));
    col_date_inproduct.setCellValueFactory(new PropertyValueFactory<>("date"));
    
    tbl_productinfo.setItems(data);
    
    }
}
