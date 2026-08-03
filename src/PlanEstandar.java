public class PlanEstandar implements PlanSuscripcion {
    String calidad = "Full HD";
    Integer pantallas = 2;

    //Methods
    @Override
    public double calcularCosto(int meses) {
        return 9 * meses;
    }

    @Override
    public String toString() {
        return "Estandar";
    }
}
