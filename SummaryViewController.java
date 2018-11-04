package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SummaryViewController implements Initializable {

    @FXML
    private AnchorPane adminView;
    @FXML
    private TableView<?> tbl_summaryview;
    @FXML
    private TableColumn<?, ?> col_no;
    @FXML
    private TableColumn<?, ?> col_supname_in_summaryview;
    @FXML
    private TableColumn<?, ?> col_brand_in_summaryview;
    @FXML
    private TableColumn<?, ?> col_product_in_summaryview;
    @FXML
    private TableColumn<?, ?> col_pprice_in_summaryview;
    @FXML
    private TableColumn<?, ?> col_sprice_in_summaryview;
    @FXML
    private TableColumn<?, ?> col_date_in_summaryview;
    @FXML
    private Button btn_load_summarydetails;
    @FXML
    private Button btn_delete_summary;
    @FXML
    private Button btn_edit_pprice_in_summaryview;
    @FXML
    private Button btn_addauditor_in_summaryview;
    @FXML
    private Button btn_email_in_summaryview;
    private DBConnector db;
    ResultSet rs;
    Connection conn = null;
    PreparedStatement pst = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       db = new DBConnector();
       
    }    
    
    //go to the add new auditor overview
    public void addAuditorBtn(ActionEvent event) throws IOException
    {
        Parent goAddAuditor = FXMLLoader.load(getClass().getResource("AddNewAuditor.fxml"));
        Scene addAuditor = new Scene(goAddAuditor);
    
        Stage showAddNewAuditor= (Stage) ((Node) event.getSource()).getScene().getWindow(); 
        showAddNewAuditor.setScene(addAuditor);
        showAddNewAuditor.show();
    }
    
    //go back to login overview
    public void btnBackInSummaryView(ActionEvent event) throws IOException
    {
    Parent backInSummaryView = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene backInSummary = new Scene(backInSummaryView);
    Stage showLogin = (Stage) ((Node)event.getSource()).getScene().getWindow();
    showLogin.setScene(backInSummary);
    showLogin.show();
    
    }
    
}
