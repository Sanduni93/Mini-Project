
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class AddNewAuditorController implements Initializable {

    @FXML
    private AnchorPane AddNewAuditorOverview;
    @FXML
    private Label lbl_addNewAuditor;
    @FXML
    private Label lbl_auditorId;
    @FXML
    private Label lbl_auditorFname;
    @FXML
    private Label lbl_auditorLname;
    @FXML
    private Label lbl_auditorAdd;
    @FXML
    private Label lbl_auditorNic;
    @FXML
    private Label lbl_auditorDob;
    @FXML
    private Label lbl_auditorConnum;
    @FXML
    private Label lbl_auditorUname;
    @FXML
    private Label lbl_auditorPassword;
    @FXML
    private TextField tf_auditorId;
    @FXML
    private TextField tf_auditorFname;
    @FXML
    private TextField tf_auditorLname;
    @FXML
    private TextField tf_auditorNic;
    @FXML
    private TextArea ta_auditorAdd;
    @FXML
    private DatePicker datepicker_auditorDob;
    @FXML
    private TextField tf_auditorConnum;
    @FXML
    private TextField tf_auditorUname;
    @FXML
    private PasswordField tf_auditorPassword;
    @FXML
    private Button btn_load_auditorDetails;
    @FXML
    private Button btn_addAuditor;
    @FXML
    private Button btn_updateAuditor;
    @FXML
    private Button btn_deleteAuditor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    
    
}
