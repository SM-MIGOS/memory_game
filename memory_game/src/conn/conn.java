package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

// Hier heb ik een class voor de database connectie gemaakt  - SURAJ MEGHOE
public class conn {
    // HIER HEB IK METHOD GEMAAKT VOOR DE CONNECTIE MET DE DATABASE - SURAJ MEGHOE
    public static Connection getConnection() throws Exception {
        try {

            String url = "jdbc:mysql://localhost:3306/memory_game";
            String username = "root";
            String password = "1234";

            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // HIER HEB IK METHOD GEMAAKT VOOR HET SLUITEN VAN DE CONNECTIE MET DE DATABASE - SURAJ MEGHOE
    public static void CloseDatabaseConnection(Connection conn) throws Exception {
        conn.close();
    }


}
