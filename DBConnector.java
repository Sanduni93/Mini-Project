package main;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {

    public Connection Connect()  {

        try {

            String url = "jdbc:mysql://localhost/elecdesk";
            String user = "root";
            String password = "";
            Class .forName("com.mysql.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(url,user,password);
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
return null;
    }

}
