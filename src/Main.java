import java.util.Scanner;

class Main {
    public static Scanner teclado = new Scanner(System.in);
    public static PlataformaStreaming Plataforma = new PlataformaStreaming();

    public static void main(String[] args) {
        boolean Salir = false;
        int opc = 0;
        do {
            do {
                System.out.println("--- MENÚ ---");
                System.out.println("Ingrese la acción que desea realizar");
                System.out.println("1) Registrar usuario");
                System.out.println("2) Mostrar reporte");
                System.out.println("0) Salir");
                try{
                    opc = teclado.nextInt();
                    teclado.nextLine();
                    switch (opc){
                        case 1:
                            RegistroUsuarios();
                            break;
                        case 2:
                            Reporte();
                            break;
                        case 0:
                            System.out.println("Saliendo...");
                            break;
                        default:
                            System.out.println("Error. Ingrese un valor correcto.");
                            break;
                    }
                    Salir = true;
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
        System.out.println("Ingrese el correo de perfil");
        String Correo = teclado.nextLine();
        System.out.println("Ingrese la cantidad de meses que desea activar");
        int meses = teclado.nextInt();
        do {
            System.out.println("Ingrese el tipo de plan que desea");
            System.out.println("1) Plan básico");
            System.out.println("2) Plan Estándar");
            System.out.println("3) Plan Premium");
            try{
                int opc = teclado.nextInt();
                teclado.nextLine();
                switch (opc){
                    case 1:
                        Plan = new PlanBasico();
                        break;
                    case 2:
                        Plan = new PlanEstandar();
                        break;
                    case 3:
                        Plan = new PlanPremium();
                        break;
                    default:
                        System.out.println("Error. Ingrese un valor correcto.");
                        break;
                }
                Salir = true;
            }catch (Exception ex){
                System.out.println("Error. Ingrese un valor correcto.");
                teclado.nextLine();
            }
        }while(!Salir);
        System.out.println("Registro realizado correctamente.");
        Usuario Usuario = new Usuario(Correo, meses, Plan);
        Plataforma.RegistrarUsuarios(Usuario);
    }
    int i = 10;
    public static void Reporte(){
        int Conteo = 1;
        double TotalRecaudado = 0.0;
        System.out.println("--- Desglose total ---");
        if (Plataforma.ListaUsuarios.isEmpty()){
            System.out.println("Lista vacía");
        }
        for (CuentaUsuario U : Plataforma.ListaUsuarios){
            System.out.println("Usuario: " + Conteo + " correo: " + U.getCorreoElectronico() + " meses activo: " + U.getMesesActivo() + " plan: " + U.getPlan() + " total a pagar: " + U.ObtenerTotalAPagar());
            Conteo ++;

            TotalRecaudado += U.ObtenerTotalAPagar();
        }
        System.out.println("Total recaudado: $" + TotalRecaudado);
    }


}