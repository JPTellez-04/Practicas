import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;" +
                    "databaseName=MENSAJERIA;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";

    private static final String USER = "sa";
    private static final String PASSWORD = "ClanRagnar45#";

    public static Connection createConnection() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );

    }

}





















