package TrabalhoFinalPOO.Modelos;

public class Tratamento extends HistoricoMedico{
    private String tipoTratamento;
    private int duracao;

    public Tratamento(String nomeAnimal, String tipo, int duracao, String data){
        super(nomeAnimal, data);
        this.tipoTratamento = tipo;
        this.duracao = duracao;
    }

    @Override 
    public String toString(){
        return "Nome do animal: " + getNomeAnimal() + ".\nTratamento de " + this.tipoTratamento + " com duração de " + this.duracao + " dias, iniciado em " + getData() + ".";
    }
}
