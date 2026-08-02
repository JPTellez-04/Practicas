import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EstacionamientoDAO {

    public static void insertar(Vehiculo v){

        String sql =
                "INSERT INTO ESTACIONAMIENTO(tipo_carro,placa,horas,tarifa) VALUES(?,?,?,?)";
        try(
            Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
                ){
            ps.setString(1,v.getClass().getSimpleName());
            ps.setString(2,v.getPlaca());
            ps.setInt(3,v.getHorasEstacionado());
            ps.setDouble(4,v.calcularCostoEstacionamiento());
            ps.executeUpdate();
            System.out.println("Vehiculo Guardado");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}