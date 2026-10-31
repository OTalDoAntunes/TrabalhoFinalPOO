package TrabalhoFinalPOO.Modelos;

import TrabalhoFinalPOO.Excecoes.DataInvalidaException;

public class Cirurgia extends HistoricoMedico{
    private String tipoCirurgia;

    public Cirurgia(String tipo, String data) throws IllegalArgumentException, DataInvalidaException{
        super(data);
        if(tipo == null || tipo.isEmpty() || tipo.isBlank())
            throw new IllegalArgumentException();

        this.tipoCirurgia = tipo;
    }

    @Override 
    public String toString(){
        return "Cicurgia de " + this.tipoCirurgia + ". Realizada em " + getData() + ".";
    }
}
