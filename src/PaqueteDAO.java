import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDAO {
    public static void insertar(Paquete paquete) {
        String sql = """
                INSERT INTO PAQUETE
                (destinatario, peso_kg, tipo_envio, costo)
                VALUES(?,?,?,?)
                """;
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paquete.getNombreDestinatario());
            ps.setDouble(2, paquete.getPesoKg());
            ps.setString(3, paquete.getEstrategia().toString());
            ps.setDouble(4, paquete.obtenerCostoEnvio());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = rs.getInt(1);
                paquete.setId(id);
            }
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static List<Paquete> obtenerTodos(){
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM PAQUETE";
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id");
                String destinatario = rs.getString("destinatario");
                double peso = rs.getDouble("peso_kg");
                String tipo = rs.getString("tipo_envio");
                EstrategiaEnvio estrategia;
                switch(tipo){
                    case "Estándar":
                        estrategia = new EnvioEstandar();
                        break;
                    case "Express":
                        estrategia = new EnvioExpress();
                        break;
                    case "Internacional":
                        estrategia = new EnvioInternacional();
                        break;
                    default:
                        estrategia = new EnvioEstandar();
                }
                Paquete paquete = new PaqueteHijo(
                        destinatario,
                        peso,
                        estrategia
                );
                paquete.setId(id);
                lista.add(paquete);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public static Paquete buscarPorId(int idBuscar){

        String sql = "SELECT * FROM PAQUETE WHERE id = ?";

        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, idBuscar);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){

                String destinatario = rs.getString("destinatario");
                double peso = rs.getDouble("peso_kg");
                String tipo = rs.getString("tipo_envio");

                EstrategiaEnvio estrategia;

                switch(tipo){

                    case "Estándar":
                        estrategia = new EnvioEstandar();
                        break;

                    case "Express":
                        estrategia = new EnvioExpress();
                        break;

                    case "Internacional":
                        estrategia = new EnvioInternacional();
                        break;

                    default:
                        estrategia = new EnvioEstandar();
                }

                Paquete paquete = new PaqueteHijo(
                        destinatario,
                        peso,
                        estrategia
                );

                paquete.setId(idBuscar);

                rs.close();

                return paquete;
            }

            rs.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    public static void eliminar(int id){
        String sql = "DELETE FROM PAQUETE WHERE id = ?";
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1,id);
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Paquete eliminado correctamente.");
            }else{
                System.out.println("No existe un paquete con ese ID.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void actualizar(Paquete paquete){
        String sql = """
            UPDATE PAQUETE
            SET destinatario = ?,
                peso_kg = ?,
                tipo_envio = ?,
                costo = ?
            WHERE id = ?
            """;
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, paquete.getNombreDestinatario());
            ps.setDouble(2, paquete.getPesoKg());
            ps.setString(3, paquete.getEstrategia().toString());
            ps.setDouble(4, paquete.obtenerCostoEnvio());
            ps.setInt(5, paquete.getId());
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Paquete actualizado correctamente.");
            }else{
                System.out.println("No se encontró el paquete.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}