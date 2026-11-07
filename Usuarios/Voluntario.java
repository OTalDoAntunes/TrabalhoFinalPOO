package TrabalhoFinalPOO.Usuarios;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Modelos.Animal;
import TrabalhoFinalPOO.Modelos.Atividade;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.GestaoDeAnimais;
import TrabalhoFinalPOO.Funcionalidades.AgendamentoDeAtividades;

public class Voluntario extends Usuario{
    private List<Animal> animaisResponsavel = new ArrayList<>();
    private List<Atividade> historicoAtividades = new ArrayList<>();

    public Voluntario(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    }

    public static void cadastrar(){
        try{
            String login = JOptionPane.showInputDialog("Crie um login.");
            if(login == null || login.isEmpty() || login.isBlank())
                throw new IllegalArgumentException();

            String senha = JOptionPane.showInputDialog("Crie uma senha.\nSua senha deve conter pelo menos oito caracteres com pelo menos uma letra maiuscula e dois numeros.");
            if(senha == null || senha.isEmpty() || senha.isBlank())
                throw new IllegalArgumentException();
            if(senha.length() < 8)
                throw new SenhaInvalidaException("Senha invalida.");
            int maiusculas = 0, numeros = 0;
            for(Character c : senha.toCharArray()){
                if(Character.isDigit(c) == true)
                    numeros++;
                if(Character.isUpperCase(c) == true)
                    maiusculas++;
            }
            if(maiusculas < 1 || numeros < 2)
                throw new SenhaInvalidaException("Senha invalida.");

            Voluntario novoVoluntario = new Voluntario(login, senha);
            novoVoluntario.adicionarUsuario(novoVoluntario);
            
            JOptionPane.showMessageDialog(null, "Voce se cadastrou no sistema como voluntario.");
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(LoginJaExistenteException LJEE){
            JOptionPane.showMessageDialog(null, "Já existe um usuario com esse login.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(SenhaInvalidaException SIE){
            JOptionPane.showMessageDialog(null, "A senha deve ter oito caracteres com uma letra maiscula e dois numeros.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }

    public void verAnimaisDisponiveis(){
        List<Animal> disponiveis = new ArrayList<>();

        for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
            if(a.getStatusAdocao().equalsIgnoreCase("disponivel") || a.getStatusAdocao().equalsIgnoreCase("disponível"))
                disponiveis.add(a);
        }
        
        GestaoDeAnimais.mostrarRegistros(disponiveis);
    }

    public void candidatarAtividades(){
        AgendamentoDeAtividades.exibirAtividades(AgendamentoDeAtividades.getAtividades());

        if(AgendamentoDeAtividades.getAtividades().size() != 0){
            boolean continuar, candidatou;

            do{
                continuar = false;
                candidatou = false;
                
                String atividade = JOptionPane.showInputDialog("Insira o nome da atividade que voce deseja se candidatar.").trim();
        
                for(Atividade a : AgendamentoDeAtividades.getAtividades()){
                    if(a.getNomeAtividade().equals(atividade)){
                        a.adiconarVoluntario(this);
                        JOptionPane.showMessageDialog(null, "Voce se candidatou a uma atividade.");
                        this.historicoAtividades.add(a);
                        candidatou = true;
                    }
                }

                if(candidatou == false)
                    JOptionPane.showMessageDialog(null, "Não existe uma atividade com esse nome.");

                if(JOptionPane.showInputDialog("Deseja se candidatar a mais alguma atividade(s/n):").trim().equalsIgnoreCase("s"))
                    continuar = true;
            } while(continuar == true);
        }
    }

    public void verAnimaisResponsavel(){
        if(this.animaisResponsavel.size() != 0){
            StringBuilder todosAnimais = new StringBuilder();
            
            for(Animal a : animaisResponsavel){
                todosAnimais.append(a).append("\n-------------------------------------------\n");
            }

            JTextArea area = new JTextArea(todosAnimais.toString());
            area.setEditable(false);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(200, 200));

            JOptionPane.showMessageDialog(null, scroll, "Aniamis sobre sua responsabilidade.", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Voce ainda não é responsavel por nenhum animal.");
    }

    public void verHistoricoParticipacoes(){
        if(this.historicoAtividades.size() != 0){
            StringBuilder historicoCompleto = new StringBuilder();

            for(Atividade a : this.historicoAtividades){
                historicoCompleto.append(a).append("\n--------------------------------------------------------------\n");
            }

            JTextArea area = new JTextArea(historicoCompleto.toString());
            area.setEditable(false);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(280, 280));

            JOptionPane.showMessageDialog(null, scroll, "Historico completo de participações na ONG.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override 
    public String toString(){
        return "Login: " + getLogin() + ".";
    }
}