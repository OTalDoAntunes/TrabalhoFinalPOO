package TrabalhoFinalPOO.Modelos;

public class Vacina extends HistoricoMedico{
    private String medicamento;

    public Vacina(String nomeAnimal, String data, String medicamento){
        super(nomeAnimal, data);
        this.medicamento = medicamento;
    }

    public String getMedicamento(){
        return this.medicamento;
    }

    public void setMedicamento(String medicamento){
        this.medicamento = medicamento;
    }

    @Override 
    public String toString(){
        return "Nome do animal: " + getNomeAnimal() + ".\nVacina utilizada: " + this.medicamento + ".\nData de aplicação: " + getData() + ".";
    }
}
