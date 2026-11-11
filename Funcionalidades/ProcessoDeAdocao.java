package TrabalhoFinalPOO.Funcionalidades;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import TrabalhoFinalPOO.Modelos.Animal;
import TrabalhoFinalPOO.Excecoes.*;

public class ProcessoDeAdocao{
    public static void solicitarAdocao(){
        List<Animal> disponiveis = new ArrayList<>();

        for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
            if(a.getStatusAdocao().trim().equalsIgnoreCase("Disponivel") || a.getStatusAdocao().trim().equalsIgnoreCase("Disponível"))
                disponiveis.add(a);
        }

        GestaoDeAnimais.mostrarRegistros(disponiveis);
        int numeroRegistro = Integer.parseInt(JOptionPane.showInputDialog("Qual animal deseja adotar?\nInforme o numero do registro.")), i = 1;
        boolean solicitacaoFeita = false;

        for(Animal a : disponiveis){
            if(i == numeroRegistro){
                solicitacaoFeita = true;
                //posivel pedido de adoção pro adm
                JOptionPane.showMessageDialog(null, "Sua solicitação foi enviada com sucesso!");
                break;
            }
        }

        if(solicitacaoFeita == false)
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar um animal com esse numero de registro.");
    }   

    public static void devolucao(GestaoDeAnimais gerenciador){
        try{
            boolean entradaIncorreta, animalEncontrado = false;
            String motivo = JOptionPane.showInputDialog("Informe o motivo da devolução do animal.");
            if(motivo == null || motivo.isEmpty() || motivo.isBlank())
                throw new IllegalArgumentException();

            String nome, especie, raca, porte, estadoDeSaude, dataDeResgate, dataDaAdocao;
            int idade;

            while (animalEncontrado == false) {
                do{
                    entradaIncorreta = false;
                    nome = JOptionPane.showInputDialog("Insira o nome do animal.");
                    if(nome == null || nome.isEmpty() || nome.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    especie = JOptionPane.showInputDialog("Insira a especie do animal.");
                    if(especie == null || especie.isEmpty() || especie.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    raca = JOptionPane.showInputDialog("Insira a raça do animal.");
                    if(raca == null || raca.isEmpty() || raca.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    porte = JOptionPane.showInputDialog("Insira o porte do animal.");
                    if(porte == null || porte.isEmpty() || porte.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    estadoDeSaude = JOptionPane.showInputDialog("Insira o estado de saúde do animal.");
                    if(estadoDeSaude == null || estadoDeSaude.isEmpty() || estadoDeSaude.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    dataDeResgate = JOptionPane.showInputDialog("Insira a data de resgate do animal.\nA data deve estar no formato dia/mes/ano com dia e mes com os dois algarismos e o ano completo.");
                    if(dataDeResgate == null || dataDeResgate.isEmpty() || dataDeResgate.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                    else if(DataInvalidaException.validarData(dataDeResgate) == false){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira a data no formato pedido.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    idade = Integer.parseInt(JOptionPane.showInputDialog("Insira a idade do animal."));
                    if(idade <= 0){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma idade maior que zero.");
                    }
                } while(entradaIncorreta);

                do{
                    entradaIncorreta = false;
                    dataDaAdocao = JOptionPane.showInputDialog("Insira a data em que o animal foi adotado.\nA data deve estar no formato dia/mes/ano com dia e mes com os dois algarismos e o ano completo.");
                    if(dataDaAdocao == null || dataDaAdocao.isEmpty() || dataDaAdocao.isBlank()){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira uma entrada valida.");
                    }
                    else if(DataInvalidaException.validarData(dataDaAdocao) == false){
                        entradaIncorreta = true;
                        JOptionPane.showMessageDialog(null, "Por favor insira a data no formato pedido.");
                    }
                } while(entradaIncorreta);

                for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                    if(a.getNome().equals(nome) && a.getEspecie().equals(especie) && a.getRaca().equals(raca) && a.getPorte().equals(porte) && a.getEstadoSaude().equals(estadoDeSaude) && a.getDataResgate().equals(dataDeResgate) && a.getIdade() == idade && a.getStatusAdocao().equals("indisponivel")){
                        animalEncontrado = true;
                        //colocar dentro da lista de devoluçao
                        a.setStatusAdocao("disponivel");
                        JOptionPane.showMessageDialog(null, "Seu pedido de devolução foi enviado.");
                    }
                }

                if(animalEncontrado == false){
                    if(JOptionPane.showInputDialog("Animal não encontrado, deseja inserir os dados novamente?(s/n)").trim().equalsIgnoreCase("n")){
                        animalEncontrado = true;
                        JOptionPane.showMessageDialog(null, "Pedido de devolução não efetuado.");
                    }
                }
            }
            
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "E necessario informar o motivo da devolução.");

        }
    }
}
