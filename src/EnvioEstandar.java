public class EnvioEstandar implements EstrategiaEnvio{



    @Override
    public double calcularCosto(double pesoKg) {
        return pesoKg * 2.0;
    }
    public boolean esRecargoUnico() {
        return false;
    }
    public String toString() {
        return "Estándar";
    }
}
