package main;

import Dialog.AlertDialogInsert;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class UpdateCategoryController implements Initializable {

    @FXML
    private AnchorPane UpdateCategoryOverview;
    @FXML
    private Label lbl_updatecatg;
    @FXML
    private Label lbl_catgid_inupdatecatg;
    @FXML
    private Label lbl_catgname_inupdatecatg;
    @FXML
    private Label lbl_brands_inupdatecatg;
    @FXML
    private Label lbl_supname_inupdatecatg;
    @FXML
    private Label lbl_date_inupdatecatg;
    @FXML
    private Label lbl_des_inupdatecatg;
    @FXML
    private TextField tf_catgid_in_updatecatg;
    @FXML
    private TextField tf_catgname_in_updatecatg;
    @FXML
    private ComboBox<String> combo_brandname_in_updatecatg;
    @FXML
    private ComboBox<String> combo_supname_in_updatecatg;
    @FXML
    private DatePicker datepicker_date_in_updatecatg;
    @FXML
    private TextField tf_des_in_updatecatg;
    @FXML
    private TextField tf_enter_catgid_toupdate;
    @FXML
    private Button btn_load_catgdetails_toupdate;
    @FXML
    private Button btn_update_catg;
    @FXML
    private Button btn_back_in_updatecatg;

    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    private DBConnector db;
    Connection conn ;
    PreparedStatement pst;
    ResultSet rs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new DBConnector();
       //add brand names to the combobox

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select*from addbrandstable");
            rs = pst.executeQuery();
            while (rs.next()) {
                list1.add(rs.getString("brandname"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        combo_brandname_in_updatecatg.setItems(list1);
        //add suppliers names to combobox
              try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
            pst = conn.prepareStatement("select*from addsuppliertable");
            rs = pst.executeQuery();
            while (rs.next()) {
                list2.add(rs.getString("supname"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        combo_supname_in_updatecatg.setItems(list2);

    }
    
    //load category details to update
    
     public class Function {

        @FXML
        public ResultSet find(String s) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/elecdesk", "root", "");
                pst = conn.prepareStatement("select*from addcategorytable where categoryid = ?");
                pst.setString(1, s);
                rs = pst.executeQuery();
            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }
            return rs;
        }
    }
     @FXML
     public void btnGoToLoadCatgDetailsToUpdate(ActionEvent event)
     {
     
     UpdateCategoryController.Function f = new UpdateCategoryController.Function();
     String categoryid = "categoryid";
     String categoryname = "categoryname";
     String brandname = "brandname";
     String supname = "supname";
     String date = "date";
     String des = "des";
     
     rs = f.find(tf_enter_catgid_toupdate.getText());
     
     try{
      if(rs.next())
      {
      tf_catgid_in_updatecatg.setText(rs.getString("categoryid"));
      tf_catgname_in_updatecatg.setText(rs.getString("categoryname"));
      combo_brandname_in_updatecatg.setValue(rs.getString("brandname"));
      combo_supname_in_updatecatg.setValue(rs.getString("supname"));
      
      tf_des_in_updatecatg.setText(rs.getString("des"));
      
     }else{
      JOptionPane.showMessageDialog(null, "No data for this categoryid");
      }   
     
     }
     catch(SQLException ex)
     {
     JOptionPane.showMessageDialog(null, ex.getMessage());
     
     }
     }
     //update category details
     @FXML
     public void btnUpdateCategory(ActionEvent event) throws SQLException {
     
        try {
            String categoryid = tf_catgid_in_updatecatg.getText();
            String categoryname = tf_catgname_in_updatecatg.getText();
            String brandname = combo_brandname_in_updatecatg.getSelectionModel().getSelectedItem();
            String supname = combo_supname_in_updatecatg.getSelectionModel().getSelectedItem();
            String date = ((TextField)datepicker_date_in_updatecatg.getEditor()).getText();
            String des=tf_des_in_updatecatg.getText();
            
            String sql = "update addcategorytable set categoryid='"+categoryid+"',categoryname='"+categoryname+"',"
                   + "brandname='"+brandname+"',supname='"+supname+"',date='"+date+"',des='"+des+"' where categoryid='"+categoryid+"'";
           
            pst = conn.prepareStatement(sql);
            //pst.execute();
             AlertDialogInsert.display("Connection", "Data Updated Successfully");
        } catch (SQLException ex) {
         Logger.getLogger(UpdateCategoryController.class.getName()).log(Level.SEVERE, null, ex);
    
        }
        finally{
        pst.execute();
        pst.close();
        }
         
     
     
     }


    

}
