public class TarifaCamion implements  Tarifa {
    @Override
    public Double calcular(Integer horas) {
        return (double) (horas * 4) + 5;
    }
}
