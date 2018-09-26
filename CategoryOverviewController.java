
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


public class CategoryOverviewController implements Initializable {

    @FXML
    private AnchorPane CategoryOverview;
    @FXML
    private Label lbl_catg_info;
    @FXML
    private Button btn_load_catgdetails;
    @FXML
    private Button btn_addcatg;
    @FXML
    private Button btn_updatecatg;
    @FXML
    private Button btn_deletecatg;
    @FXML
    private Button btn_back_in_catgoverview;
    @FXML
    private TableView<ModelTableCategory> tbl_catg_info;
    @FXML
    private TableColumn<ModelTableCategory, String> col_catgid;
    @FXML
    private TableColumn<ModelTableCategory, String> col_catgname;
    @FXML
    private TableColumn<ModelTableCategory, String> col_brandname;
    @FXML
    private TableColumn<ModelTableCategory, String> col_supname;
    @FXML
    private TableColumn<ModelTableCategory, String> col_date;
    @FXML
    private TableColumn<ModelTableCategory, String> col_des;
    
    private DBConnector db;
    private PreparedStatement pst=null;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      db = new DBConnector();
    }    
    
    //go to add new category
    
    @FXML
    public void addCatgBtn(ActionEvent event) throws IOException{
       Parent goAddCategory = FXMLLoader.load(getClass().getResource("AddCategory.fxml"));
        Scene addcategory = new Scene(goAddCategory);
    
        Stage showAddCategory= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showAddCategory.setScene(addcategory);
        showAddCategory.show();
    
    }
    
    // go to update category
        
    @FXML
    public void updateCatgBtn(ActionEvent event) throws IOException{
       Parent goAddCategory = FXMLLoader.load(getClass().getResource("UpdateCategory.fxml"));
        Scene updatecategory = new Scene(goAddCategory);
    
        Stage showUpdateCategory= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showUpdateCategory.setScene(updatecategory);
        showUpdateCategory.show();
    
    }
    
    //delete category
    
         
    @FXML
    public void deleteCatgBtn(ActionEvent event) throws IOException{
       Parent goDeleteCategory = FXMLLoader.load(getClass().getResource("DeleteCategory.fxml"));
        Scene updatecategory = new Scene(goDeleteCategory);
    
        Stage showDeleteCategory= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showDeleteCategory.setScene(updatecategory);
        showDeleteCategory.show();
    
    }
    
     //set values for table
    private ObservableList<ModelTableCategory> data;
    @FXML
    private void btn_load_category_details(ActionEvent event) {
        try {

            Connection conn = db.Connect();
            data = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery("select*from addcategorytable");
        
            while (rs.next()) {
                data.add(new ModelTableCategory(rs.getString("categoryid"),rs.getString("categoryname"), rs.getString("brandname"),
                rs.getString("supname"),rs.getString("date"),rs.getString("des")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);

        }

        //set values for table
        col_catgid.setCellValueFactory(new PropertyValueFactory<>("categoryid"));
        col_catgname.setCellValueFactory(new PropertyValueFactory<>("categoryname"));
        col_brandname.setCellValueFactory(new PropertyValueFactory<>("brandname"));
        col_supname.setCellValueFactory(new PropertyValueFactory<>("supname"));
        col_date.setCellValueFactory(new PropertyValueFactory("date"));
        col_des.setCellValueFactory(new PropertyValueFactory("des"));

        tbl_catg_info.setItems(data);

    }
    
    //go backto supplier overview
    @FXML
    public void btnGoBackToSupplierOverviewFromCategoryView(ActionEvent event) throws IOException{
    Parent BackCategoryOverview = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
    Scene supplierfromcategory = new Scene(BackCategoryOverview);
    
    Stage showSupplierFromCategory= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplierFromCategory.setScene(supplierfromcategory);
    showSupplierFromCategory.show();
    
    
    
    }
    
    
    
    
}
