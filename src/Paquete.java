public abstract class Paquete {
    private String nombreDestinatario = "";
    private double pesoKg = 0.0;
    EstrategiaEnvio Estrategia;
    private int id;

    public Paquete(String nombreDestinatario, double pesoKg, EstrategiaEnvio estrategia) {
        this.nombreDestinatario = nombreDestinatario;
        this.pesoKg = pesoKg;
        this.Estrategia = estrategia;
    }

    public double obtenerCostoEnvio(){

        return Estrategia.calcularCosto(pesoKg);
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public EstrategiaEnvio getEstrategia() {
        return Estrategia;
    }

    public void setEstrategia(EstrategiaEnvio estrategia) {
        Estrategia = estrategia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
