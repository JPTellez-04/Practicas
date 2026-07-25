import modelos.Alumno;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcion;

        do {
            opcion = menu();

            switch (opcion) {
                case 1 -> registrarAlumno();
                case 2 -> mostrarAlumnos();
                case 3 -> actualizarAlumno();
                case 4 -> eliminarAlumno();
                case 5 -> mostrarEstadisticas();
                case 6 -> System.out.println("\nGracias por utilizar el sistema.");
                default -> System.out.println("\nOpción inválida.");
            }

        } while (opcion != 6);

        sc.close();
    }

    public static int menu() {

        System.out.println("""
                
                ==========================================
                      SISTEMA DE GESTIÓN DE ALUMNOS
                ==========================================
                
                1) Registrar Alumno
                2) Mostrar Alumnos
                3) Actualizar Alumno
                4) Eliminar Alumno
                5) Estadísticas
                6) Salir
                """);

        System.out.print("Seleccione una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        return opcion;
    }

    private static void registrarAlumno() {

        try {

            System.out.println("\n===== REGISTRAR ALUMNO =====");

            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Edad: ");
            int edad = sc.nextInt();
            sc.nextLine();

            System.out.print("Sexo (Hombre/Mujer): ");
            String sexo = sc.nextLine();

            System.out.print("Correo: ");
            String correo = sc.nextLine();

            Alumno alumno = new Alumno(
                    matricula,
                    nombre,
                    edad,
                    sexo,
                    correo
            );

            alumno.save();

            System.out.println("\nAlumno registrado correctamente.");

        } catch (SQLException e) {
            System.out.println("\nNo fue posible registrar al alumno.");
            e.printStackTrace();
        }
    }

    private static void mostrarAlumnos() {

        try {

            List<Alumno> alumnos = Alumno.findAll();

            System.out.println("\n========== LISTA DE ALUMNOS ==========\n");

            if (alumnos.isEmpty()) {
                System.out.println("No existen alumnos registrados.");
                return;
            }

            alumnos.forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println("\nError al consultar los alumnos.");
        }
    }

    private static void actualizarAlumno() {

        try {

            System.out.println("\n===== ACTUALIZAR ALUMNO =====");

            System.out.print("Matrícula del alumno: ");
            String matricula = sc.nextLine();

            System.out.print("Nuevo nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Nueva edad: ");
            int edad = sc.nextInt();
            sc.nextLine();

            System.out.print("Nuevo sexo: ");
            String sexo = sc.nextLine();

            System.out.print("Nuevo correo: ");
            String correo = sc.nextLine();

            Alumno.updateStudentByMatricula(
                    matricula,
                    nombre,
                    edad,
                    sexo,
                    correo
            );

            System.out.println("\nAlumno actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("\nNo fue posible actualizar el alumno.");
        }
    }

    private static void eliminarAlumno() {

        try {

            System.out.println("\n===== ELIMINAR ALUMNO =====");

            System.out.print("Matrícula: ");
            String matricula = sc.nextLine();

            Alumno.deleteStudentByMatricula(matricula);

            System.out.println("\nAlumno eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("\nNo fue posible eliminar el alumno.");
        }
    }

    private static void mostrarEstadisticas() {

        try {

            int hombres = Alumno.numberOfMen();
            int total = Alumno.total();
            int mujeres = total - hombres;

            System.out.println("""
                    
                    ===== ESTADÍSTICAS =====
                    """);

            System.out.println("Total de alumnos : " + total);
            System.out.println("Hombres          : " + hombres);
            System.out.println("Mujeres          : " + mujeres);

        } catch (SQLException e) {
            System.out.println("\nNo fue posible obtener las estadísticas.");
        }
    }

}