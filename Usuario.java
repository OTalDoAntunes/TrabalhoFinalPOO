package TrabalhoFinalPOO;

public abstract class Usuario{
    private String login, senha, nome, cpf;

    public Usuario(String login, String senha, String nome, String cpf){
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
    }
}