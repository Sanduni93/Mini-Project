package main;

import Dialog.AlertDialogInsert;
import static com.mysql.jdbc.Messages.getString;
import java.lang.String;
import java.awt.event.MouseEvent;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static sun.misc.MessageUtils.where;
import static javax.swing.UIManager.getString;

public class UpdateSupplierController implements Initializable {

    @FXML
    private Label lbl_updatesupplier;
    @FXML
    private Label lbl_updatesupid;
    @FXML
    private Label lbl_updatesupname;
    @FXML
    private Label lbl_updatesupconnum;
    @FXML
    private Label lbl_updatesupadd;
    @FXML
    private Label lbl_updatesupdes;
    @FXML
    private Button btn_update_supplier;
    @FXML
    private TextField tf_update_supid;
    @FXML
    private TextField tf_update_supname;
    @FXML
    private TextField tf_update_supconnum;
    @FXML
    private TextField tf_update_supadd;
    @FXML
    private TextField tf_update_supdes;
    @FXML
    private AnchorPane UpdateSupplierOverview;
    @FXML
    private TextField enter_supplierid_toupdate;
    @FXML
    private Button btn_load_supdetails;
    @FXML
    private Button btn_update_supplier1;

    private DBConnector db;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBConnector();
    }

    public class Function {

        @FXML
        public ResultSet find(String s) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
                pst = conn.prepareStatement("select*from addsuppliertable where supid = ?");
                pst.setString(1, s);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
            return rs;
        }
    }

    @FXML
    public void btnGoToUpdateSupplierDetails(ActionEvent event) {

        Function f = new Function();
        ResultSet rs = null;
        String supid = "supid";
        String supname = "supname";
        String supconnum = "supconnum";
        String supadd = "supadd";
        String des = "des";

        rs = f.find(enter_supplierid_toupdate.getText());

        try {

            if (rs.next()) {

                tf_update_supid.setText(rs.getString("supid"));
                tf_update_supname.setText(rs.getString("supname"));
                tf_update_supconnum.setText(rs.getString("supconnum"));
                tf_update_supadd.setText(rs.getString("supadd"));
                tf_update_supdes.setText(rs.getString("des"));

            } else {

                JOptionPane.showMessageDialog(null, "No data for this index");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }
//update details

    @FXML
    public void btnUpdateSupplier(ActionEvent event) throws SQLException {

        try {

            String supid = tf_update_supid.getText();
            String supname = tf_update_supname.getText();
            String supconnum = tf_update_supconnum.getText();
            String supadd = tf_update_supadd.getText();
            String des = tf_update_supdes.getText();

            String sql = "update addsuppliertable set supid='" + supid + "',supname='" + supname + "',supconnum='" + supconnum + "',supadd='" + supadd + "',des='" + des + "' where supid='" + supid + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            
            AlertDialogInsert.display("Connection", "Data Updated Successfully");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }
    }
    
    @FXML
    public void btnBackUpdateSupplier(ActionEvent event) throws IOException
    {
    Parent BackUpdateSupplier = FXMLLoader.load(getClass().getResource("AuditorView.fxml"));
    Scene supplier1 = new Scene(BackUpdateSupplier);
    
    Stage showSupplier1= (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplier1.setScene(supplier1);
    showSupplier1.show();
    
    }

}
