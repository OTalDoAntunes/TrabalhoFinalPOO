package TrabalhoFinalPOO.Modelos;

public class Consulta extends HistoricoMedico{
    private String nomeVeterinario, relatorio;

    public Consulta(String nomeVeterinario, String nomeAnimal, String relatorio, String data){
        super(nomeAnimal, data);
        this.nomeVeterinario = nomeVeterinario;
        this.relatorio = relatorio;
    }

    public void setNomeVeterinario(String nome){
        this.nomeVeterinario = nome;
    }

    public void setRelatorio(String relatorio){
        this.relatorio = relatorio;
    }

    @Override 
    public String toString(){
        return "Nome do animal: " + getNomeAnimal() + ".\nConsulta com: " + this.nomeVeterinario + ".\nFeita em " + getData() + ".\nRelatorio da consulta:\n" + this.relatorio;
    }
}
