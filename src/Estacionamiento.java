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

    public void printParkingLot(List<Vehiculo> vehiculos){

        System.out.println("==============================================================");
        System.out.printf("| %-3s | %-12s | %-8s | %-8s | %-10s |%n",
                "ID","Placa","Horas","Tipo","Costo");
        int id = 1;
        double total = 0;
        for(Vehiculo v : vehiculos){
            double costo = v.calcularCostoEstacionamiento();
            System.out.printf("| %-3d | %-12s | %-8d | %-8s | $%-9.2f |%n",
                    id++,
                    v.getPlaca(),
                    v.getHorasEstacionado(),
                    v.getClass().getSimpleName(),
                    costo);
            total += costo;
        }
        System.out.println("--------------------------------------------------------------");
        System.out.printf("TOTAL: $%.2f%n", total);
    }

    //GetterAndSetters
    public List<Vehiculo> getEstacionamiento() {
        return estacionamiento;
    }
    public void setEstacionamiento(List<Vehiculo> estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
}
