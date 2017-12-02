import java.io.FileInputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * Simple Java program to connect to MySQL database running on localhost and
 * running SELECT and INSERT query to retrieve and add data.
 * @author Javin Paul
 */
public class index {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "admin";
    private static final String password = "";

    public static void main(String args[]) {
        String query = "select count(*) from book";

        try
        {
            Connection conn = DriverManager.getConnection(url, user, password);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            query = " insert into book (Name, Surname, Date, City)"
                    + " values (?, ?, ?, ?)";


            java.util.Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("props.xml"));
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString (1, prop.getProperty("Name"));
            preparedStmt.setString (2, prop.getProperty("Surname"));
            preparedStmt.setDate   (3, startDate);
            preparedStmt.setString (4, prop.getProperty("City"));
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }


        }
    }