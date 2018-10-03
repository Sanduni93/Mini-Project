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

public class AddProductController implements Initializable {

    @FXML
    private Label lbl_addproduct;
    @FXML
    private Label lbl_productid_in_addproduct;
    @FXML
    private Label lbl_productname_in_addproduct;
    @FXML
    private Label lbl_quantity_in_addproduct;
    @FXML
    private Label lbl_ppricein_addproduct;
    @FXML
    private Label lbl_sprice_in_addproduct;
    @FXML
    private Label lbl_supname_in_addproduct;
    @FXML
    private Label lbl_brandname_in_addproduct;
    @FXML
    private Label lbl_category_in_addproduct;
    @FXML
    private Label lbl_rma_in_addproduct;
    @FXML
    private Label lbl_date_in_addproduct;
    @FXML
    private TextField tf_productid_in_addproduct;
    @FXML
    private TextField tf_productname_in_addproduct;
    @FXML
    private TextField tf_quantity_in_addproduct;
    @FXML
    private TextField tf_pprice_in_addproduct;
    @FXML
    private TextField tf_sprice_in_addproduct;
    @FXML
    private ComboBox<String> combo_supname_in_addproduct;
    @FXML
    private ComboBox<String> combo_brand_in_addproduct;
    @FXML
    private ComboBox<String> combo_category_in_addproduct;
    @FXML
    private TextField tf_rms_in_addproduct;
    @FXML
    private DatePicker datepicker_date_in_addproduct;
    @FXML
    private Button btn_add_inaddproduct;
    @FXML
    private Button btn_back_in_addproduct;

    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList();
    private DBConnector db;
    private Connection conn = null;
    private PreparedStatement pst = null;
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

        combo_supname_in_addproduct.setItems(list1);
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

        combo_brand_in_addproduct.setItems(list2);

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

        combo_category_in_addproduct.setItems(list3);

    }

    //add product details to table
    @FXML
    public void addProductBtn(ActionEvent event) throws SQLException {
        String productid = tf_productid_in_addproduct.getText();
        String productname = tf_productname_in_addproduct.getText();
        String quantity = tf_quantity_in_addproduct.getText();
        String pprice = tf_pprice_in_addproduct.getText();
        String sprice = tf_sprice_in_addproduct.getText();
        String supname = combo_supname_in_addproduct.getValue();
        String brandname = combo_brand_in_addproduct.getValue();
        String catgname = combo_category_in_addproduct.getValue();
        String rma = tf_rms_in_addproduct.getText();
        String date = datepicker_date_in_addproduct.getEditor().getText();

        try {

            String sql = "insert into addproducttable (productid,productname,quantity,pprice,sprice,supname,brand,category,rma,date)"
                    + "values (?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, productid);
            pst.setString(2, productname);
            pst.setString(3, quantity);
            pst.setString(4, pprice);
            pst.setString(5, sprice);
            pst.setString(6, supname);
            pst.setString(7, brandname);
            pst.setString(8, catgname);
            pst.setString(9, rma);
            pst.setString(10, date);
            AlertDialogInsert.display("Connection", "Data Inserted Successfully");
        } catch (Exception ex) {
            Logger.getLogger(AddSupplierController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.execute();
            pst.close();
        }
        tf_productid_in_addproduct.clear();
        tf_productname_in_addproduct.clear();
        tf_quantity_in_addproduct.clear();
        tf_pprice_in_addproduct.clear();
        tf_sprice_in_addproduct.clear();
        tf_rms_in_addproduct.clear();
    }

    //back btn in add product
    @FXML
    public void btn_back_inaddproducts(ActionEvent event) throws IOException {
        Parent BackAddProduct = FXMLLoader.load(getClass().getResource("ProductOverview.fxml"));
        Scene product1 = new Scene(BackAddProduct);

        Stage showProduct1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showProduct1.setScene(product1);
        showProduct1.show();
    }
}
