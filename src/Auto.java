public class Auto extends Vehiculo {
    public Auto(String placa, Integer horasEstacionamiento) {
        super(placa,horasEstacionamiento, new TarifaAuto());
    }


}
