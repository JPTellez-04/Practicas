public class TarifaAuto implements Tarifa{
    @Override
    public Double calcular(Integer horas) {
        return (double) (horas * 2);
    }
}
