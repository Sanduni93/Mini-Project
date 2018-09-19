package main;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.beans.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuditorViewController implements Initializable {

    @FXML
    private AnchorPane auditorView;
    @FXML
    private Button btn_products_auditorView;
    @FXML
    private TableView<ModelTableSuppliers> tbl_supinfo;
    @FXML
    private TableColumn<ModelTableSuppliers, String> col_supid;
    @FXML
    private TableColumn<ModelTableSuppliers, String> col_supname;
    @FXML
    private TableColumn<ModelTableSuppliers, String> col_supconnum;
    @FXML
    private TableColumn<ModelTableSuppliers, String> col_supadd;
    @FXML
    private TableColumn<ModelTableSuppliers, String> col_des;
    @FXML
    private Button btn_add_supplieroverview;
    @FXML
    private Button btn_delete_supplieroverview;
    @FXML
    private Button btn_update_supplieroverview;
    @FXML
    private Button btn_supplier_auditorView;
    @FXML
    private Button btn_brand_auditorView;
    @FXML
    private Button btn_category_auditorView;
    @FXML
    private Button btn_load_supplier_details;

//go to the brand overview
    @FXML
    private void btn_brand_auditorView(ActionEvent event) throws IOException {

        Parent GoBrandOverview = FXMLLoader.load(getClass().getResource("BrandOverview.fxml"));
        Scene brand = new Scene(GoBrandOverview);

        Stage showBrand = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showBrand.setScene(brand);
        showBrand.show();
    }

//go to the category overview
    @FXML
    private void btn_categoryoverview(ActionEvent event) throws IOException {

        Parent GoCategoryOverview = FXMLLoader.load(getClass().getResource("CategoryOverview.fxml"));
        Scene category = new Scene(GoCategoryOverview);

        Stage showCategory = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showCategory.setScene(category);
        showCategory.show();
    }

//go to the product overview
    @FXML
    private void btn_products_auditorView(ActionEvent event) throws IOException {
        Parent GoProductOverview = FXMLLoader.load(getClass().getResource("Products.fxml"));
        Scene product = new Scene(GoProductOverview);

        Stage showProduct = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showProduct.setScene(product);
        showProduct.show();

    }

//adding supplier window
    @FXML
    private void btn_add_supplieroverview(ActionEvent event) throws IOException {
        Parent AddSupplierOverview = FXMLLoader.load(getClass().getResource("AddSupplier.fxml"));
        Scene supplier = new Scene(AddSupplierOverview);

        Stage showSupplier = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showSupplier.setScene(supplier);
        showSupplier.show();

    }
    //update supplier window
    @FXML
    private void btn_update_supplieroverview(ActionEvent event) throws IOException {
        Parent UpdateSupplierOverview = FXMLLoader.load(getClass().getResource("UpdateSupplier.fxml"));
        Scene supplier1 = new Scene(UpdateSupplierOverview);

        Stage showSupplier1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showSupplier1.setScene(supplier1);
        showSupplier1.show();

    }
    //delete supplier overview
    @FXML
    private void btn_delete_supplieroverview(ActionEvent event) throws IOException{
    
    Parent DeleteSupplierOverview = FXMLLoader.load(getClass().getResource("DeleteSupplier.fxml"));
    Scene supplier2 = new Scene(DeleteSupplierOverview);
    
    Stage showSupplier2= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplier2.setScene(supplier2);
    showSupplier2.show();
    
    }

    private ObservableList<ModelTableSuppliers> data;
    private DBConnector db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBConnector();

    }
    //set values for table

    @FXML
    private void btn_load_supplier_details(ActionEvent event) {
        try {

            Connection conn = db.Connect();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("select*from addsuppliertable");
        
            while (rs.next()) {
                data.add(new ModelTableSuppliers(rs.getString("supid"),rs.getString("supname"), rs.getString("supconnum"),
                rs.getString("supadd"),rs.getString("des")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);

        }

        //set values for table
        col_supid.setCellValueFactory(new PropertyValueFactory<>("supid"));
        col_supname.setCellValueFactory(new PropertyValueFactory<>("supname"));
        col_supconnum.setCellValueFactory(new PropertyValueFactory<>("supconnum"));
        col_supadd.setCellValueFactory(new PropertyValueFactory("supadd"));
        col_des.setCellValueFactory(new PropertyValueFactory("des"));

        tbl_supinfo.setItems(data);

    }
    
   

    }
