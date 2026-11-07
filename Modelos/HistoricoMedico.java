package TrabalhoFinalPOO.Modelos;

public abstract class HistoricoMedico{
    private String nomeAnimal, data;

    public HistoricoMedico(String nomeAnimal, String data){
        this.nomeAnimal = nomeAnimal;
        this.data = data;
    }

    public String getNomeAnimal(){
        return this.nomeAnimal;
    }
    public String getData(){
        return this.data;
    }
}
