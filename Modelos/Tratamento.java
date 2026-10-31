package TrabalhoFinalPOO.Modelos;

import TrabalhoFinalPOO.Excecoes.DataInvalidaException;
import TrabalhoFinalPOO.Excecoes.DuracaoInvalidaException;

public class Tratamento extends HistoricoMedico{
    private String tipoTratamento;
    private int duracao;

    public Tratamento(String tipo, int duracao, String data) throws IllegalArgumentException, DataInvalidaException, DuracaoInvalidaException{
        super(data);

        if(tipo == null || tipo.isEmpty() || tipo.isBlank())
            throw new IllegalArgumentException();
        if(duracao <= 0)
            throw new DuracaoInvalidaException("A duração deve ser maior que zero.");
        
        this.tipoTratamento = tipo;
        this.duracao = duracao;
    }

    @Override 
    public String toString(){
        return "Tratamento de " + this.tipoTratamento + " com duração de " + this.duracao + " dias, iniciado em " + getData() + ".";
    }
}
