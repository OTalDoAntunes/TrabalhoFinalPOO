package TrabalhoFinalPOO.Usuarios;

import TrabalhoFinalPOO.Excecoes.LoginJaExistenteException;
import TrabalhoFinalPOO.Modelos.Usuario;

public class AdiministradorDoAbrigo extends  Usuario{
    public AdiministradorDoAbrigo(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    }    
}