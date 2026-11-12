package TrabalhoFinalPOO.Usuarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Modelos.*;
import TrabalhoFinalPOO.Funcionalidades.*;
import TrabalhoFinalPOO.Interfaces.Notificacoes;

public class AdiministradorDoAbrigo extends Usuario implements Notificacoes{
    private List<String> notificacoes = new ArrayList<>();

    public AdiministradorDoAbrigo(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    } 

    public List<String> getNotificacoes(){
        return notificacoes;
    }

    public void gerenciarAnimais(){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual ação deseja realizar.\n1 - Cadastrar animais.\n2 - Editar registros."));

            if(opcao == 1){
                GestaoDeAnimais.cadastrarAnimais(this);
                Notificacoes.notificarAdiministrador("Um animal foi cadastrado.");
            }
            else if(opcao == 2){
                GestaoDeAnimais.editarRegistros(this);
                Notificacoes.notificarAdiministrador("Editaram as informações de algum animal.");
            }
            else{
                JOptionPane.showMessageDialog(null, "Opção invalida.");
            }
    }

    public void gerenciarUsuarios(){
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual operação deseja realizar.\n1 - Ver usuarios.\n2 - Deletar um usuario."));

        if(opcao == 1){
            if(Usuario.getUsuarios().size() != 0){
                StringBuilder todosUsuarios = new StringBuilder();

                for(Usuario u : Usuario.getUsuarios()){
                    todosUsuarios.append(u).append("\n-------------------------------------------\n");
                }

                JTextArea area = new JTextArea(todosUsuarios.toString());
                area.setEditable(false);

                JScrollPane scroll = new JScrollPane(area);
                scroll.setPreferredSize(new java.awt.Dimension(200, 200));

                JOptionPane.showMessageDialog(null, scroll, "Usuarios presentes no sistema.", JOptionPane.INFORMATION_MESSAGE);
            }else
                JOptionPane.showMessageDialog(null, "Nao existem usuarios no sistema.");
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
                            Notificacoes.notificarAdiministrador("Um usuario foi removido.");
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
            Notificacoes.notificarAdiministrador("Uma atividade foi cadastrada.");
            Notificacoes.notificarVoluntarios("Foi adiciona uma nova atividade.");
        }
        else if(opcao == 2){
            AgendamentoDeAtividades.editarAtividade(this);
            Notificacoes.notificarAdiministrador("Editaram uma atividade.");
            Notificacoes.notificarVoluntarios("Houve uma correção nas informações de uma atividade.");
        }
        else
            JOptionPane.showMessageDialog(null, "Opção invalida.");
    }

