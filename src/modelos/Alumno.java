package modelos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Alumno {
    /* Atributes */
    private Integer id;
    private String matricula;
    private String nombre;
    private Integer edad;
    private String sexo;
    private String correo;

    /* Constructors */
    private Alumno(Integer id, String matricula, String nombre, Integer edad, String sexo, String correo) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
    }
    public Alumno(String matricula, String nombre, Integer edad, String sexo, String correo) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
    }

    /* Methods */
    public void save() throws SQLException {
        try(
                Connection con = Conexion.createConnection();
                PreparedStatement stmt = con.prepareStatement("INSERT INTO Alumno(matricula,nombre,edad,sexo,correo) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ){
            stmt.setString(1, this.matricula);
            stmt.setString(2, this.nombre);
            stmt.setInt(3, this.edad);
            stmt.setString(4, this.sexo);
            stmt.setString(5, this.correo);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        }catch (Exception e){
            //throw new SQLException();
            e.printStackTrace();
        }
    }

    public static ArrayList<Alumno> findAll() throws SQLException {
        try(
                Connection con = Conexion.createConnection();
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM Alumno");
                ){
            ResultSet rs = stmt.executeQuery();
            ArrayList<Alumno> alumnos = new ArrayList<>();
            while(rs.next()){
                alumnos.add(new Alumno(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
            return  alumnos;
        }catch (Exception e){
            throw new SQLException();
        }
    }
    public static void updateStudentByMatricula(String matricula, String nombre, Integer edad, String sexo, String correo) throws SQLException {
        try(
                Connection con = Conexion.createConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE Alumno SET nombre=?, edad = ?, sexo=?, correo=? WHERE matricula=?");
        ){
            stmt.setString(1, nombre);
            stmt.setInt(2, edad);
            stmt.setString(3, sexo);
            stmt.setString(4, correo);
            stmt.setString(5, matricula);
            stmt.executeUpdate();
        }catch (Exception e){
            throw new SQLException();
        }
    }

    public static void deleteStudentByMatricula(String matricula) throws SQLException {
        try(
                Connection con = Conexion.createConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Alumno WHERE matricula=?");
        ){
            stmt.setString(1, matricula);
            stmt.executeUpdate();
        }catch (Exception e){
            throw new SQLException();
        }
    }

    public static Integer numberOfMen() throws SQLException {
        try(
                Connection con = Conexion.createConnection();
                PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM Alumno WHERE sexo = 'Hombre'");
                ){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            return null;
        }catch (Exception e){
            throw new SQLException();
        }
    }

    public static Integer total() throws SQLException {
        try(
                Connection con = Conexion.createConnection();
                PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM Alumno");
        ){
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            return null;
        }catch (Exception e){
            throw new SQLException();
        }
    }

    /* ToString */
    @Override
    public String toString() {
        return """
            ----------------------------------------
            ID         : %d
            Matrícula  : %s
            Nombre     : %s
            Edad       : %d
            Sexo       : %s
            Correo     : %s
            """.formatted(id, matricula, nombre, edad, sexo, correo);
    }

    /* Getters y Setters */
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
