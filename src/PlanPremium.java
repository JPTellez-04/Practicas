public class PlanPremium implements PlanSuscripcion {
    String calidad = "4k Ultra HD";
    Integer pantallas = 4;

    //Methods
    @Override
    public double calcularCosto(int meses) {
        return (14 * meses) + 3;
    }

    @Override
    public String toString() {
        return "Premium";
    }
}