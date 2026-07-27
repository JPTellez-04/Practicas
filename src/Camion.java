public class Camion extends Vehiculo{
    public Camion(String placa, Integer horasEstacionamiento) {
        super(placa,horasEstacionamiento, new TarifaCamion());
    }

    //ToString

}
