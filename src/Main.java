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
                System.out.println("3. Actualizar Vehículo");
                System.out.println("4. Eliminar Vehículo");
                System.out.println("5. Contar Vehículos");
                System.out.println("6. Salir");
                System.out.print("Ingresar desicion: ");
                int opcion = input.nextInt();
                input.nextLine();
                switch (opcion) {
                    case 1 -> {
                        //estacionamiento.registerVehicle(registerVehicle());
                        Vehiculo vehiculo = registerVehicle();
                        estacionamiento.registerVehicle(vehiculo);
                        EstacionamientoDAO.insertar(vehiculo);
                    }
                    case 2 -> estacionamiento.printParkingLot(EstacionamientoDAO.obtenerTodos());
                    case 3 -> actualizarVehiculo();
                    case 4 -> eliminarVehiculo();
                    case 5 -> System.out.println("Total de vehículos: " + EstacionamientoDAO.contar());
                    case 6 -> conditional = false;
                    default -> System.out.println("Opción inválida");
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
                        condicion = false;
                        return new Auto(placa, horas);
                    }
                    case 2 -> {
                        condicion = false;
                        return new Moto(placa, horas);
                    }
                    case 3 -> {
                        condicion = false;
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
    public static void actualizarVehiculo(){
        System.out.print("ID del vehículo: ");
        int id = input.nextInt();
        input.nextLine();
        Vehiculo nuevoVehiculo = registerVehicle();
        EstacionamientoDAO.actualizar(id, nuevoVehiculo);
    }
    public static void eliminarVehiculo(){
        System.out.print("ID del vehículo a eliminar: ");
        int id = input.nextInt();
        input.nextLine();
        EstacionamientoDAO.eliminar(id);
    }
}