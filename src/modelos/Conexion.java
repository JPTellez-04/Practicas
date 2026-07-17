package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String usuario = "sa";
    private static final String contraseña = "ClanRagnar45#";
    private static final String port = "1433";
    private static final String host = "localhost";
    private static final String dataBaseName = "Animals";
    private static final String server = "sqlserver";
    //private static final String url = "jdbc:sqlserver://localhost:%s;databaseName=Animals;encrypt=true;trustServerCertificate=true";

    public static String getStringUrl(){
        return String.format("jdbc:%s://%s:%s;databaseName=%s;encrypt=true;trustServerCertificate=true",server,host,port,dataBaseName);
    }
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(getStringUrl(),usuario,contraseña);
    }
}