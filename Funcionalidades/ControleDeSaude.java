package TrabalhoFinalPOO.Funcionalidades;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.HistoricoMedico;
import TrabalhoFinalPOO.Modelos.Cirurgia;
import TrabalhoFinalPOO.Modelos.Consulta;
import TrabalhoFinalPOO.Modelos.Tratamento;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Usuarios.Veterinario;
import TrabalhoFinalPOO.Excecoes.*;

public class ControleDeSaude{
    private static List<Consulta> consultas = new ArrayList<>();
    private static List<Cirurgia> cirurgias = new ArrayList<>();
    private static List<Tratamento> tratamentos =  new ArrayList<>();
    private static List<HistoricoMedico> registroMedicoCompleto = new ArrayList<>();

    public static void adicionarConsulta(Usuario tipoUsuario, Consulta consulta){
        try{
            if(tipoUsuario instanceof Veterinario){
                consultas.add(consulta);
            }
            else
                throw new PermissaoInvalidaException("Permissao invalida.");
        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        }
    }

    public static void adicionarTratamento(Usuario tipoUsuario, Tratamento tratamento){
        try{
            if(tipoUsuario instanceof Veterinario){
                tratamentos.add(tratamento);
            }
            else
                throw new PermissaoInvalidaException("Permissao invalida.");
        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        }
    }

    public static void adicionarCirurgia(Usuario tipoUsuario, Cirurgia cirurgia){
        try{
            if(tipoUsuario instanceof Veterinario){
                cirurgias.add(cirurgia);
            }
            else
                throw new PermissaoInvalidaException("Permissao invalida.");
        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        }
    }
}