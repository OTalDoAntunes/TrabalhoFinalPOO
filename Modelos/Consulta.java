package TrabalhoFinalPOO.Modelos;

import TrabalhoFinalPOO.Excecoes.DataInvalidaException;

public class Consulta extends HistoricoMedico{
    private String nomeVeterinario, relatorio;

    public Consulta(String nome, String relatorio, String data) throws IllegalArgumentException, DataInvalidaException{
        super(data);
        
        if((nome == null || nome.isEmpty() || nome.isBlank()) || (relatorio == null || relatorio.isEmpty() || relatorio.isBlank()))
            throw new IllegalArgumentException();

        this.nomeVeterinario = nome;
        this.relatorio = relatorio;
    }

    @Override 
    public String toString(){
        return "Consulta com: " + this.nomeVeterinario + ".\nFeita em " + getData() + ".\nRelatorio da consulta:\n" + this.relatorio;
    }
}
