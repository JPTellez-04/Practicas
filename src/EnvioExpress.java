public class EnvioExpress implements EstrategiaEnvio {


    @Override
    public double calcularCosto(double pesoKg) {
        return (pesoKg * 4.5) + 3.0;
    }
    public boolean esRecargoUnico() {
        return true;
    }
    public String toString() {
        return "Express";
    }
}
