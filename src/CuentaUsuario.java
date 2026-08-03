public abstract class CuentaUsuario {
    private String CorreoElectronico = "";
    private int MesesActivo = 0;
    private PlanSuscripcion Plan;
    private int id;

    public CuentaUsuario(String correoElectronico, int mesesActivo, PlanSuscripcion plan) {
        CorreoElectronico = correoElectronico;
        MesesActivo = mesesActivo;
        Plan = plan;
    }
    private CuentaUsuario(int id, String correoElectronico, int mesesActivo, PlanSuscripcion plan) {
        this.id = id;
        CorreoElectronico = correoElectronico;
        MesesActivo = mesesActivo;
        Plan = plan;
    }

    public double ObtenerTotalAPagar(){
        return Plan.calcularCosto(MesesActivo);
    }


    public String getCorreoElectronico() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        CorreoElectronico = correoElectronico;
    }

    public int getMesesActivo() {
        return MesesActivo;
    }

    public void setMesesActivo(int mesesActivo) {
        MesesActivo = mesesActivo;
    }

    public PlanSuscripcion getPlan() {
        return Plan;
    }

    public void setPlan(PlanSuscripcion plan) {
        Plan = plan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}