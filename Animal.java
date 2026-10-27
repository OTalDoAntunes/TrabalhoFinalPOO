package TrabalhoFinalPOO;

public class Animal{
    private String nome, especie, raca, porte, estadoDeSaude, dataDeResgate, statusDaAdocao;
    private int idade;

    public Animal(String nome, String especie, String raca, String porte, String estadoDeSaude, String dataDeResgate, String statusDaAdocao, int idade) throws IllegalArgumentException, DataInvalidaException, IdadeInvalidaException{
        if((nome == null || nome.isEmpty() || nome.isBlank()) || (especie == null || especie.isEmpty() || especie.isBlank()) || (raca == null || raca.isEmpty() || raca.isBlank()) || (porte == null || porte.isEmpty() || porte.isBlank()) || (estadoDeSaude == null || estadoDeSaude.isEmpty() || estadoDeSaude.isBlank()) || (statusDaAdocao == null || statusDaAdocao.isEmpty() || statusDaAdocao.isBlank()) || (dataDeResgate == null || dataDeResgate.isEmpty() || dataDeResgate.isBlank()))
            throw new IllegalArgumentException();
        if(idade <= 0)
            throw new IdadeInvalidaException("Idade menor ou igual a zero.");
        if(DataInvalidaException.validarData(dataDeResgate) == false)
            throw new DataInvalidaException("Data em um formato invalido.");
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.porte = porte;
        this.estadoDeSaude = estadoDeSaude;
        this.dataDeResgate = dataDeResgate;
        this.statusDaAdocao = statusDaAdocao;
        this.idade = idade;
    }

    public String getNome(){
        return this.nome;
    }
    public String getEspecie(){
        return this.especie;
    }

    public String getRaca(){
        return this.raca;
    }

    public String getPorte(){
        return this.porte;
    }

    public String getEstadoSaude(){
        return this.estadoDeSaude;
    }

    public String getDataResgate(){
        return this.dataDeResgate;
    }

    public String getStatusAdocao(){
        return this.statusDaAdocao;
    }

    public int getIdade(){
        return this.idade;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEspecie(String especie){
        this.especie = especie;
    }

    public void setRaca(String raca){
        this.raca = raca;
    }

    public void setPorte(String porte){
        this.porte = porte;
    }

    public void setEstadoSaude(String saude){
        this.estadoDeSaude = saude;
    }

    public void setDataResgate(String data){
        this.dataDeResgate = data;
    }

    public void setStatusAdocao(String status){
        this.statusDaAdocao = status;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    @Override 
    public String toString(){
        return "Nome: " + this.nome + ".\nEspecie: " + this.especie + ".\nRaça: " + this.raca + ".\nPorte: " + this.porte + ".\nEstado de saúde: " + this.estadoDeSaude + ".\nData de resgate: " + this.dataDeResgate + ".\nStatus da adoção: " + this.statusDaAdocao + ".\nIdade: " + this.idade + ".";
    }
}
