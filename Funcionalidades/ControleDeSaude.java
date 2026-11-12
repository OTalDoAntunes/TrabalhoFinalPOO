package TrabalhoFinalPOO.Funcionalidades;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Cirurgia;
import TrabalhoFinalPOO.Modelos.Consulta;
import TrabalhoFinalPOO.Modelos.Tratamento;
import TrabalhoFinalPOO.Modelos.Vacina;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Usuarios.AdiministradorDoAbrigo;
import TrabalhoFinalPOO.Usuarios.Veterinario;
import TrabalhoFinalPOO.Excecoes.*;

public class ControleDeSaude{
    private static List<Consulta> consultas = new ArrayList<>();
    private static List<Cirurgia> cirurgias = new ArrayList<>();
    private static List<Tratamento> tratamentos =  new ArrayList<>();
    private static List<Vacina> vacinas = new ArrayList<>();
    
    public static List<Consulta> getConsultas(){
        return consultas;
    }

    public static List<Tratamento> getTratamentos(){
        return tratamentos;
    }

    public static  List<Cirurgia> getCirurgias(){
        return cirurgias;
    }

    public static List<Vacina> getVacinas(){
        return vacinas;
    }

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
    
    public static void adicionarVacina(Usuario tipoUsuario, Vacina vacina){
        try{
            if(tipoUsuario instanceof Veterinario){
                vacinas.add(vacina);
            }
            else
                throw new PermissaoInvalidaException("Permissao invalida.");
        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        }
    }

    public static void verHistoricoMedicoCompleto(Usuario tipoUsuario){
        try{
            if(consultas.size() > 0 || tratamentos.size() > 0 || cirurgias.size() > 0 || vacinas.size() > 0){
                if(tipoUsuario instanceof Veterinario || tipoUsuario instanceof AdiministradorDoAbrigo){
                    StringBuilder historicoCompleto = new StringBuilder();
        
                    historicoCompleto.append("Consultas:\n");
                    for(Consulta c : consultas){
                        historicoCompleto.append(c).append("\n------------------------------------\n");
                    }
        
                    historicoCompleto.append("\n\n====================================\n\nTratamentos:\n");
                    for(Tratamento t : tratamentos){
                        historicoCompleto.append(t).append("\n------------------------------------\n");
                    }
        
                    historicoCompleto.append("\n\n====================================\n\nCirurgias:\n");
                    for(Cirurgia c : cirurgias){
                        historicoCompleto.append(c).append("\n------------------------------------\n");
                    }
        
                    historicoCompleto.append("\n\n====================================\n\nVacinas:\n");
                    for(Vacina v : vacinas){
                        historicoCompleto.append(v).append("\n------------------------------------\n");
                    }
        
                    JTextArea area = new JTextArea(historicoCompleto.toString());
                    area.setEditable(false);
            
                    JScrollPane scroll = new JScrollPane(area);
                    scroll.setPreferredSize(new java.awt.Dimension(280, 280));
            
                    JOptionPane.showMessageDialog(null, scroll, "Relatorio de todas as atividades medicas", JOptionPane.INFORMATION_MESSAGE);
                } else
                    throw new PermissaoInvalidaException("Permissão invalida.");

            } else
                JOptionPane.showMessageDialog(null, "Não existem um relatorio medico.");

        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        }
    }
}