import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {
    Connection con;
    Statement s;
    Connect(){
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/University_db","fahim","root1234");
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

}
