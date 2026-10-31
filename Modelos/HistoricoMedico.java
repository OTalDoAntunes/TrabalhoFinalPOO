package TrabalhoFinalPOO.Modelos;

import TrabalhoFinalPOO.Excecoes.DataInvalidaException;

public abstract class HistoricoMedico{
    private String data;

    public HistoricoMedico(String data) throws IllegalArgumentException, DataInvalidaException{
        if(data == null || data.isEmpty() || data.isBlank())
            throw new IllegalArgumentException();
        if(DataInvalidaException.validarData(data) == false)
            throw new DataInvalidaException("Data invalida.");

        this.data = data;
    }

    public String getData(){
        return this.data;
    }
}
