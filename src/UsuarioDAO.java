import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    public static void insertar(Usuario usuario){
        String sql = """
                INSERT INTO USUARIO
                (correo, meses_activo, tipo_plan, costo)
                VALUES(?,?,?,?)
                """;
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, usuario.getCorreoElectronico());
            ps.setInt(2, usuario.getMesesActivo());
            ps.setString(3, usuario.getPlan().toString());
            ps.setDouble(4,usuario.ObtenerTotalAPagar());
            System.out.println(usuario.getPlan().toString());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static List<Usuario> obtenerTodos(){
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIO";
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                String correo = rs.getString("correo");
                int meses = rs.getInt("meses_activo");
                String tipoPlan = rs.getString("tipo_plan");
                PlanSuscripcion plan = null;
                switch(tipoPlan){
                    case "Basico" -> plan = new PlanBasico();
                    case "Estandar" -> plan = new PlanEstandar();
                    case "Premium" -> plan = new PlanPremium();
                }
                if(plan != null){
                    Usuario usuario = new Usuario(correo, meses, plan);
                    lista.add(usuario);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public static void actualizar(int id, Usuario usuario){
        String sql = """
            UPDATE USUARIO
            SET correo = ?,
                meses_activo = ?,
                tipo_plan = ?,
                costo = ?
            WHERE id = ?
            """;
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, usuario.getCorreoElectronico());
            ps.setInt(2, usuario.getMesesActivo());
            ps.setString(3, usuario.getPlan().toString());
            ps.setDouble(4, usuario.ObtenerTotalAPagar());
            ps.setInt(5, id);
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Usuario actualizado correctamente.");
            }else{
                System.out.println("No existe un usuario con ese ID.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void eliminar(int id){
        String sql = "DELETE FROM USUARIO WHERE id = ?";
        try(Connection con = Conexion.createConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            if(filas > 0){
                System.out.println("Usuario eliminado correctamente.");
            }else{
                System.out.println("No existe un usuario con ese ID.");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static int contar(){
        String sql = "SELECT COUNT(*) FROM USUARIO";
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