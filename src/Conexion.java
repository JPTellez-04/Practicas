import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=STREAMING;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "ClanRagnar45#";
    public static Connection createConnection() throws SQLException{
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}