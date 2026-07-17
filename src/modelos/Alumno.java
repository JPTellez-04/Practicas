package modelos;

import java.sql.*;

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

        }
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
