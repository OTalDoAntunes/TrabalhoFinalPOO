package TrabalhoFinalPOO.Modelos;

import java.util.List;
import java.util.ArrayList;
import TrabalhoFinalPOO.Excecoes.*;

public abstract class Usuario{
    private String login, senha;
    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String login, String senha)throws LoginJaExistenteException{
        for(Usuario u : usuarios){
            if(login.equalsIgnoreCase(u.getLogin())){
                    throw new LoginJaExistenteException("Ja existe um usuario com esse login.");
            }
        }

        this.login = login;
        this.senha = senha;
    }

    public String getLogin(){
        return this.login;
    }

    public String getSenha(){
        return this.senha;
    }

    public void adicionarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }

    public static List<Usuario> getUsuarios(){
        return usuarios;
    }
}