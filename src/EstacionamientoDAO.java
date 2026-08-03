import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public static List<Vehiculo> obtenerTodos(){

        List<Vehiculo> lista = new ArrayList<>();

        String sql = "SELECT * FROM ESTACIONAMIENTO";

        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                String placa = rs.getString("placa");
                int horas = rs.getInt("horas");
                String tipo = rs.getString("tipo_carro");
                Vehiculo vehiculo = null;
                System.out.println("Tipo = [" + tipo + "]");
                System.out.println("Longitud = " + tipo.length());
                switch(tipo){
                    case "Auto"->{
                        vehiculo = new Auto(placa, horas);
                    }
                    case "Moto"->{
                        vehiculo = new Moto(placa, horas);
                    }
                    case "Camion"-> {
                        vehiculo = new Camion(placa, horas);
                    }
                }
                if(vehiculo != null){
                    lista.add(vehiculo);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("Cantidad de vehículos: " + lista.size());

        for (Vehiculo v : lista) {
            System.out.println(v);
        }
        return lista;
    }
    public static void actualizar(int id, Vehiculo v){
        String sql = """
            UPDATE ESTACIONAMIENTO
            SET placa=?,
                tipo_carro=?,
                horas=?,
                tarifa=?
            WHERE id=?
            """;
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,v.getPlaca());
            ps.setString(2,v.getClass().getSimpleName());
            ps.setInt(3,v.getHorasEstacionado());
            ps.setDouble(4,v.calcularCostoEstacionamiento());
            ps.setInt(5,id);
            int filas = ps.executeUpdate();
            if(filas>0)
                System.out.println("Vehículo actualizado.");
            else
                System.out.println("No existe ese ID.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void eliminar(int id){
        String sql = "DELETE FROM ESTACIONAMIENTO WHERE id=?";
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            int filas = ps.executeUpdate();
            if(filas>0)
                System.out.println("Vehículo eliminado.");
            else
                System.out.println("No existe ese ID.");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static int contar(){
        String sql = "SELECT COUNT(*) FROM ESTACIONAMIENTO";
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}