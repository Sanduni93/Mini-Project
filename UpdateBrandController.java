
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class UpdateBrandController implements Initializable {

    @FXML
    private AnchorPane UpdateBrandOverview;
    @FXML
    private Label lbl_updatebrand;
    @FXML
    private Label lbl_brandname_inupdatebrand;
    @FXML
    private Label lbl_supname_inupdatebrand;
    @FXML
    private Label lbl_date_inupdatebrand;
    @FXML
    private Label lbl_description_inupdatebrand;
     @FXML
    private TextField tf_enterbrandid;
      @FXML
    private TextField tf_brandid_inupdatebrand;
    @FXML
    private TextField tf_brandname_inupdatebrand;
    @FXML
    private TextField tf_branddes_inupdatebrand;
    @FXML
    private Button btn_back_in_updatebrand;
    @FXML
    private Button btn_load_branddetails;
    @FXML
    private ComboBox<String> combo_supname_inupdatebrand;
    @FXML
    private Button btn_update_brand;
    @FXML
    private DatePicker datepicker_date_inupdatebrand;
    
      ObservableList<String> list = FXCollections.observableArrayList();
      private DBConnector db;
      private Connection conn;
      private PreparedStatement pst;
      private ResultSet rs;
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set combo box values
       db = new DBConnector();
        
           try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
        pst=conn.prepareStatement("select*from addsuppliertable");
        rs = pst.executeQuery();
        while(rs.next())
        {
            list.add(rs.getString("supname"));
        }
        }
        catch (SQLException e) 
        {
            System.out.println(e);
        }
        combo_supname_inupdatebrand.setItems(list);
       
       
    }    
    
    public class Function {

        @FXML
        public ResultSet find(String s) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
                pst = conn.prepareStatement("select*from addbrandstable where brandid = ?");
                pst.setString(1, s);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
            return rs;
        }
    }
    //load brand details to update
    @FXML
    public void btnGoToUpdateBrandsDetails(ActionEvent event) {

        UpdateBrandController.Function f = new UpdateBrandController.Function();
        ResultSet rs = null;
        String brandid = "brandid";
        String brandname = "brandname";
        String supname = "supname";
        String date = "date" ;
        String des = "des";
       

        rs = f.find(tf_enterbrandid.getText());

        try {

            if (rs.next()) {
                tf_brandid_inupdatebrand.setText(rs.getString("brandid")); 
                tf_brandname_inupdatebrand.setText(rs.getString("brandname"));
                combo_supname_inupdatebrand.setValue(rs.getString("supname"));
                tf_branddes_inupdatebrand.setText(rs.getString("des"));
              
            } else {

                JOptionPane.showMessageDialog(null, "No data for this index");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }

    }
    
    @FXML
    public void btnUpdateSupplier(ActionEvent event) throws SQLException {

        try {

          String bid = tf_brandid_inupdatebrand.getText();
          String bname = tf_brandname_inupdatebrand.getText();
          String supname = combo_supname_inupdatebrand.getValue();
          String date = datepicker_date_inupdatebrand.getEditor().getText();
          String des = tf_branddes_inupdatebrand.getText();

            String sql = "update addbrandstable set brandid='" + bid + "',brandname='" + bname + "',supname = '"+supname+"',date='"+date+"',des='" + des + "'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            
            AlertDialogInsert.display("Connection", "Data Updated Successfully");

        } 
        catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Data Updated Successfully");

        }
    }
    
    @FXML
    public void btnGoBackToBrandOverview(ActionEvent event) throws IOException
    {
    Parent brandGoBack = FXMLLoader.load(getClass().getResource("BrandOverview.fxml"));
    Scene supplier2 = new Scene(brandGoBack);
    
    Stage showSupplier2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
    showSupplier2.setScene(supplier2);
    showSupplier2.show();
    }
    
}
