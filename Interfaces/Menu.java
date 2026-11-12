package TrabalhoFinalPOO.Interfaces;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import TrabalhoFinalPOO.Modelos.*;
import TrabalhoFinalPOO.Usuarios.*;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.*;

public interface Menu{
    public static void menuPrincipal(){
        int opcao;
        try{
            AdiministradorDoAbrigo adm = new AdiministradorDoAbrigo("adm", "LJk315mdf");
            Usuario.getUsuarios().add(adm);
        } catch(LoginJaExistenteException LJEE){}

        do{
            opcao = Integer.parseInt(JOptionPane.showInputDialog("==========Menu  principal==========\n1 - Fazer login.\n2 - Cadastra-se.\n3 - Ver animais disponiveis.\n4 - Sobre o sistema.\n0 - Sair.\nEscolha uma opção:"));
            
            if(opcao == 1){
                try{
                    boolean encontrado = false;

                    String login = JOptionPane.showInputDialog("Insira seu login:").trim();
                    if(login == null || login.isEmpty() || login.isBlank())
                        throw new IllegalArgumentException();

                    String senha = JOptionPane.showInputDialog("Insira sua senha.").trim();
                    if(senha == null || senha.isEmpty() || senha.isBlank())
                        throw new IllegalArgumentException();
        
                    for(Usuario u : Usuario.getUsuarios()){
                        if(u.getLogin().equals(login)){
                            encontrado = true;
                            if(u.getSenha().equals(senha)){
                                if(u instanceof Voluntario){
                                    int opcaoVoluntario;
                                    do{
                                        opcaoVoluntario = Integer.parseInt(JOptionPane.showInputDialog("==========Menu voluntario==========\n1 - Ver animais disponiveis.\n2 - Solicitar adoção.\n3 - Consultar solicitações.\n4 - Participar de eventos.\n5 - Historico de participações.\n6 - Notificações.\n0 - Sair.\nEscolha uma opção:"));
    
                                        if(opcaoVoluntario == 1){
                                            ((Voluntario)u).verAnimaisDisponiveis();
                                        }
                                        else if(opcaoVoluntario == 2){
                                            ((Voluntario)u).solicitarAdocao();
                                        }
                                        else if(opcaoVoluntario == 3){
                                            ((Voluntario)u).verSolicitacoes();
                                        }
                                        else if(opcaoVoluntario == 4){
                                            ((Voluntario)u).candidatarAtividades();
                                        }
                                        else if(opcaoVoluntario == 5){
                                            ((Voluntario)u).verHistoricoParticipacoes();
                                        }
                                        else if(opcaoVoluntario == 6){
                                            Notificacoes.verNotificacoes(u);
                                            if(JOptionPane.showInputDialog("Deseja apagar TODAS as notificações?(s/n)").trim().equalsIgnoreCase("s"))
                                                Notificacoes.apagarNotificacoes(u);
                                        }
                                        else if(opcaoVoluntario == 0){
                                            JOptionPane.showMessageDialog(null, "Deslogado.");
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Opção invalida.");
                                        }

                                    } while(opcaoVoluntario != 0);
                                    break;
                                }
                                else if(u instanceof Veterinario){
                                    int opcaoVeterinario;

                                    do{
                                        opcaoVeterinario = Integer.parseInt(JOptionPane.showInputDialog("==========Menu veterinario==========\n1 - Consultar animais\n2 - Registrar atividades medicas.\n3 - Atualizar atividades medicas.\n4 - Emitir relatorio medico.\n5 - Notificações.\n0 - Sair.\nEscolha uma opção:"));

                                        if(opcaoVeterinario == 1){
                                            try{
                                                int metodoVizualizacao = Integer.parseInt("Como deseja ver os animais:\n1 - Ver todos os animais.\n2 - Buscar por um animal em especifico.");

                                                if(metodoVizualizacao == 1){
                                                    GestaoDeAnimais.mostrarRegistros(GestaoDeAnimais.getRegistrosAnimais());
                                                }
                                                else if(metodoVizualizacao == 2){
                                                    GestaoDeAnimais.buscar();
                                                }
                                                else{
                                                    JOptionPane.showMessageDialog(null, "Opção invalida.");
                                                }
                                            } catch(IllegalArgumentException IAE){
                                                JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                            }
                                        }
                                        else if(opcaoVeterinario == 2){
                                            ((Veterinario)u).registraAtividadesMedicas();
                                        }
                                        else if(opcaoVeterinario == 3){
                                            ((Veterinario)u).editarAtividadesMedicas();
                                        }
                                        else if(opcaoVeterinario == 4){
                                            ControleDeSaude.verHistoricoMedicoCompleto(u);
                                        }
                                        else if(opcaoVeterinario == 5){
                                            Notificacoes.verNotificacoes(u);
                                            if(JOptionPane.showInputDialog("Deseja apagar TODAS as notificações?(s/n)").trim().equalsIgnoreCase("s"))
                                                Notificacoes.apagarNotificacoes(u);
                                        }
                                        else if(opcaoVeterinario == 0){
                                            JOptionPane.showMessageDialog(null, "Deslogado.");
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Opção inexistente.");
                                        }
                                    } while(opcaoVeterinario != 0);
                                    break;
                                }
                                else if(u instanceof AdiministradorDoAbrigo){
                                    int opcaoAdm;

                                    do{
                                        opcaoAdm = Integer.parseInt(JOptionPane.showInputDialog("==========Menu do adiministrador==========\n1 - Gerenciar animais.\n2 - Gerenciar usuarios.\n3 - Aprovar adoções.\n4 - Gerenciar eventos.\n5 - Relatorios.\n6 - Estatisticas.\n7 - Notificações.\n0 - Sair.\nEscolha uma opção:"));

                                        if(opcaoAdm == 1){
                                            ((AdiministradorDoAbrigo)u).gerenciarAnimais();
                                        }
                                        else if(opcaoAdm == 2){
                                            ((AdiministradorDoAbrigo)u).gerenciarUsuarios();
                                        }
                                        else if(opcaoAdm == 3){
                                            ((AdiministradorDoAbrigo)u).gerenciarAdocoes();
                                        }
                                        else if(opcaoAdm == 4){
                                            ((AdiministradorDoAbrigo)u).gerenciarEventos();
                                        }
                                        else if(opcaoAdm == 5){
                                            ControleDeSaude.verHistoricoMedicoCompleto(u);
                                        }
                                        else if(opcaoAdm == 6){
                                            ((AdiministradorDoAbrigo)u).verEstatisticas();
                                        }
                                        else if(opcaoAdm == 7){
                                            Notificacoes.verNotificacoes(u);
                                            if(JOptionPane.showInputDialog("Deseja apagar TODAS as notificações?(s/n)").trim().equalsIgnoreCase("s"))
                                                Notificacoes.apagarNotificacoes(u);
                                        }
                                        else if(opcaoAdm == 0){
                                            JOptionPane.showMessageDialog(null, "Deslogando.");
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Opção invalida.");
                                        }
                                    } while(opcaoAdm != 0);
                                    break;
                                }
                            }
                            JOptionPane.showInternalMessageDialog(null, "Senha incorreta.");
                        }
                    }
                    if(encontrado == false)
                        JOptionPane.showMessageDialog(null, "Login inexistente.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
            }
            else if(opcao == 2){
                int opcaoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Deseja se cadastrar como:\n1 - Voluntario.\n2 - Veterinario."));

                if(opcaoCadastro == 1){
                    Voluntario.cadastrar();

                    Notificacoes.notificarAdiministrador("Um voluntario se cadastrou.");
                }
                else if(opcaoCadastro == 2){
                    Veterinario.cadastrar();

                    Notificacoes.notificarAdiministrador("Um veterinario se cadastrou.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Opção invalida.");
                }
            }
            else if(opcao == 3){
                List<Animal> disponiveis = new ArrayList<>();

                for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                    for(Tratamento t : ControleDeSaude.getTratamentos()){
                        if(a.getNome().equals(t.getNomeAnimal())){
                            if(a.getStatusAdocao().equalsIgnoreCase("disponivel") || a.getStatusAdocao().equalsIgnoreCase("disponível"))
                                disponiveis.add(a);
                        }
                    }
                    disponiveis.add(a);
                }
        
                GestaoDeAnimais.mostrarRegistros(disponiveis);
            }
            else if(opcao == 4){
                StringBuilder mensagem = new StringBuilder();
                mensagem.append("Esse sistema tem como  finalidade  facilitar o controle e o acompanhamento\ndos animais resgatados pela ONG, para um funcionamento mais organizado\no sistema possui algumas exigencias como: data em um formato especifico,\nlogins unicos, senhas de oito caracteres com pelo menos uma letra maiuscu-\nla e dois numeros alem de em determinados momentos exigir um tipo especi-\nfico de entrada como a numerica utilizada para acessar esse campo.");
            
                JTextArea area = new JTextArea(mensagem.toString());
                area.setEditable(false);

                JScrollPane scroll = new JScrollPane(area);
                scroll.setPreferredSize(new java.awt.Dimension(408,125));

                JOptionPane.showMessageDialog(null, scroll, "Sobre o sistema.", JOptionPane.INFORMATION_MESSAGE);
            }
            else if(opcao == 0){
                JOptionPane.showMessageDialog(null, "Programa encerrado.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Opção invalida.");
            }

        } while(opcao != 0);
    }
}
