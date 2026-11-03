package TrabalhoFinalPOO.Usuarios;

import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Excecoes.*;

public class Veterinario extends Usuario{
    public Veterinario(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    }

    
}