import java.util.ArrayList;

public class PlataformaStreaming {
    ArrayList<CuentaUsuario> ListaUsuarios = new ArrayList();

    public void RegistrarUsuarios(Usuario User){
        ListaUsuarios.add(User);
    }

}