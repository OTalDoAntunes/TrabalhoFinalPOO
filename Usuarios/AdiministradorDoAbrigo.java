package TrabalhoFinalPOO.Usuarios;

import javax.swing.*;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Modelos.*;
import TrabalhoFinalPOO.Funcionalidades.*;
import TrabalhoFinalPOO.Usuarios.*;

public class AdiministradorDoAbrigo extends  Usuario{
    public AdiministradorDoAbrigo(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    } 

    public void gerenciarAnimais(){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual ação deseja realizar.\n1 - Cadastrar animais.\n2 - Editar registros."));

            if(opcao == 1){
                GestaoDeAnimais.cadastrarAnimais(this);
            }
            else if(opcao == 2){
                GestaoDeAnimais.editarRegistros(this);
            }
            else{
                JOptionPane.showMessageDialog(null, "Opção invalida.");
            }
    }

    public void gerenciarUsuarios(){
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual operação deseja realizar.\n1 - Editar informações.\n2 - Deletar um usuario."));

        if(opcao == 1){
            //adicionar mais informações aos usuario e notifica-los
        }
        else if(opcao == 2){
            try{
                if(Usuario.getUsuarios().size() != 0){
                    StringBuilder todosUsuarios = new StringBuilder();
    
                    for(Usuario u : Usuario.getUsuarios()){
                        todosUsuarios.append(u).append("\n-------------------------------------------\n");
                    }
    
                    JTextArea area = new JTextArea(todosUsuarios.toString());
                    area.setEditable(false);
    
                    JScrollPane scroll = new JScrollPane(area);
                    scroll.setPreferredSize(new java.awt.Dimension(280, 280));
    
                    JOptionPane.showMessageDialog(null, scroll, "Usuarios cadastrados no sistema.", JOptionPane.INFORMATION_MESSAGE);
    
                    String loginAlvo = JOptionPane.showInputDialog("Insira o login do usuario que voce deseja remover:").trim();
                    if(loginAlvo == null || loginAlvo.isEmpty() || loginAlvo.isBlank())
                        throw new IllegalArgumentException();

                    for(Usuario u : Usuario.getUsuarios()){
                        if(u.getLogin().equals(loginAlvo)){
                            Usuario.getUsuarios().remove(u);
                            JOptionPane.showMessageDialog(null, "Usuario removido.");
                            break;
                        }
                    }
                }
            } catch(IllegalArgumentException IAE){
                JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void gerenciarEventos(){
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual operação deseja realizar.\n1 - Cadastrar novos eventos.\n2 - Editar eventos."));

        if(opcao == 1){
            AgendamentoDeAtividades.cadastrarAtividade(this);
        }
        else if(opcao == 2){
            AgendamentoDeAtividades.editarAtividade(this);
        }
        else
            JOptionPane.showMessageDialog(null, "Opção invalida.");
    }
}