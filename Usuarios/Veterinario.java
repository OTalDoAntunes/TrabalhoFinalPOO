package TrabalhoFinalPOO.Usuarios;

import javax.swing.*;
import TrabalhoFinalPOO.Modelos.Animal;
import TrabalhoFinalPOO.Modelos.Cirurgia;
import TrabalhoFinalPOO.Modelos.Consulta;
import TrabalhoFinalPOO.Modelos.Tratamento;
import TrabalhoFinalPOO.Modelos.Usuario;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.ControleDeSaude;
import TrabalhoFinalPOO.Funcionalidades.GestaoDeAnimais;

public class Veterinario extends Usuario{
    public Veterinario(String login, String senha) throws LoginJaExistenteException{
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

                String opcao = JOptionPane.showInputDialog("Que tipo de atividade deseja registrar:\n-Consulta veterinaria.\n-Tratamento.\n-Cirurgia.");
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
}