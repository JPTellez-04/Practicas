import java.util.ArrayList;
import java.util.List;

public class Estacionamiento {
    private List<Vehiculo> estacionamiento;

    //Constructor
    public Estacionamiento() {
        this.estacionamiento = new ArrayList<Vehiculo>();
    }

    //Methods
    public void registerVehicle(Vehiculo vehiculo) {
        this.estacionamiento.add(vehiculo);
    }

    public void printParkingLot() {
        System.out.println("=========================================");
        System.out.println("            Parking lot");
        System.out.printf("| %-3s | %-12s | %-12s | %-12s | %-12s \n", "ID","Placa","Horas", "Tarifa", "Cobro");
        int i = 1;
        Double tarifaFinal = 0.0;
        for (Vehiculo vehiculo : this.estacionamiento) {
            //System.out.print( "\n"+i + ".- " + vehiculo.toString() + " " + vehiculo.calcularCostoEstacionamiento());
            System.out.printf("| %-3d | %-36s | $%.2f%n", i,vehiculo.toString(), vehiculo.calcularCostoEstacionamiento());
            tarifaFinal += vehiculo.calcularCostoEstacionamiento();
            i++;
        }
        System.out.println("=======================================================");
        System.out.println("El costo total de las tarifas es de: $"+tarifaFinal);
    }

    //GetterAndSetters
    public List<Vehiculo> getEstacionamiento() {
        return estacionamiento;
    }
    public void setEstacionamiento(List<Vehiculo> estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
}
