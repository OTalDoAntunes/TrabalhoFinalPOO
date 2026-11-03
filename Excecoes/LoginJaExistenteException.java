package TrabalhoFinalPOO.Excecoes;

public class LoginJaExistenteException extends Exception{
    public LoginJaExistenteException(String mensagem){
        super(mensagem);
    }
}
