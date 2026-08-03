import java.util.Scanner;

class Main {
    public static Scanner teclado = new Scanner(System.in);
    public static EmpresaMensajeria Empresa = new EmpresaMensajeria();

    public static void main(String[] args) {
        int opc = 0;
        boolean Salir = false;


        do {
            System.out.println("--- MENÚ ---");
            System.out.println("Ingrese la acción que desea relaizar");
            System.out.println("1) Realizar un envío");
            System.out.println("2) Mostrar reporte");
            System.out.println("0) Salir");
            do {
                try{
                    opc = teclado.nextInt();
                    teclado.nextLine();
                    switch (opc){
                        case 1:
                            RealizarEnvio();
                            break;
                        case 2:
                            MostrarReporte();
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
            }while (!Salir);
        }while (opc != 0);
    }


    public static void RealizarEnvio(){
        boolean Salir = false;
        EstrategiaEnvio Estrategia = null;
        double PesoPaquete;
        System.out.println("Ingrese el nombre del destinatario del paquete");
        String NombreDestinatario = teclado.nextLine();
        do{
            System.out.println("Ingrese el peso del paquete");
            PesoPaquete = teclado.nextDouble();
            if(PesoPaquete <= 0){
                System.out.println("Error. Ingrese un peso mayor a 0");
                teclado.nextLine();
            }
        }while (PesoPaquete <= 0);

        do {
            System.out.println("Seleccione el tipo de envío que desea realizar");
            System.out.println("1) Envío Estándar");
            System.out.println("2) Envío Express");
            System.out.println("3) Envío Internacional");
            try{
                int opc = teclado.nextInt();
                teclado.nextLine();
                switch (opc){
                    case 1:
                        Estrategia = new EnvioEstandar();
                        break;
                    case 2:
                        Estrategia = new EnvioExpress();
                        break;
                    case 3:
                        Estrategia = new EnvioInternacional();
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
        }while (!Salir);
        System.out.println("Registro realizado correctamente");
        Paquete PaqueteEnviar = new PaqueteHijo(NombreDestinatario, PesoPaquete, Estrategia);
        Empresa.RegistrarPaquetes(PaqueteEnviar);
    }

    public static void MostrarReporte(){
        int Conteo = 1;
        double TotalRecaudado = 0.0;
        System.out.println("--- DESGLOSE TOTAL ---");
        if(Empresa.PaquetesRegistrados.isEmpty()){
            System.out.println("La lista está vacía.");
        }
        for (Paquete P : Empresa.PaquetesRegistrados){
            System.out.println("Paquete: " + Conteo +
                    ", destinatario: " + P.getNombreDestinatario() +
                    ", peso del paquete: " + P.getPesoKg() +
                    "KG, tipo de envío: " + P.getEstrategia() +
                    ", costo total: " + P.obtenerCostoEnvio());

            Conteo ++;
            TotalRecaudado += P.obtenerCostoEnvio();
        }
        System.out.println("Total recaudado: $" + TotalRecaudado);
    }
}