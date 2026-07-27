public class Moto extends Vehiculo{
    public Moto(String placa, Integer horasEstacionamiento) {
        super(placa,horasEstacionamiento, new TarifaMoto());
    }

    //ToString

}
