package TrabalhoFinalPOO.Usuarios;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Modelos.Animal;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.GestaoDeAnimais;

public class Voluntario extends Usuario{
    private List<Animal> animaisResponsavel = new ArrayList<>();

    public Voluntario(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    }

    public static void cadastrar(){
        try{
            String login = JOptionPane.showInputDialog("Crie um login.");
            if(login == null || login.isEmpty() || login.isBlank())
                throw new IllegalArgumentException();
            String senha = JOptionPane.showInputDialog("Crie uma senha.");
            if(senha == null || senha.isEmpty() || senha.isBlank())
                throw new IllegalArgumentException();

            Voluntario novoVoluntario = new Voluntario(login, senha);
            novoVoluntario.adicionarUsuario(novoVoluntario);
            
            JOptionPane.showMessageDialog(null, "Voce se cadastrou no sistema como voluntario.");
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(LoginJaExistenteException LJEE){
            JOptionPane.showMessageDialog(null, "Já existe um usuario com esse login.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        }
        
    }

    public void verAnimaisDisponiveis(GestaoDeAnimais gerenciador){
        List<Animal> disponiveis = new ArrayList<>();

        for(Animal a : gerenciador.getRegistrosAnimais()){
            if(a.getStatusAdocao().equalsIgnoreCase("disponivel") || a.getStatusAdocao().equalsIgnoreCase("disponível"))
                disponiveis.add(a);
        }
        
        gerenciador.mostrarRegistros(disponiveis);
    }
}