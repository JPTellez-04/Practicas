import java.util.List;
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
            System.out.println("3) Buscar paquete");
            System.out.println("4) Actualizar paquete");
            System.out.println("5) Eliminar paquete");
            System.out.println("0) Salir");
            do {
                try{
                    opc = teclado.nextInt();
                    teclado.nextLine();
                    switch(opc){

                        case 1 -> RealizarEnvio();
                        case 2 -> MostrarReporte();
                        case 3 -> BuscarPaquete();
                        case 4 -> ActualizarPaquete();
                        case 5 -> EliminarPaquete();
                        case 0 -> System.out.println("Saliendo...");
                        default -> System.out.println("Opción inválida.");
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
        //Empresa.RegistrarPaquetes(PaqueteEnviar);
        PaqueteDAO.insertar(PaqueteEnviar);
    }

    public static void MostrarReporte(){
        List<Paquete> lista = PaqueteDAO.obtenerTodos();
        double total = 0;
        if(lista.isEmpty()){
            System.out.println("No existen paquetes registrados.");
            return;
        }
        System.out.println("====================================================================================");
        System.out.printf("%-5s %-20s %-10s %-18s %-10s%n",
                "ID","DESTINATARIO","PESO","TIPO","COSTO");
        System.out.println("====================================================================================");
        for(Paquete p : lista){
            System.out.printf("%-5d %-20s %-10.2f %-18s $%-10.2f%n",
                    p.getId(),
                    p.getNombreDestinatario(),
                    p.getPesoKg(),
                    p.getEstrategia(),
                    p.obtenerCostoEnvio());
            total += p.obtenerCostoEnvio();
        }
        System.out.println("====================================================================================");
        System.out.printf("TOTAL RECAUDADO: $%.2f%n", total);
    }

    public static void BuscarPaquete(){
        System.out.print("Ingrese el ID del paquete: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        Paquete paquete = PaqueteDAO.buscarPorId(id);
        if(paquete == null){
            System.out.println("No existe un paquete con ese ID.");
        }else{
            System.out.println("--------------------------------------");
            System.out.println("ID: " + paquete.getId());
            System.out.println("Destinatario: " + paquete.getNombreDestinatario());
            System.out.println("Peso: " + paquete.getPesoKg());
            System.out.println("Tipo: " + paquete.getEstrategia());
            System.out.println("Costo: $" + paquete.obtenerCostoEnvio());
            System.out.println("--------------------------------------");
        }
    }
    public static void EliminarPaquete(){
        System.out.print("Ingrese el ID del paquete a eliminar: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        PaqueteDAO.eliminar(id);
    }

    public static void ActualizarPaquete(){
        System.out.print("Ingrese el ID del paquete: ");
        int id = teclado.nextInt();
        teclado.nextLine();
        Paquete paquete = PaqueteDAO.buscarPorId(id);
        if(paquete == null){
            System.out.println("No existe ese paquete.");
            return;
        }
        System.out.print("Nuevo destinatario: ");
        paquete.setNombreDestinatario(teclado.nextLine());
        System.out.print("Nuevo peso: ");
        paquete.setPesoKg(teclado.nextDouble());
        teclado.nextLine();
        System.out.println("Tipo de envío:");
        System.out.println("1) Estándar");
        System.out.println("2) Express");
        System.out.println("3) Internacional");
        int opcion = teclado.nextInt();
        teclado.nextLine();
        switch(opcion){
            case 1 -> paquete.setEstrategia(new EnvioEstandar());
            case 2 -> paquete.setEstrategia(new EnvioExpress());
            case 3 ->paquete.setEstrategia(new EnvioInternacional());
            default -> System.out.println("Opción inválida.");
        }
        PaqueteDAO.actualizar(paquete);
    }
}