package TrabalhoFinalPOO.Usuarios;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Modelos.Animal;
import TrabalhoFinalPOO.Modelos.Atividade;
import TrabalhoFinalPOO.Modelos.Tratamento;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.GestaoDeAnimais;
import TrabalhoFinalPOO.Funcionalidades.ProcessoDeAdocao;
import TrabalhoFinalPOO.Interfaces.Notificacoes;
import TrabalhoFinalPOO.Funcionalidades.AgendamentoDeAtividades;
import TrabalhoFinalPOO.Funcionalidades.ControleDeSaude;

public class Voluntario extends Usuario implements Notificacoes{
    private List<Animal> animaisResponsavel = new ArrayList<>();
    private List<Atividade> historicoAtividades = new ArrayList<>();
    private List<Animal> solicitacoes = new ArrayList<>();
    private List<String> notificacoes = new ArrayList<>();

    public Voluntario(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    }

    public List<String> getNotificacoes(){
        return notificacoes;
    }

    public List<Animal> getSolicitacoes(){
        return this.solicitacoes;
    }

    public List<Animal> getAnimaisResponsavel(){
        return this.animaisResponsavel;
    }

    public List<Atividade> getHistoricoAtividades(){
        return this.historicoAtividades;
    }

    public void setAnimaisResponsavel(Animal animal){
        this.animaisResponsavel.add(animal);
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
                        this.historicoAtividades.add(a);
                        candidatou = true;

                        Notificacoes.notificarUsuario("Voce se candidatou a uma atividade.", this);
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
    
    public void solicitarAdocao(){
        List<Animal> disponiveis = new ArrayList<>();

        for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
            if(a.getStatusAdocao().trim().equalsIgnoreCase("Disponivel") || a.getStatusAdocao().trim().equalsIgnoreCase("Disponível"))
                disponiveis.add(a);
        }

        GestaoDeAnimais.mostrarRegistros(disponiveis);
        int numeroRegistro = Integer.parseInt(JOptionPane.showInputDialog("Qual animal deseja adotar?\nInforme o numero do registro.")), i = 1;
        boolean solicitacaoFeita = false;

        for(Animal a : disponiveis){
            if(i == numeroRegistro){
                solicitacaoFeita = true;
                this.solicitacoes.add(a);

                Notificacoes.notificarAdiministrador("Um voluntario fez uma solicitação.");
                Notificacoes.notificarUsuario( "Sua solicitação foi enviada com sucesso!", this);
                break;
            }
            i++;
        }

        if(solicitacaoFeita == false)
            JOptionPane.showMessageDialog(null, "Não foi possivel encontrar um animal com esse numero de registro.");
    }   

    public void verSolicitacoes(){
        if(this.solicitacoes.size() != 0){
            StringBuilder todasSolicitacoes = new StringBuilder();
            int i = 1;
    
            for(Animal a : this.solicitacoes){
                todasSolicitacoes.append(i + "º solicitação:\n").append(a).append("\n------------------------------------------------\n");
                i++;
            }
    
            JTextArea area =  new JTextArea(todasSolicitacoes.toString());
            area.setEditable(false);
    
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(200, 200));
    
            JOptionPane.showMessageDialog(null, scroll, "Solicitações do voluntario.", JOptionPane.INFORMATION_MESSAGE);

        } else
            JOptionPane.showMessageDialog(null, "Não existem solicitações.");
    }

    public void devolucao(){
        try{
            boolean animalEncontrado, continuar;
            do{
                continuar = false;
                animalEncontrado = false;
                
                if(this.animaisResponsavel.size() != 0){
                    verAnimaisResponsavel();
                    
                    String nomeAnimal = JOptionPane.showInputDialog("Insira o nome do animal que deseja devolver.");
                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                        throw new IllegalArgumentException();
                    
                    for(Animal a : this.animaisResponsavel){
                        if(a.getNome().equals(nomeAnimal)){
                            animalEncontrado = true;
    
                            String motivo = JOptionPane.showInputDialog("Informe o motivo da devolução do animal.");
                            if(motivo == null || motivo.isEmpty() || motivo.isBlank())
                                throw new IllegalArgumentException();
                            
                            ProcessoDeAdocao.getDevolucoes().append("Motivo da devolução:\n").append(motivo).append("\nAnimal devolvido:\n").append(a).append("\n----------------------------------------------\n");
                            
                            Notificacoes.notificarAdiministrador("Um animal foi devolvido.");
                            Notificacoes.notificarUsuario("Voce devolveu um animal", this);
                            break;
                        }
                    }
    
                    if(animalEncontrado == false)
                        JOptionPane.showMessageDialog(null, "Não existe um animal com esse nome sob sua responsabilidade.");
    
                    if(JOptionPane.showInputDialog("Deseja devolver algum outro animal?(s/n)").trim().equalsIgnoreCase("s"))
                        continuar = true;
                } else
                    JOptionPane.showMessageDialog(null, "Não existem animais sob sua reponsabilidade para serem devolvidos.");

            } while(continuar == true);

        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.");

        }
    }

    @Override 
    public String toString(){
        return "Login: " + getLogin() + ".";
    }
}