package TrabalhoFinalPOO.Usuarios;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Modelos.Animal;

public class Voluntario extends Usuario{
    private List<Animal> animaisResponsavel = new ArrayList<>();

    public Voluntario(String login, String senha) throws IllegalArgumentException{
        super(login, senha);
    }

    public static void cadastrar(String login, String senha){
        try{
            Voluntario novoVoluntario = new Voluntario(login, senha);
            //verificar se nao existe login igual por meio de exceção
            novoVoluntario.adicionarUsuario(novoVoluntario);
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        }
        
    }
}