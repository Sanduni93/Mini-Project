
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BrandOverviewController implements Initializable {

    @FXML
    private AnchorPane GoBrandOverview;
    @FXML
    private Label lbl_brandinfo;
    @FXML
    private TableView<ModelTableBrands> tbl_brandsinfo;
    @FXML
    private TableColumn<ModelTableBrands, String>  col_brandid_inbrands;
    @FXML
    private TableColumn<ModelTableBrands, String> col_brandname;
    @FXML
    private TableColumn<ModelTableBrands, String> col_supplier_inbrands;
    @FXML
    private TableColumn<ModelTableBrands, String> col_date_inbrands;
    @FXML
    private TableColumn<ModelTableBrands, String> col_des_inbrands;
    @FXML
    private Button btn_load_brands;
    @FXML
    private Button btn_addbrand;
    @FXML
    private Button btn_updatebrand;
    @FXML
    private Button btn_deletebrand;
    @FXML
    private Button btn_backtosupplier;

     private DBConnector db;
    private PreparedStatement pst = null;
    private Object event;
       private ObservableList<ModelTableBrands> data;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   db=new DBConnector();
    }    
//go to the add brand overview
    public void addBrandBtn(ActionEvent event) throws IOException{
    
        Parent goAddBrand = FXMLLoader.load(getClass().getResource("AddBrand.fxml"));
        Scene addbrand = new Scene(goAddBrand);
    
        Stage showAddBrand= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showAddBrand.setScene(addbrand);
        showAddBrand.show();
    
    }
  //go to the update brand overview
    public void updateBrandBtn(ActionEvent event) throws IOException{
    
        Parent goUpdateBrand = FXMLLoader.load(getClass().getResource("UpdateBrand.fxml"));
        Scene addbrand = new Scene(goUpdateBrand);
    
        Stage showUpdateBrand= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showUpdateBrand.setScene(addbrand);
        showUpdateBrand.show();
   }
    //go to delete brand overview
    public void deleteBrandBtn(ActionEvent event) throws IOException{
         Parent goDeleteBrand = FXMLLoader.load(getClass().getResource("DeleteBrand.fxml"));
         Scene deletebrand = new Scene (goDeleteBrand);
         
         Stage showDeleteBrand = (Stage) ((Node) event.getSource()).getScene().getWindow();
         showDeleteBrand.setScene(deletebrand);
         showDeleteBrand.show();
    }
    

//load brand details in to the table view
  @FXML
    private void btn_load_brands(ActionEvent event) {
        try {

            Connection conn = db.Connect();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("select*from addbrandstable");
        
            while (rs.next()) {
                data.add(new ModelTableBrands(rs.getString("brandid"),rs.getString("brandname"), rs.getString("supname"),
                rs.getString("date"),rs.getString("des")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);

        }

        //set values for table
        col_brandid_inbrands.setCellValueFactory(new PropertyValueFactory<>("brandid"));
        col_brandname.setCellValueFactory(new PropertyValueFactory<>("brandname"));
        col_supplier_inbrands.setCellValueFactory(new PropertyValueFactory<>("supname"));
        col_date_inbrands.setCellValueFactory(new PropertyValueFactory("date"));
        col_des_inbrands.setCellValueFactory(new PropertyValueFactory("des"));

        tbl_brandsinfo.setItems(data);

    }
    
    
     //go back to supplier overview
     
          @FXML
     public void btn_backtosupplier(ActionEvent event) throws IOException
     {
     
         Parent goBackToSupplier = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
         Scene goSupplierAgain= new Scene(goBackToSupplier);
         
         Stage setGoSupplier = (Stage) ((Node) event.getSource()).getScene().getWindow();
         setGoSupplier.setScene(goSupplierAgain);
         setGoSupplier.show();
         
     
     }

}