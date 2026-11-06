package TrabalhoFinalPOO.Funcionalidades;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Atividade;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Usuarios.AdiministradorDoAbrigo;
import TrabalhoFinalPOO.Excecoes.*;

public class AgendamentoDeAtividades{
    List<Atividade> atividades = new ArrayList<>();

    public void cadastrarAtividade(Usuario tipoUsuario){
        try{
            if(tipoUsuario instanceof AdiministradorDoAbrigo == false)
                throw new PermissaoInvalidaException("Voce não tem permissão para fazer isso");

            String nomeAtividade = JOptionPane.showInputDialog("Insira o nome da atividade:").trim();
            if(nomeAtividade == null || nomeAtividade.isEmpty() || nomeAtividade.isBlank())
                throw new IllegalArgumentException();

            String tipoAtividade = JOptionPane.showInputDialog("Insira o tipo de atividade:").trim();
            if(tipoAtividade == null || tipoAtividade.isEmpty() || tipoAtividade.isBlank())
                throw new IllegalArgumentException();

            String data = JOptionPane.showInputDialog("Insira a data em que a atividade ocorrera:\nA data deve estar no formato dia/mes/ano, com o dia e o mes contendo os dois algarismos e o ano completo.").trim();
            if(data == null || data.isEmpty() || data.isBlank())
                throw new IllegalArgumentException();
            if(DataInvalidaException.validarData(data) == false)
                throw new DataInvalidaException("Data invalida.");

            Atividade novaAtividade = new Atividade(nomeAtividade, tipoAtividade, data, true);
            atividades.add(novaAtividade);
        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(DataInvalidaException DIE){
            JOptionPane.showMessageDialog(null, "Data invalida", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void exibirAtividades(List<Atividade> atividades){
        if(atividades.size() != 0){
            StringBuilder todasAtividades = new StringBuilder();

            for(Atividade a : atividades){
                todasAtividades.append(a).append("\n-------------------------------------------\n");
            }

            JTextArea area = new JTextArea(todasAtividades.toString());
            area.setEditable(false);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(200, 200));

            JOptionPane.showMessageDialog(null, scroll, "Atidades cadastradas no sistema.", JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "Não existem atividades cadastradas.");
    }

    public void editarAtividade(Usuario tipUsuario){
        try{
            if(tipUsuario instanceof AdiministradorDoAbrigo == false)
                throw new PermissaoInvalidaException("Voce não tem permissão para fazer isso");

            boolean continuar, encontrado;

            do{
                continuar = false;
                encontrado = false;

                exibirAtividades(this.atividades);
                String atividadeAlvo = JOptionPane.showInputDialog("Insira o nome da atividade que deseja modificar:").trim();
                
                for(Atividade a : atividades){
                    if(atividadeAlvo.equals(a.getNomeAtividade())){
                        encontrado = true;
                        String opcao = JOptionPane.showInputDialog("Qual informação deseja alterar?");
                        opcao = opcao.replace(" ", "");

                        if(opcao.equalsIgnoreCase("nome") || opcao.equalsIgnoreCase("nomeDaAtividade")){
                            String nome = JOptionPane.showInputDialog("Insira o novo nome da atividade:").trim();
                            if(nome == null || nome.isEmpty() || nome.isBlank())
                                throw new IllegalArgumentException();

                            a.setNomeAtividade(nome);
                        }
                        else if(opcao.equalsIgnoreCase("tipo") || opcao.equalsIgnoreCase("tipoDaAtividade")){
                            String tipo = JOptionPane.showInputDialog("Insira o novo tipo de atividade:").trim();
                            if(tipo == null || tipo.isEmpty() || tipo.isBlank())
                                throw new IllegalArgumentException();

                            a.setTipoAtividade(tipo);
                        }
                        else if(opcao.equalsIgnoreCase("data")){
                            String data = (JOptionPane.showInputDialog("Insira a nova data:\nLembrando que a data deve estar no formato dia/mes/ano, com dia e mes com os dois algarismos e o ano completo")).trim();
                            if(data == null || data.isEmpty() || data.isBlank())
                                throw new IllegalArgumentException();
                            if(DataInvalidaException.validarData(data) == false)
                                throw new DataInvalidaException("Data invalida");

                            a.setData(data);
                        }
                        else if(opcao.equalsIgnoreCase("disponibilidade")){
                            String disponibilidade = JOptionPane.showInputDialog("As inscrições vâo estar abertas ou fechadas?").trim();
                            if(disponibilidade == null || disponibilidade.isEmpty() || disponibilidade.isBlank())
                                throw new IllegalArgumentException();

                            if(disponibilidade.equalsIgnoreCase("abertas"))
                                a.setDisponibilidade(true);
                            else if(disponibilidade.equalsIgnoreCase("fechadas"))
                                a.setDisponibilidade(false);
                            else
                                JOptionPane.showMessageDialog(null, "Entrada invalida.");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "Opção invalida.");
                    }

                    if(encontrado == false)
                        JOptionPane.showMessageDialog(null, "Não existe uma atividade com esse nome.");

                    if(JOptionPane.showInputDialog("Deseja editar mais alguma coisa(s/n)").trim().equalsIgnoreCase("s"))
                        continuar = true;

                }
            } while(continuar == true);
            
        } catch(PermissaoInvalidaException PIE){
            JOptionPane.showMessageDialog(null, "Voce não tem permissão para fazer isso.");

        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(DataInvalidaException DIE){
            JOptionPane.showMessageDialog(null, "Data invalida", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
