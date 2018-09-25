
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView<?> tbl_catg_info;
    @FXML
    private TableColumn<?, ?> col_catgid;
    @FXML
    private TableColumn<?, ?> col_catgname;
    @FXML
    private TableColumn<?, ?> col_brandname;
    @FXML
    private TableColumn<?, ?> col_supname;
    @FXML
    private TableColumn<?, ?> col_date;
    @FXML
    private TableColumn<?, ?> col_des;
    
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
    
    
    
    
}
