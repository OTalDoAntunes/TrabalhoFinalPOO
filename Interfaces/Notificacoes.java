package TrabalhoFinalPOO.Interfaces;

import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Usuarios.AdiministradorDoAbrigo;
import TrabalhoFinalPOO.Usuarios.Veterinario;
import TrabalhoFinalPOO.Usuarios.Voluntario;

public interface Notificacoes {
    public static void notificarVoluntarios(String mensagem){
        for(Usuario u : Usuario.getUsuarios()){
            if(u instanceof Voluntario){
                ((Voluntario)u).getNotificacoes().add(mensagem);
            }
        }
    }

    public static void notificarVeterinarios(String mensagem){
        for(Usuario u : Usuario.getUsuarios()){
            if(u instanceof Veterinario){
                ((Veterinario)u).getNotificacoes().add(mensagem);
            }
        }
    }

    public static void notificarAdiministrador(String mensagem){
        for(Usuario u : Usuario.getUsuarios()){
            if(u instanceof AdiministradorDoAbrigo){
                ((AdiministradorDoAbrigo)u).getNotificacoes().add(mensagem);
            }
        }
    }

    public static void notificarUsuario(String mensagem, Usuario usuario){
        for(Usuario u : Usuario.getUsuarios()){
            if(u == usuario){
                if(usuario instanceof Voluntario){
                    ((Voluntario)usuario).getNotificacoes().add(mensagem);
                }
            }
            else if(u == usuario){
                if(usuario instanceof Veterinario){
                    ((Veterinario)usuario).getNotificacoes().add(mensagem);
                }
            }
            else if(u == usuario){
                if(usuario instanceof AdiministradorDoAbrigo){
                    ((AdiministradorDoAbrigo)usuario).getNotificacoes().add(mensagem);
                }
            }
        }
    }

    public static void verNotificacoes(Usuario tipoUsuario){
        if(tipoUsuario instanceof Voluntario){
            if(((Voluntario)tipoUsuario).getNotificacoes().size() != 0){
                StringBuilder notificacoes = new StringBuilder();

                for(int i = 0; i < ((Voluntario)tipoUsuario).getNotificacoes().size(); i++){
                     notificacoes.append(i + 1).append(" - ").append(((AdiministradorDoAbrigo)tipoUsuario).getNotificacoes().get(i)).append("\n-------------------------------------------------\n");
                }

                JTextArea area = new JTextArea(notificacoes.toString());
                area.setEditable(false);

                JScrollPane scroll = new JScrollPane(area);
                scroll.setPreferredSize(new java.awt.Dimension(200, 200));

                JOptionPane.showMessageDialog(null, scroll, "Notificações recebidas.", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Voce não tem notificações.");
        }
        else if(tipoUsuario instanceof Veterinario){
            if(((Veterinario)tipoUsuario).getNotificacoes().size() != 0){
                StringBuilder notificacoes = new StringBuilder();

                for(int i = 0; i < ((Veterinario)tipoUsuario).getNotificacoes().size(); i++){
                     notificacoes.append(i + 1).append(" - ").append(((AdiministradorDoAbrigo)tipoUsuario).getNotificacoes().get(i)).append("\n-------------------------------------------------\n");
                }

                JTextArea area = new JTextArea(notificacoes.toString());
                area.setEditable(false);

                JScrollPane scroll = new JScrollPane(area);
                scroll.setPreferredSize(new java.awt.Dimension(200, 200));

                JOptionPane.showMessageDialog(null, scroll, "Notificações recebidas.", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Voce não tem notificações.");
        }
        else if(tipoUsuario instanceof AdiministradorDoAbrigo){
            if(((AdiministradorDoAbrigo)tipoUsuario).getNotificacoes().size() != 0){
                StringBuilder notificacoes = new StringBuilder();

                for(int i = 0; i < ((AdiministradorDoAbrigo)tipoUsuario).getNotificacoes().size(); i++){
                    notificacoes.append(i + 1).append(" - ").append(((AdiministradorDoAbrigo)tipoUsuario).getNotificacoes().get(i)).append("\n-------------------------------------------------\n");
                }

                JTextArea area = new JTextArea(notificacoes.toString());
                area.setEditable(false);

                JScrollPane scroll = new JScrollPane(area);
                scroll.setPreferredSize(new java.awt.Dimension(200, 200));

                JOptionPane.showMessageDialog(null, scroll, "Notificações recebidas.", JOptionPane.INFORMATION_MESSAGE);
            }
            else
                JOptionPane.showMessageDialog(null, "Voce não tem notificações.");
        }
    }

    public static void apagarNotificacoes(Usuario usuario){
        if(usuario instanceof Voluntario){
            if(((Voluntario)usuario).getNotificacoes().size() != 0){
                ((Voluntario)usuario).getNotificacoes().clear();
            }
            else
                JOptionPane.showMessageDialog(null, "Não existem notificações para serem apagadas.");
        }
        else if(usuario instanceof Veterinario){
            if(((Veterinario)usuario).getNotificacoes().size() != 0){
                ((Veterinario)usuario).getNotificacoes().clear();
            }
            else
                JOptionPane.showMessageDialog(null, "Não existem notificações para serem apagadas.");
        }
        else if(usuario instanceof AdiministradorDoAbrigo){
            if(((AdiministradorDoAbrigo)usuario).getNotificacoes().size() != 0){
                ((AdiministradorDoAbrigo)usuario).getNotificacoes().clear();
            }
            else
                JOptionPane.showMessageDialog(null, "Não existem notificações para serem apagadas.");
        }
    }
}