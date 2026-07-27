public class Vehiculo {
    private String placa;
    private Integer horasEstacionado;
    private Tarifa tarifa;

    //Constructors
    public Vehiculo(String placa, Integer horasEstacionado, Tarifa tarifa) {
        this.placa = placa;
        this.horasEstacionado = horasEstacionado;
        this.tarifa = tarifa;
    }

    //Methods
    public Double calcularCostoEstacionamiento(){
        return tarifa.calcular(horasEstacionado);
    }

    //ToString

    @Override
    public String toString(){
        return String.format("%-12s | %-12d | %-12s",
                placa,
                horasEstacionado,
                getClass().getSimpleName());
    }

    //GettersAndSetters
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Integer getHorasEstacionado() {
        return horasEstacionado;
    }
    public void setHorasEstacionado(Integer horasEstacionado) {
        this.horasEstacionado = horasEstacionado;
    }
}
