public class PlanBasico implements PlanSuscripcion{
    String calidad = "SD";
    Integer pantallas = 1;

    //Methods
    @Override
    public double calcularCosto(int meses) {
        return 5 * meses;
    }

    @Override
    public String toString() {
        return "Básico";
    }
}