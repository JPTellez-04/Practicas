import java.util.Scanner;

class Main{
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        Estacionamiento estacionamiento = new Estacionamiento();
        menu(estacionamiento);
    }
    public static void menu(Estacionamiento estacionamiento){
        boolean conditional = true;
        while(conditional) {
            try {
                System.out.println("Menu");
                System.out.println("1. Agregar Vehiculo");
                System.out.println("2. Mostrar Reporte");
                System.out.println("3. Salir");
                System.out.print("Ingresar desicion: ");
                int opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1 -> {
                        estacionamiento.registerVehicle(registerVehicle());
                        Vehiculo vehiculo = registerVehicle();
                        estacionamiento.registerVehicle(vehiculo);
                        EstacionamientoDAO.insertar(vehiculo);
                    }
                    case 2 -> estacionamiento.printParkingLot();
                    case 3 -> conditional = false;
                    default -> System.out.println("Invalid Option");
                }
            } catch (Exception e) {
                System.out.println("Error, vuelve a ingresar");
            }
        }
    }
    public static Vehiculo registerVehicle(){
        try{
            System.out.print("Ingresar la placa del vehiculo: ");
            String placa = input.nextLine();
            System.out.print("Ingresar las horas que lleva en el estamcionameinto el vehiculo: ");
            Integer horas = input.nextInt();
            input.nextLine();
            boolean condicion = true;
            while(condicion) {
                System.out.println("Tipos de Vehiculos ");
                System.out.println("1. Auto - 2.Moto - 3.Camion");
                System.out.print("Ingresa tu desicion: ");
                int opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1 -> {
                        return new Auto(placa, horas);
                    }
                    case 2 -> {
                        return new Moto(placa, horas);
                    }
                    case 3 -> {
                        return new Camion(placa, horas);
                    }
                    default -> System.out.println("Opcion invalida");

                }
            }
        }catch(Exception e){
            System.out.println("Error, vuelve a ingresar");
        }
        return null;
    }
}