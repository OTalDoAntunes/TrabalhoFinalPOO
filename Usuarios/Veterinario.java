package TrabalhoFinalPOO.Usuarios;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import TrabalhoFinalPOO.Modelos.Animal;
import TrabalhoFinalPOO.Modelos.Cirurgia;
import TrabalhoFinalPOO.Modelos.Consulta;
import TrabalhoFinalPOO.Modelos.Tratamento;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Modelos.Vacina;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.ControleDeSaude;
import TrabalhoFinalPOO.Funcionalidades.GestaoDeAnimais;
import TrabalhoFinalPOO.Interfaces.Notificacoes;

public class Veterinario extends Usuario implements Notificacoes{
    private List<String> notificacoes = new ArrayList<>();

    public Veterinario(String login, String senha) throws LoginJaExistenteException{
        super(login, senha);
    }

    public List<String> getNotificacoes(){
        return notificacoes;
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

            Veterinario novoVeterinario = new Veterinario(login, senha);
            novoVeterinario.adicionarUsuario(novoVeterinario);

        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(LoginJaExistenteException LJAE){
            JOptionPane.showMessageDialog(null, "Ja existe um usuario com esse login.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(SenhaInvalidaException SIE){
            JOptionPane.showMessageDialog(null, "A senha deve ter oito caracteres com uma letra maiscula e dois numeros.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void registraAtividadesMedicas(){
        try{
            boolean continuar;

            do{
                continuar = false;

                String opcao = JOptionPane.showInputDialog("Que tipo de atividade deseja registrar:\n-Consulta veterinaria.\n-Tratamento.\n-Cirurgia.\n-Vacinação.");
                opcao = opcao.replace(" ", "");

                if(opcao.equalsIgnoreCase("consulta") || opcao.equalsIgnoreCase("consultaVeterinaria") || opcao.equalsIgnoreCase("consultaVeterinária")){
                    String nomeVeterinario = JOptionPane.showInputDialog("Nome do veterinário que fez a consulta.").trim();
                    if(nomeVeterinario == null || nomeVeterinario.isEmpty() || nomeVeterinario.isBlank())
                        throw new IllegalArgumentException();

                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                        throw new IllegalArgumentException();
                    boolean encontrado = false;
                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                        if(a.getNome().equals(nomeAnimal)){
                            encontrado = true;
                            break;
                        }
                    }
                    if(encontrado == false)
                        throw new AnimalNaoExistenteException("Animal não existente.");

                    String data = JOptionPane.showInputDialog("Quando a consulta foi realizada.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                    if(data == null || data.isEmpty() || data.isBlank())
                        throw new IllegalArgumentException();
                    if(DataInvalidaException.validarData(data) == false)
                        throw new DataInvalidaException("Data invalida.");

                    String relatorio = JOptionPane.showInputDialog("Insira o relatorio sobre a consulta.").trim();
                    if(relatorio == null || relatorio.isEmpty() || relatorio.isBlank())
                        throw new IllegalArgumentException();

                    Consulta novaConsulta = new Consulta(nomeVeterinario, nomeAnimal, relatorio, data);
                    ControleDeSaude.adicionarConsulta(this, novaConsulta);

                    Notificacoes.notificarAdiministrador("Uma consulta foi registrada no sistema.");
                    Notificacoes.notificarUsuario("Voce registrou uma consulta", this);
                }
                else if(opcao.equalsIgnoreCase("tratamento")){
                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                        throw new IllegalArgumentException();
                    boolean encontrado = false;
                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                        if(a.getNome().equals(nomeAnimal)){
                            encontrado = true;
                            break;
                        }
                    }
                    if(encontrado == false)
                        throw new AnimalNaoExistenteException("Animal não existente.");

                    String tipoTratamento = JOptionPane.showInputDialog("Qual e o tipo de tratamento sendo realizado.").trim();
                    if(tipoTratamento == null || tipoTratamento.isEmpty() || tipoTratamento.isBlank())
                        throw new IllegalArgumentException();

                    String dataInicio = JOptionPane.showInputDialog("Quando foi dado inicio ao tratamento.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                    if(dataInicio == null || dataInicio.isEmpty() || dataInicio.isBlank())
                        throw new IllegalArgumentException();
                    if(DataInvalidaException.validarData(dataInicio) == false)
                        throw new DataInvalidaException("Data invalida");

                    int duracao = Integer.parseInt(JOptionPane.showInputDialog("Qual a duração do tartamento em dias."));
                    if(duracao <= 0)
                        throw new DuracaoInvalidaException("Tempo de duração invalido.");

                    Tratamento novoTratamento = new Tratamento(nomeAnimal, tipoTratamento, duracao, dataInicio);
                    ControleDeSaude.adicionarTratamento(this, novoTratamento);

                    Notificacoes.notificarAdiministrador("Um tratamento foi registrado no sistema.");
                    Notificacoes.notificarUsuario("Voce registrou um tratamento.", this);
                }
                else if(opcao.equalsIgnoreCase("cirurgia")){
                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                        throw new IllegalArgumentException();
                    boolean encontrado = false;
                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                        if(a.getNome().equals(nomeAnimal)){
                            encontrado = true;
                            break;
                        }
                    }
                    if(encontrado == false)
                        throw new AnimalNaoExistenteException("Animal não existente.");

                    String tipoCirugia = JOptionPane.showInputDialog("Qual foi o tipo de cirurgia realizada.").trim();
                    if(tipoCirugia == null || tipoCirugia.isEmpty() || tipoCirugia.isBlank())
                        throw new IllegalArgumentException();

                    String data = JOptionPane.showInputDialog("Insira a data de realização da cirurgia.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                    if(data == null || data.isEmpty() || data.isBlank())
                        throw new IllegalArgumentException();
                    if(DataInvalidaException.validarData(data) == false)
                        throw new DataInvalidaException("Data invalida.");

                    Cirurgia novaCirurgia = new Cirurgia(nomeAnimal, tipoCirugia, data);
                    ControleDeSaude.adicionarCirurgia(this, novaCirurgia);

                    Notificacoes.notificarAdiministrador("Uma cirurgia foi cadastrada no sistema.");
                    Notificacoes.notificarUsuario("Voce registrou uma cirurgia.", this);
                }
                else if(opcao.equalsIgnoreCase("vacinacao") || opcao.equalsIgnoreCase("vacinação")){
                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                        throw new IllegalArgumentException();
                    boolean encontrado = false;
                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                        if(a.getNome().equals(nomeAnimal)){
                            encontrado = true;
                            break;
                        }
                    }
                    if(encontrado == false)
                        throw new AnimalNaoExistenteException("Animal não existente.");

                    String medicamento = JOptionPane.showInputDialog("Qual foi a vacina utilizada:").trim();
                    if(medicamento == null || medicamento.isEmpty()|| medicamento.isBlank())
                        throw new IllegalArgumentException();

                    String data = JOptionPane.showInputDialog("Insira a data de realização da vacinação.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                    if(data == null || data.isEmpty() || data.isBlank())
                        throw new IllegalArgumentException();
                    if(DataInvalidaException.validarData(data) == false)
                        throw new DataInvalidaException("Data invalida.");

                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataVacinaAtual = LocalDate.parse(data, formato);

                    boolean cadastrada = false;

                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                        if(a.getNome().equals(nomeAnimal)){
                            for(Vacina v : ControleDeSaude.getVacinas()){
                                if(v.getNomeAnimal().equals(nomeAnimal) && v.getMedicamento().equals(medicamento)){
                                    LocalDate dataVacinaAntiga = LocalDate.parse(v.getData(), formato);

                                    if(dataVacinaAtual.isEqual(dataVacinaAntiga)){
                                        cadastrada = true;
                                        JOptionPane.showMessageDialog(null, "Não é possivel ter duas vacinas no mesmo dia.");
                                        break;
                                    }
                                    else{
                                        cadastrada = true;
                                        Vacina novaVacina = new Vacina(nomeAnimal, data, medicamento);
                                        ControleDeSaude.adicionarVacina(this, novaVacina);
                                        
                                        Notificacoes.notificarAdiministrador("Uma nova vacinação foi registrada.");
                                        Notificacoes.notificarUsuario("Voce registrou uma vacinação", this);
                                        break;
                                    }
                                }
                            }
                            cadastrada = true;
                            Vacina novaVacina = new Vacina(nomeAnimal, data, medicamento);
                            ControleDeSaude.adicionarVacina(this, novaVacina);

                            Notificacoes.notificarAdiministrador("Uma nova vacinação foi registrada.");
                            Notificacoes.notificarUsuario("Voce registrou uma vacinação", this);
                            break;
                        }
                    }

                    if(cadastrada == false){
                        Vacina novaVacina = new Vacina(nomeAnimal, data, medicamento);
                        ControleDeSaude.adicionarVacina(this, novaVacina);

                        Notificacoes.notificarAdiministrador("Uma nova vacinação foi registrada.");
                        Notificacoes.notificarUsuario("Voce registrou uma vacinação", this);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Tipo de atividade medica invalida.");

                if(JOptionPane.showInputDialog("Deseja adiconar mais alguma atividade medica.(s/n)").trim().equalsIgnoreCase("s"))
                    continuar = true;

            } while(continuar == true);
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(DataInvalidaException DIE){
            JOptionPane.showMessageDialog(null, "Data invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(AnimalNaoExistenteException ANEE){
            JOptionPane.showMessageDialog(null, "Animal não existente no sistema.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(DuracaoInvalidaException die){
            JOptionPane.showMessageDialog(null, "A duração deve ser maior que zero.");
            
        }
    }

    public void editarAtividadesMedicas(){
        try{
            boolean continuar;

            do{
                continuar = false;

                int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual tipo de atividade medica deseja editar:\n1 - Consulta veterinaria.\n2 - Tratamento.\n3 - Cirurgia.\n4 - Vacinação."));

                if(opcao == 1){
                    if(ControleDeSaude.getConsultas().size() != 0){
                        StringBuilder todasConsultas = new StringBuilder();
                        int i = 1;
    
                        for(Consulta c : ControleDeSaude.getConsultas()){
                            todasConsultas.append(i + "º consulta:\n").append(c).append("\n----------------------------------------------------\n");
                            i++;
                        }
    
                        JTextArea area = new JTextArea(todasConsultas.toString());
                        area.setEditable(false);
    
                        JScrollPane scroll = new JScrollPane(area);
                        scroll.setPreferredSize(new java.awt.Dimension(280, 280));
    
                        JOptionPane.showMessageDialog(null, scroll, "Todas a consultas do sistema.", JOptionPane.INFORMATION_MESSAGE);
    
                        int consulta = Integer.parseInt(JOptionPane.showInputDialog("Qual consulta deseja editar:")), j = 1;
    
                        for(Consulta c : ControleDeSaude.getConsultas()){
                            if(j == consulta){
                                int editar = Integer.parseInt(JOptionPane.showInputDialog("Qual informação quer editar.\n1 - Nome do veterinario.\n2 - Nome do animal.\n3 - Data da consulta.\n4 -Relatorio."));
    
                                if(editar == 1){
                                    String nomeVeterinario = JOptionPane.showInputDialog("Nome do veterinário que fez a consulta.").trim();
                                    if(nomeVeterinario == null || nomeVeterinario.isEmpty() || nomeVeterinario.isBlank())
                                        throw new IllegalArgumentException();
    
                                    c.setNomeVeterinario(nomeVeterinario);
    
                                    Notificacoes.notificarAdiministrador("Uma consulta foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma consulta", this);
                                }
                                else if(editar == 2){
                                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                                        throw new IllegalArgumentException();
                                    boolean encontrado = false;
                                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                                        if(a.getNome().equals(nomeAnimal)){
                                            encontrado = true;
                                            break;
                                        }
                                    }
                                    if(encontrado == false)
                                        throw new AnimalNaoExistenteException("Animal não existente.");
    
                                    c.setNomeAnimal(nomeAnimal);
    
                                    Notificacoes.notificarAdiministrador("Uma consulta foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma consulta", this);
                                }
                                else if(editar == 3){
                                    String data = JOptionPane.showInputDialog("Quando a consulta foi realizada.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                                    if(data == null || data.isEmpty() || data.isBlank())
                                        throw new IllegalArgumentException();
                                    if(DataInvalidaException.validarData(data) == false)
                                        throw new DataInvalidaException("Data invalida.");
                                    
                                    c.setData(data);
    
                                    Notificacoes.notificarAdiministrador("Uma consulta foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma consulta", this);
                                }
                                else if(editar == 4){
                                    String relatorio = JOptionPane.showInputDialog("Insira o relatorio sobre a consulta.").trim();
                                    if(relatorio == null || relatorio.isEmpty() || relatorio.isBlank())
                                        throw new IllegalArgumentException();
    
                                    c.setRelatorio(relatorio);
    
                                    Notificacoes.notificarAdiministrador("Uma consulta foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma consulta", this);
                                }
                                else 
                                    JOptionPane.showMessageDialog(null, "Opção invalida.");
                            }
                            j++;
                        }
                    } 
                    else
                        JOptionPane.showMessageDialog(null, "Não existem consultas para serem editadas.");
                }
                else if(opcao == 2){
                    if(ControleDeSaude.getTratamentos().size() != 0){
                        StringBuilder todosTratamentos = new StringBuilder();
                        int i = 1;
    
                        for(Tratamento t : ControleDeSaude.getTratamentos()){
                            todosTratamentos.append(i + "º tratamento:\n").append(t).append("\n----------------------------------------------------\n");
                            i++;
                        }
    
                        JTextArea area = new JTextArea(todosTratamentos.toString());
                        area.setEditable(false);
    
                        JScrollPane scroll = new JScrollPane(area);
                        scroll.setPreferredSize(new java.awt.Dimension(280, 280));
    
                        JOptionPane.showMessageDialog(null, scroll, "Todos os tratamentos do sistema.", JOptionPane.INFORMATION_MESSAGE);
    
                        int tratamento = Integer.parseInt(JOptionPane.showInputDialog("Qual tratamento deseja editar:")), j = 1;
    
                        for(Tratamento t : ControleDeSaude.getTratamentos()){
                            if(j == tratamento){
                                int editar = Integer.parseInt(JOptionPane.showInputDialog("Qual informação deseja editar.\n1 - Nome do animal.\n2 - Tipo de tratamento.\n3 - Data de inicio.\n4 - Duração."));
    
                                if(editar == 1){
                                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                                        throw new IllegalArgumentException();
                                    boolean encontrado = false;
                                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                                        if(a.getNome().equals(nomeAnimal)){
                                            encontrado = true;
                                            break;
                                        }
                                    }
                                    if(encontrado == false)
                                        throw new AnimalNaoExistenteException("Animal não existente.");
    
                                    t.setNomeAnimal(nomeAnimal);
    
                                    Notificacoes.notificarAdiministrador("Um tratamento foi editado.");
                                    Notificacoes.notificarUsuario("Voce editou um tratamento.", this);
                                }
                                else if(editar == 2){
                                    String tipoTratamento = JOptionPane.showInputDialog("Qual e o tipo de tratamento sendo realizado.").trim();
                                    if(tipoTratamento == null || tipoTratamento.isEmpty() || tipoTratamento.isBlank())
                                        throw new IllegalArgumentException();
    
                                    t.setTipoTratamento(tipoTratamento);
    
                                    Notificacoes.notificarAdiministrador("Um tratamento foi editado.");
                                    Notificacoes.notificarUsuario("Voce editou um tratamento.", this);
                                }
                                else if(editar == 3){
                                    String dataInicio = JOptionPane.showInputDialog("Quando foi dado inicio ao tratamento.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                                    if(dataInicio == null || dataInicio.isEmpty() || dataInicio.isBlank())
                                        throw new IllegalArgumentException();
                                    if(DataInvalidaException.validarData(dataInicio) == false)
                                        throw new DataInvalidaException("Data invalida");
    
                                    t.setData(dataInicio);
    
                                    Notificacoes.notificarAdiministrador("Um tratamento foi editado.");
                                    Notificacoes.notificarUsuario("Voce editou um tratamento.", this);
                                }
                                else if(editar == 4){
                                    int duracao = Integer.parseInt(JOptionPane.showInputDialog("Qual a duração do tartamento em dias."));
                                    if(duracao <= 0)
                                        throw new DuracaoInvalidaException("Tempo de duração invalido.");
    
                                    t.setDuracao(duracao);
    
                                    Notificacoes.notificarAdiministrador("Um tratamento foi editado.");
                                    Notificacoes.notificarUsuario("Voce editou um tratamento.", this);
                                }
                                else 
                                    JOptionPane.showMessageDialog(null, "Opção invalida.");
                            }
                        }

                    } 
                    else
                        JOptionPane.showMessageDialog(null, "Não existem tratamentos para serem editados.");
                }
                else if(opcao == 3){
                    if(ControleDeSaude.getCirurgias().size() != 0){
                        StringBuilder todasCirurgias = new StringBuilder();
                        int i = 1;
    
                        for(Cirurgia c : ControleDeSaude.getCirurgias()){
                            todasCirurgias.append(i + "º cirurgia:\n").append(c).append("\n----------------------------------------------------\n");
                            i++;
                        }
    
                        JTextArea area = new JTextArea(todasCirurgias.toString());
                        area.setEditable(false);
    
                        JScrollPane scroll = new JScrollPane(area);
                        scroll.setPreferredSize(new java.awt.Dimension(280, 280));
    
                        JOptionPane.showMessageDialog(null, scroll, "Todas as cirurgias do sistema.", JOptionPane.INFORMATION_MESSAGE);
    
                        int cirurgia = Integer.parseInt(JOptionPane.showInputDialog("Qual cirurgia deseja editar:")), j = 1;
    
                        for(Cirurgia c : ControleDeSaude.getCirurgias()){
                            if(j == cirurgia){
                                int editar = Integer.parseInt(JOptionPane.showInputDialog("Qual informação deseja editar.\n1 - Nome do animal.\n2 - Tipo da cirurgia.\n3 - Data."));
    
                                if(editar == 1){
                                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                                        throw new IllegalArgumentException();
                                    boolean encontrado = false;
                                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                                        if(a.getNome().equals(nomeAnimal)){
                                            encontrado = true;
                                            break;
                                        }
                                    }
                                    if(encontrado == false)
                                        throw new AnimalNaoExistenteException("Animal não existente.");
    
                                    c.setNomeAnimal(nomeAnimal);
    
                                    Notificacoes.notificarAdiministrador("Uma cirurgia foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma cirurgia.", this);
                                }
                                else if(editar == 2){
                                    String tipoCirugia = JOptionPane.showInputDialog("Qual foi o tipo de cirurgia realizada.").trim();
                                    if(tipoCirugia == null || tipoCirugia.isEmpty() || tipoCirugia.isBlank())
                                        throw new IllegalArgumentException();
    
                                    c.setTipoCirurgia(tipoCirugia);
    
                                    Notificacoes.notificarAdiministrador("Uma cirurgia foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma cirurgia.", this);
                                }
                                else if(editar == 3){
                                    String data = JOptionPane.showInputDialog("Quando a consulta foi realizada.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                                    if(data == null || data.isEmpty() || data.isBlank())
                                        throw new IllegalArgumentException();
                                    if(DataInvalidaException.validarData(data) == false)
                                        throw new DataInvalidaException("Data invalida.");
                                    
                                    c.setData(data);
    
                                    Notificacoes.notificarAdiministrador("Uma cirurgia foi editada.");
                                    Notificacoes.notificarUsuario("Voce editou uma cirurgia.", this);
                                }
                                else 
                                    JOptionPane.showMessageDialog(null, "Opção invalida.");
                            }
                        }

                    } 
                    else
                        JOptionPane.showMessageDialog(null, "Não existem cirurgias para serem editadas.");
                }
                else if(opcao == 4){
                    if(ControleDeSaude.getVacinas().size() != 0){
                        StringBuilder todasVacinas = new StringBuilder();
                        int i = 1;
    
                        for(Vacina v : ControleDeSaude.getVacinas()){
                            todasVacinas.append(i + "º vacinação:\n").append(v).append("\n----------------------------------------------------\n");
                            i++;
                        }
    
                        JTextArea area = new JTextArea(todasVacinas.toString());
                        area.setEditable(false);
    
                        JScrollPane scroll = new JScrollPane(area);
                        scroll.setPreferredSize(new java.awt.Dimension(280, 280));
    
                        JOptionPane.showMessageDialog(null, scroll, "Todas a consultas do sistema.", JOptionPane.INFORMATION_MESSAGE);
    
                        int vacina = Integer.parseInt(JOptionPane.showInputDialog("Qual vacinação deseja editar:")), j = 1;
    
                        for(Vacina v : ControleDeSaude.getVacinas()){
                            if(j == vacina){
                                int editar = Integer.parseInt(JOptionPane.showInputDialog("Qual informação deseja editar.\n1 - Nome do animal.\n2 - Vacina utilizada.\n3 - Data da vacinação."));
    
                                if(editar == 1){
                                    String nomeAnimal = JOptionPane.showInputDialog("Nome do animal.").trim();
                                    if(nomeAnimal == null || nomeAnimal.isEmpty() || nomeAnimal.isBlank())
                                        throw new IllegalArgumentException();
                                    boolean encontrado = false;
                                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                                        if(a.getNome().equals(nomeAnimal)){
                                            encontrado = true;
                                            break;
                                        }
                                    }
                                    if(encontrado == false)
                                        throw new AnimalNaoExistenteException("Animal não existente.");
    
                                    v.setNomeAnimal(nomeAnimal);
    
                                    Notificacoes.notificarAdiministrador("Um registro de vacinação foi editado.");
                                    Notificacoes.notificarUsuario("Voce editou um registro de vacinação.", this);
                                }
                                else if(editar == 2){
                                    String medicamento = JOptionPane.showInputDialog("Qual foi a vacina utilizada:").trim();
                                    if(medicamento == null || medicamento.isEmpty()|| medicamento.isBlank())
                                        throw new IllegalArgumentException();
                                    
                                    v.setMedicamento(medicamento);
    
                                    Notificacoes.notificarAdiministrador("Um registro de vacinação foi editado.");
                                    Notificacoes.notificarUsuario("Voce editou um registro de vacinação.", this);
                                }
                                else if(editar == 3){
                                    String data = JOptionPane.showInputDialog("Insira a data de realização da vacinação.\nA data deve estar no formato dia/mes/ano, com dia e mes com dois algarismos e o ano completo.").trim();
                                    if(data == null || data.isEmpty() || data.isBlank())
                                        throw new IllegalArgumentException();
                                    if(DataInvalidaException.validarData(data) == false)
                                        throw new DataInvalidaException("Data invalida.");
    
                                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    LocalDate dataVacinaAtual = LocalDate.parse(data, formato);
    
                                    boolean cadastrada = false;
    
                                    for(Animal a : GestaoDeAnimais.getRegistrosAnimais()){
                                        if(a.getNome().equals(v.getNomeAnimal())){
                                            for(Vacina m : ControleDeSaude.getVacinas()){
                                                if(m.getNomeAnimal().equals(v.getNomeAnimal()) && m.getMedicamento().equals(v.getMedicamento())){
                                                    LocalDate dataVacinaAntiga = LocalDate.parse(m.getData(), formato);
    
                                                    if(dataVacinaAtual.isEqual(dataVacinaAntiga)){
                                                        cadastrada = true;
                                                        JOptionPane.showMessageDialog(null, "A data e identica a anterior.");
                                                        break;
                                                    }
                                                    else{
                                                        cadastrada = true;
                                                        v.setData(data);
                                            
                                                        Notificacoes.notificarAdiministrador("Um registro de vacinação foi editado.");
                                                        Notificacoes.notificarUsuario("Voce editou um registro de vacinação.", this);
                                                        break;
                                                    }
                                                }
                                            }
                                            cadastrada = true;
                                            v.setData(data);
    
                                            Notificacoes.notificarAdiministrador("Um registro de vacinação foi editado.");
                                            Notificacoes.notificarUsuario("Voce editou um registro de vacinação.", this);
                                            break;
                                        }
                                    }
    
                                    if(cadastrada == false){
                                        v.setData(data);
    
                                        Notificacoes.notificarAdiministrador("Um registro de vacinação foi editado.");
                                        Notificacoes.notificarUsuario("Voce editou um registro de vacinação.", this);
                                    }
                                }
                            }
                        }

                    } 
                    else
                        JOptionPane.showMessageDialog(null, "Não existem vacinações para serem editadas.");
                }
                else 
                    JOptionPane.showMessageDialog(null, "Opção invalida.");

                if(JOptionPane.showInputDialog("Deseja editar alguma outra atividade medica?(s/n)").trim().equalsIgnoreCase("s"))
                    continuar = true;

            } while(continuar == true);
        } catch(IllegalArgumentException IAE){
            JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(DataInvalidaException DIE){
            JOptionPane.showMessageDialog(null, "Data invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(AnimalNaoExistenteException ANEE){
            JOptionPane.showMessageDialog(null, "Animal não existente no sistema.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

        } catch(DuracaoInvalidaException die){
            JOptionPane.showMessageDialog(null, "A duração deve ser maior que zero.");
            
        }
    }

    @Override 
    public String toString(){
        return "Login: " + getLogin() + ".";
    }
}