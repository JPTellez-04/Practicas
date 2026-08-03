import java.util.ArrayList;

public class EmpresaMensajeria {
    ArrayList<Paquete> PaquetesRegistrados = new ArrayList<>();

    public void RegistrarPaquetes(Paquete P){
        PaquetesRegistrados.add(P);
    }
}
