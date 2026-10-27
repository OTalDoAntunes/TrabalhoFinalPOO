package TrabalhoFinalPOO;

import javax.swing.JOptionPane;

public class Teste{
    public static void main(String args[]){
        try{
            GestaoDeAnimais gestao = new GestaoDeAnimais();

            gestao.cadastrarAnimais("douglas", "cachorro", "labrador", "grande", "saudavel", "12/09/2016", "Adotado", 10);
            gestao.cadastrarAnimais("mario", "gato", "persa", "pequeno", "doente", "30/11/2026", "nao adotado", 6);
            System.out.println("editando elementos");
            gestao.editarRegistros();
            System.out.println("Verificando a ediçao e removendo");
            gestao.editarRegistros();
            System.out.println("verificando a remocao");
            gestao.editarRegistros();
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Alguma entrada  é invalida!");

        } catch(IdadeInvalidaException IIE){
            JOptionPane.showMessageDialog(null, "A idade precisa ser maior que zero.");

        } catch(DataInvalidaException DIE){
            JOptionPane.showMessageDialog(null, "A data deve estar no formato dia/mes/ano, com o dia e o mes contendo dois algarismos.");

        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Algum erro imprevisto aconteceu.");
        }
        
    }
}