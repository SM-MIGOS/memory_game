
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class conn
{
    public static void main (String[] args)
    {
        try {
            //test1234
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/memory_game", "root", "1234");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_usernaam"));
            }

        } catch (Exception e)
        {
            e.printStackTrace();;
        }
    }
}
