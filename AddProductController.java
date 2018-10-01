
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
