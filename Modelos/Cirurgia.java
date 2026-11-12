package TrabalhoFinalPOO.Modelos;

public class Cirurgia extends HistoricoMedico{
    private String tipoCirurgia;

    public Cirurgia(String nomeAnimal, String tipo, String data){
        super(nomeAnimal, data);
        this.tipoCirurgia = tipo;
    }

    public void setTipoCirurgia(String tipo){
        this.tipoCirurgia = tipo;
    }
    @Override 
    public String toString(){
        return "Nome do animal: " + getNomeAnimal() + ".\nCicurgia de " + this.tipoCirurgia + ". Realizada em " + getData() + ".";
    }
}
