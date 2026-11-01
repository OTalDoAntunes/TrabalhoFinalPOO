package TrabalhoFinalPOO.Modelos;

import java.util.List;
import java.util.ArrayList;

public abstract class Usuario{
    private String login, senha;
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String login, String senha) throws IllegalArgumentException{
        if((login == null || login.isEmpty() || login.isBlank()) || (senha == null || senha.isEmpty() || senha.isBlank()))
            throw new IllegalArgumentException();

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
        this.usuarios.add(usuario);
    }
}