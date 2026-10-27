package TrabalhoFinalPOO;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class GestaoDeAnimais{
    private List<Animal> registros = new ArrayList<>();
    
    public void mostrarRegistros(List<Animal> registros){
        if(registros.size() != 0){
            StringBuilder todosRegistros = new StringBuilder();
            int i = 1;

            for(Animal g : registros){
                todosRegistros.append(i + "º registro:\n").append(g).append("\n----------------------\n");
                i++;
            }

            JOptionPane.showMessageDialog(null, todosRegistros, "Todos os registros de animais presentes no sistema.", JOptionPane.INFORMATION_MESSAGE);
        }else
            JOptionPane.showMessageDialog(null, "Nao existem registros.");
        
    }

    public void cadastrarAnimais(String nome, String especie, String raca, String porte, String estadoDeSaude, String dataDeResgate, String statusDaAdocao, int idade) throws IllegalArgumentException, DataInvalidaException, IdadeInvalidaException{
        Animal novoAnimal = new Animal(nome, especie, raca, porte, estadoDeSaude, dataDeResgate, statusDaAdocao, idade);
        this.registros.add(novoAnimal);
    }

    public void editarRegistros() throws IllegalArgumentException, DataInvalidaException, IdadeInvalidaException{
        if(this.registros.size() != 0){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog("Qual operação deseja realizar?\n1 - Editar o registro de um animal.\n2 - Remover um registro."));

            if(opcao == 1){
                boolean continuar;
                mostrarRegistros(this.registros);
                int numeroRegistro = Integer.parseInt(JOptionPane.showInputDialog("Qual registro deseja editar?")), i = 1;

                for(Animal g : this.registros){
                    if(i == numeroRegistro){
                        do{
                            continuar = false;
                            String dadoAltera = JOptionPane.showInputDialog("Qual informaçâo quer editar?");
                            dadoAltera = dadoAltera.replace(" ", "");

                            if(dadoAltera.equalsIgnoreCase("nome")){
                                String nome = JOptionPane.showInputDialog("Insira o nome:");
                                if(nome == null || nome.isEmpty() || nome.isEmpty())
                                    throw new IllegalArgumentException();
                                g.setNome(nome);
                            }
                            else if(dadoAltera.equalsIgnoreCase("especie")){
                                String especie = JOptionPane.showInputDialog("Insira a especie:");
                                if(especie == null || especie.isEmpty() || especie.isBlank())
                                    throw new IllegalArgumentException();
                                g.setEspecie(especie);
                            }
                            else if(dadoAltera.equalsIgnoreCase("raca") || dadoAltera.equalsIgnoreCase("raça")){
                                String raca = JOptionPane.showInputDialog("Insira a raça:");
                                if(raca == null || raca.isEmpty() || raca.isBlank())
                                    throw new IllegalArgumentException();
                                g.setRaca(raca);
                            }
                            else if(dadoAltera.equalsIgnoreCase("porte")){
                                String porte = JOptionPane.showInputDialog("Insira o porte:");
                                if(porte == null || porte.isEmpty() || porte.isBlank())
                                    throw new IllegalArgumentException();
                                g.setPorte(porte);
                            }
                            else if(dadoAltera.equalsIgnoreCase("estadoDeSaude") || dadoAltera.equalsIgnoreCase("estadoDeSaúde")){
                                String saude = JOptionPane.showInputDialog("Insira o estado de saúde:");
                                if(saude == null || saude.isEmpty() || saude.isBlank())
                                    throw new IllegalArgumentException();
                                g.setEstadoSaude(saude);
                            }
                            else if(dadoAltera.equalsIgnoreCase("dataDeResgate")){
                                String data = JOptionPane.showInputDialog("Insira a data de resgate:\nA data deve estar no formato dia/mes/ano, com o dia e o mes contendo os dois algarismos e o ano completo.");
                                if(data == null || data.isEmpty() || data.isBlank())
                                    throw new IllegalArgumentException();
                                if(DataInvalidaException.validarData(data) == false)
                                    throw new DataInvalidaException("Data em um formato invalido.");
                                g.setDataResgate(data);
                            }
                            else if(dadoAltera.equalsIgnoreCase("statusDaAdocao") || dadoAltera.equalsIgnoreCase("statusDaAdoção")){
                                String status = JOptionPane.showInputDialog("Insira o status da adoção:");
                                if(status == null || status.isEmpty() || status.isBlank())
                                    throw new IllegalArgumentException();
                                g.setStatusAdocao(status);
                            }
                            else if(dadoAltera.equalsIgnoreCase("idade")){
                                int idade = Integer.parseInt(JOptionPane.showInputDialog("Insira a idade:"));
                                if(idade <= 0)
                                    throw new IdadeInvalidaException("Idade menor ou igual a zero");
                                g.setIdade(idade);
                            }
                            else
                                JOptionPane.showMessageDialog(null, "Opção inavlida!");
                            
                            if(JOptionPane.showInputDialog("Deseja editar mais alguma informação?(S/N)").toLowerCase().equals("s"))
                                continuar = true;

                        }while(continuar);
                    }
                    i++;

                }
            } else if(opcao == 2){
                mostrarRegistros(this.registros);
                int numeroRegistro = Integer.parseInt(JOptionPane.showInputDialog("Qual registro deseja apagar?"));

                if(numeroRegistro >= 1 && numeroRegistro <= this.registros.size())
                    this.registros.remove(numeroRegistro - 1);
                else
                    JOptionPane.showMessageDialog(null, "Registro inválido!");
                
            } else
                JOptionPane.showMessageDialog(null, "Opção invalida!");

        } else
            JOptionPane.showMessageDialog(null, "Nao existem registros.");
        
    }

    public void buscar() throws IllegalArgumentException{
        String opcao = JOptionPane.showInputDialog("Por qual carcteristica deseja buscar?\n(nome, especie, raça, porte, estado de saúde, data de resgate, status da adoção, idade)");
        if(opcao == null || opcao.isEmpty() || opcao.isBlank())
            throw new IllegalArgumentException();
        opcao = opcao.replace(" ", "");

        if(opcao.equalsIgnoreCase("nome")){
            String nome = JOptionPane.showInputDialog("Insira um nome para a busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.");
            if(nome == null || nome.isEmpty() || nome.isBlank())
                throw new IllegalArgumentException();

            if(registros.contains(nome)){
                List<Animal> buscaFiltrada = new ArrayList<>();

                for(Animal a : registros){
                    if(a.getNome().equals(nome)){
                        buscaFiltrada.add(a);        
                    }
                }

                mostrarRegistros(buscaFiltrada);
            }   else
                JOptionPane.showMessageDialog(null, "Nome não existente na lista.");
        }
    }
}