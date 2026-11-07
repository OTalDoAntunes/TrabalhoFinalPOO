package TrabalhoFinalPOO.Modelos;

import java.util.List;
import java.util.ArrayList;
import TrabalhoFinalPOO.Usuarios.Voluntario;

public class Atividade{
    private String nomeAtividade, tipoAtividade, data;
    private List<Voluntario> voluntariosCadastrados = new ArrayList<>();

    public Atividade(String nomeAtividade, String tipoAtividade, String data){
        this.nomeAtividade = nomeAtividade;
        this.tipoAtividade = tipoAtividade;
        this.data = data;
    }

    public String getNomeAtividade(){
        return this.nomeAtividade;
    }

    public String getData(){
        return this.data;
    }

    public void setNomeAtividade(String nome){
        this.nomeAtividade = nome;
    }

    public void setTipoAtividade(String tipo){
        this.tipoAtividade = tipo;
    }

    public void setData(String data){
        this.data = data;
    }

    public void adiconarVoluntario(Voluntario voluntario){
        this.voluntariosCadastrados.add(voluntario);
    }
    
    @Override 
    public String toString(){
        return "Nome da atividade: " + this.nomeAtividade + ".\nTipo de atividade: " + this.tipoAtividade + ".\nData da atividade: " + this.data + ".";
    }
}
