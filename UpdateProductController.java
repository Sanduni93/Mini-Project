
package main;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateProductController implements Initializable {

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
    Connection conn=null;
    PreparedStatement pst=null;
    ResultSet rs = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         db = new DBConnector();

        //add supplier's names for combobox
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select*from addsuppliertable");
            rs = pst.executeQuery();
            while (rs.next()) {
                list1.add(rs.getString("supname"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        combo_supname_in_updateproduct.setItems(list1);
        //add brand names for combobox
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select*from addbrandstable");
            rs = pst.executeQuery();
            while (rs.next()) {
                list2.add(rs.getString("brandname"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        combo_brand_in_updateproduct.setItems(list2);

        //add category names for combobox   
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select*from addcategorytable");
            rs = pst.executeQuery();
            while (rs.next()) {
                list3.add(rs.getString("categoryname"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        combo_category_in_updateproduct.setItems(list3);
        
        
    }    
    
}
