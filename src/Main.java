import java.util.Scanner;

class Main {
    public static Scanner teclado = new Scanner(System.in);
    public static PlataformaStreaming Plataforma = new PlataformaStreaming();

    public static void main(String[] args) {
        boolean Salir = false;
        int opc = 0;
        do {
            do {
                System.out.println("1. Registrar usuario");
                System.out.println("2. Mostrar reporte");
                System.out.println("3. Actualizar usuario");
                System.out.println("4. Eliminar usuario");
                System.out.println("5. Contar usuarios");
                System.out.println("0. Salir");
                try{
                    opc = teclado.nextInt();
                    teclado.nextLine();
                    switch (opc){
                        case 1 -> RegistroUsuarios();
                        case 2 -> Reporte();
                        case 3 -> ActualizarUsuario();
                        case 4  -> EliminarUsuario();
                        case 5 -> {
                            System.out.println("--------------------------------");
                            System.out.println("Usuarios registrados: " + UsuarioDAO.contar());
                            System.out.println("--------------------------------");
                        }
                        case 0 -> {
                            System.out.println("Saliendo...");
                            Salir = true;
                        }
                        default -> System.out.println("Error. Ingrese un valor correcto.");
                    }
                }catch (Exception ex){
                    System.out.println("Error. Ingrese un valor correcto.");
                    teclado.nextLine();
                }
            }while(!Salir);
        }while (opc != 0);
    }

    public static void RegistroUsuarios(){
        boolean Salir = false;
        PlanSuscripcion Plan = null;

        System.out.println("--- Registro de usuario ---");

        Usuario Usuario = leerUsuario();
        UsuarioDAO.insertar(Usuario);
    }
    int i = 10;
    public static void Reporte(){
        int Conteo = 1;
        double TotalRecaudado = 0.0;
        System.out.println("--- Desglose total ---");
        for (CuentaUsuario U : UsuarioDAO.obtenerTodos()){
            System.out.println("Usuario: " + U.getId() + "\nCorreo: " + U.getCorreoElectronico() + " \nMeses activo: " + U.getMesesActivo() + " \nPlan: " + U.getPlan() + " \nTotal a pagar: $" + U.ObtenerTotalAPagar() + "\n      - - - - - - ");
            Conteo ++;

            TotalRecaudado += U.ObtenerTotalAPagar();
        }
        System.out.println("Total recaudado: $" + TotalRecaudado);
    }
    public static void ActualizarUsuario(){
        System.out.println("----- ACTUALIZAR USUARIO -----");
        System.out.print("Ingrese el ID del usuario: ");
        int id = teclado.nextInt();
        teclado.nextLine();

        Usuario usuario = leerUsuario();
        UsuarioDAO.actualizar(id, usuario);
    }

    public static void EliminarUsuario(){
        System.out.println("----- ELIMINAR USUARIO -----");
        System.out.print("Ingrese el ID del usuario: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        UsuarioDAO.eliminar(id);
    }

    public static Usuario leerUsuario(){
        System.out.print("Ingrese el correo: ");
        String correo = teclado.nextLine();
        System.out.print("Ingrese los meses activos: ");
        int meses = teclado.nextInt();
        teclado.nextLine();
        PlanSuscripcion plan = null;
        boolean salir = false;
        while(!salir){
            System.out.println("Seleccione el plan");
            System.out.println("1. Básico");
            System.out.println("2. Estándar");
            System.out.println("3. Premium");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            switch(opcion){
                case 1 -> {
                    plan = new PlanBasico();
                    salir = true;
                }
                case 2 -> {
                    plan = new PlanEstandar();
                    salir = true;
                }
                case 3 -> {
                    plan = new PlanPremium();
                    salir = true;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
        return new Usuario(correo, meses, plan);
    }
}