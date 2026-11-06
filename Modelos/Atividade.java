package TrabalhoFinalPOO.Modelos;

public class Atividade{
    private String nomeAtividade, tipoAtividade, data;
    private boolean disponivel;

    public Atividade(String nomeAtividade, String tipoAtividade, String data, boolean diponibilidade){
        this.nomeAtividade = nomeAtividade;
        this.tipoAtividade = tipoAtividade;
        this.data = data;
        this.disponivel = diponibilidade;
    }

    public String getNomeAtividade(){
        return this.nomeAtividade;
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

    public void setDisponibilidade(boolean disponibilidade){
        this.disponivel = disponibilidade;
    }

    @Override 
    public String toString(){
        return "Nome da atividade: " + this.nomeAtividade + ".\nTipo de atividade: " + this.tipoAtividade + ".\nData da atividade: " + this.data + ".\nDisponibilidade: " + ((disponivel == true) ? "Inscrições disponíveis." : "Inscrições indisponíveis.");
    }
}
