package TrabalhoFinalPOO.Principal;

import javax.swing.JOptionPane;
import TrabalhoFinalPOO.Funcionalidades.*;

public class Teste{
    public static void main(String args[]){
        try{
            GestaoDeAnimais gestao = new GestaoDeAnimais();
            ProcessoDeAdocao adocao = new ProcessoDeAdocao();

            gestao.cadastrarAnimais("douglas", "cachorro", "labrador", "grande", "saudavel", "12/09/2016", "disponivel", 10);
            gestao.cadastrarAnimais("mario", "gato", "persa", "pequeno", "doente", "30/11/2026", "indisponivel", 6);
        
            //System.out.println("editando elementos");
            //gestao.editarRegistros();
            //System.out.println("Verificando a ediçao e removendo");
            //gestao.editarRegistros();
            //System.out.println("verificando a remocao");
            //gestao.editarRegistros();

            //gestao.buscar();
            adocao.solicitarAdocao(gestao);
            adocao.devolucao(gestao);
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Alguma entrada  é invalida!");

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Algum erro imprevisto aconteceu.");
            
        }
        
    }
}