    public void gerenciarAdocoes(){
        try{
            if(Usuario.getUsuarios().size() != 0){
                boolean continuar;
                do{
                    continuar = false;
                    boolean voluntarioEncontrado = false;
    
                    StringBuilder todosVoluntarios = new StringBuilder();
    
                    for(Usuario u : Usuario.getUsuarios()){
                        if(u instanceof Voluntario && ((Voluntario)u).getSolicitacoes().size() != 0){
                            voluntarioEncontrado = true;
                            todosVoluntarios.append(u).append("\n--------------------------------\n");
                        }
                    }
    
                    if(voluntarioEncontrado == false){
                        JOptionPane.showMessageDialog(null, "Não existem solicitações de adoção.");
                    } else{

                        JTextArea areaVoluntarios = new JTextArea(todosVoluntarios.toString());
                        areaVoluntarios.setEditable(false);
        
                        JScrollPane scrollVoluntarios = new JScrollPane(areaVoluntarios);
                        scrollVoluntarios.setPreferredSize(new java.awt.Dimension(200, 200));
        
                        JOptionPane.showMessageDialog(null, scrollVoluntarios, "Voluntarios com solicitações presentes no sistema.", JOptionPane.INFORMATION_MESSAGE);
        
                        String loginAlvo = JOptionPane.showInputDialog("Insira o login do voluntario que deseja vizualizar as solicitações.").trim();
                        if(loginAlvo == null || loginAlvo.isEmpty() || loginAlvo.isBlank())
                            throw new IllegalArgumentException();

                        for(Usuario u : Usuario.getUsuarios()){
                            if(u.getLogin().equals(loginAlvo)){
                                ((Voluntario)u).verSolicitacoes();

                                int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual operação deseja realizar.\n1 - Aceitar solicitações.\n2 - Rejeitar solicitações."));

                                if(opcao == 1){
                                    boolean continuarAceitando, encontrado;
                                    do{
                                        continuarAceitando = false;
                                        encontrado = false;
                                        
                                        int solicitacao = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero da solicitação que deseja aceitar.")), j = 1;
                                        
                                        for(Animal a : ((Voluntario)u).getSolicitacoes()){
                                            if(j == solicitacao){
                                                encontrado = true;
                                                a.setStatusAdocao("indisponivel");
                                                ((Voluntario)u).setAnimaisResponsavel(a);
                                                ((Voluntario)u).getSolicitacoes().remove(a);
                                                JOptionPane.showMessageDialog(null, "Solicitação aceita.");
                                                Notificacoes.notificarAdiministrador("Uma solicitação de adoção foi aceita");
                                                Notificacoes.notificarUsuario("Uma de suas solicitações foi aceita", u);
                                                break;
                                            }
                                        }

                                        if(encontrado == false)
                                            JOptionPane.showMessageDialog(null, "Solicitação não encontarda.");

                                        if(JOptionPane.showInputDialog("Deseja aceitar mais alguma solicitação desse voluntario?(s/n)").trim().equalsIgnoreCase("s"))
                                            continuarAceitando = true;

                                    } while(continuarAceitando == true);
                                }
                                else if(opcao == 2){
                                    boolean continuarRejeitando, encontrado;
                                    do{
                                        continuarRejeitando = false;
                                        encontrado = false;
                                        
                                        int solicitacao = Integer.parseInt(JOptionPane.showInputDialog("Insira o numero da solicitação que deseja rejeitar.")), j = 1;
                                        
                                        for(Animal a : ((Voluntario)u).getSolicitacoes()){
                                            if(j == solicitacao){
                                                encontrado = true;
                                                ((Voluntario)u).getSolicitacoes().remove(a);
                                                JOptionPane.showMessageDialog(null, "Solicitação rejeitada.");
                                                Notificacoes.notificarAdiministrador("Uma solicitação de adoção foi recusada.");
                                                Notificacoes.notificarUsuario("Uma de suas solicitações foi rejeitada.", u);
                                                break;
                                            }
                                        }

                                        if(encontrado == false)
                                            JOptionPane.showMessageDialog(null, "Solicitação não encontarda.");

                                        if(JOptionPane.showInputDialog("Deseja rejeitar mais alguma solicitação desse voluntario?(s/n)").trim().equalsIgnoreCase("s"))
                                            continuarRejeitando = true;

                                    } while(continuarRejeitando == true);
                                }
                                else
                                    JOptionPane.showMessageDialog(null, "Opção invalida.");
                            }
                        }

                        if(JOptionPane.showInputDialog("Deseja editar as solicitações de mais algum voluntario?(s/n)").trim().equalsIgnoreCase("s"))
                            continuar = true;

                    }
                } while(continuar == true);
           } else 
            JOptionPane.showMessageDialog(null, "Não existem usuarios no sistema.");

        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void verEstatisticas(){
        StringBuilder estatisticas = new StringBuilder();

        estatisticas.append("Quantidade de animais resgatados: ").append(GestaoDeAnimais.getRegistrosAnimais().size());

        int adotados = 0;

        for(Usuario u : Usuario.getUsuarios()){
            if(u instanceof Voluntario){
                adotados += ((Voluntario)u).getAnimaisResponsavel().size();
            }
        }

        estatisticas.append(".\nQuantidade de animais adotados: ").append(adotados);

        int voluntariosAtivos = 0;

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.parse("25/12/2026", formato);

        for(Usuario u : Usuario.getUsuarios()){
            if(u instanceof Voluntario){
                LocalDate dataRecente = LocalDate.parse(((Voluntario)u).getHistoricoAtividades().get(0).getData(), formato);
                for(Atividade a : ((Voluntario)u).getHistoricoAtividades()){
                    LocalDate dataComparacao = LocalDate.parse(a.getData(), formato);

                    if(dataRecente.isBefore(dataComparacao))
                        dataRecente = dataComparacao;
                }

                if(ChronoUnit.MONTHS.between(dataRecente, dataAtual) <= 1)
                    voluntariosAtivos++;
            }
        }

        estatisticas.append(".\nQuantidade de voluntarios ativos: ").append(voluntariosAtivos);

        double taxaAdocao = (adotados * 100) / GestaoDeAnimais.getRegistrosAnimais().size();

        estatisticas.append(".\nTaxa de adoção: ").append(taxaAdocao).append("%.");

        JTextArea area = new JTextArea(estatisticas.toString());
        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(220, 100));

        JOptionPane.showMessageDialog(null, scroll, "===========Estatisticas===========", JOptionPane.INFORMATION_MESSAGE);
    }
}