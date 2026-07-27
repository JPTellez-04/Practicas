public class TarifaMoto implements Tarifa {
    @Override
    public Double calcular(Integer horas) {
        return (double) (horas);
    }
}
