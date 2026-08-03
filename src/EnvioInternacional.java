public class EnvioInternacional implements EstrategiaEnvio{

    @Override
    public double calcularCosto(double pesoKg) {
        return (pesoKg * 6.0) + 10.0;
    }
    public boolean esRecargoUnico() {
        return true;
    }
    public String toString() {
        return "Internacional";
    }
}
