package Helper;

import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class connectionHelper {
    Connection con;
    Statement stm;
    
    // menyiapkan parameter untuk koneksi ke database
    private static final String DB_NAME = "manajemen_keuangan";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    
    public static Connection getConnection() throws SQLException{
            DriverManager.registerDriver(new Driver(){});
            //membuat koneksi ke database
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn; //mengembaikan nilai conn
    }   
}
