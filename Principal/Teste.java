package TrabalhoFinalPOO.Principal;

import javax.swing.JOptionPane;
import TrabalhoFinalPOO.Funcionalidades.*;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Usuarios.AdiministradorDoAbrigo;
import TrabalhoFinalPOO.Usuarios.Veterinario;
import TrabalhoFinalPOO.Usuarios.Voluntario;

public class Teste{
    public static void main(String args[]){
        try{
            GestaoDeAnimais gestao = new GestaoDeAnimais();
            ProcessoDeAdocao adocao = new ProcessoDeAdocao();

            Usuario voluntario = new Voluntario("Antunes", "400678");
            Usuario veterinario = new Veterinario("Jorge", "hjJSsGJ");
            Usuario adm = new AdiministradorDoAbrigo("Geraldao", "OitoOitoMeiaSete");

            gestao.cadastrarAnimais(voluntario);
            gestao.cadastrarAnimais(veterinario);
            gestao.cadastrarAnimais(adm);
            //System.out.println("editando elementos");
            //gestao.editarRegistros();
            //System.out.println("Verificando a ediçao e removendo");
            //gestao.editarRegistros();
            //System.out.println("verificando a remocao");
            //gestao.editarRegistros();

            //gestao.buscar();
            //adocao.solicitarAdocao(gestao);
            //adocao.devolucao(gestao);
            Voluntario.cadastrar();
            Voluntario.cadastrar();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Algum erro imprevisto aconteceu.");
            
        }
        
    }
}