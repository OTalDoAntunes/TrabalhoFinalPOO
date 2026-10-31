package TrabalhoFinalPOO.Funcionalidades;

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import TrabalhoFinalPOO.Modelos.Animal;

public class ProcessoDeAdocao{
    
    public void solicitarAdocao(GestaoDeAnimais gerenciador){
        List<Animal> disponiveis = new ArrayList<>();

        for(Animal a : gerenciador.getRegistrosAnimais()){
            if(a.getStatusAdocao().trim().equalsIgnoreCase("Disponivel") || a.getStatusAdocao().trim().equalsIgnoreCase("Disponível"))
                disponiveis.add(a);
        }

        gerenciador.mostrarRegistros(disponiveis);
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
}
