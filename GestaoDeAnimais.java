package TrabalhoFinalPOO;

import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class GestaoDeAnimais{
    private List<Animal> registros = new ArrayList<>();
    
    public void mostrarRegistros(List<Animal> registros){
        if(registros.size() != 0){
            StringBuilder todosRegistros = new StringBuilder();
            int i = 1;

            for(Animal g : registros){
                todosRegistros.append(i + "º registro:\n").append(g).append("\n-------------------------------------------\n");
                i++;
            }

            JTextArea area = new JTextArea(todosRegistros.toString());
            area.setEditable(false);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(200, 200));

            JOptionPane.showMessageDialog(null, scroll, "Registros de animais presentes no sistema.", JOptionPane.INFORMATION_MESSAGE);
        }else
            JOptionPane.showMessageDialog(null, "Nao existem registros.");
        
    }

    public void cadastrarAnimais(String nome, String especie, String raca, String porte, String estadoDeSaude, String dataDeResgate, String statusDaAdocao, int idade) throws IllegalArgumentException, DataInvalidaException, IdadeInvalidaException{
        Animal novoAnimal = new Animal(nome, especie, raca, porte, estadoDeSaude, dataDeResgate, statusDaAdocao, idade);
        this.registros.add(novoAnimal);
    }

    public void editarRegistros(){
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
                                try{
                                    String nome = JOptionPane.showInputDialog("Insira o nome:").trim();
                                    if(nome == null || nome.isEmpty() || nome.isEmpty())
                                        throw new IllegalArgumentException();
                                    g.setNome(nome);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("especie")){
                                try{
                                    String especie = JOptionPane.showInputDialog("Insira a especie:").trim();
                                    if(especie == null || especie.isEmpty() || especie.isBlank())
                                        throw new IllegalArgumentException();
                                    g.setEspecie(especie);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("raca") || dadoAltera.equalsIgnoreCase("raça")){
                                try{
                                    String raca = JOptionPane.showInputDialog("Insira a raça:").trim();
                                    if(raca == null || raca.isEmpty() || raca.isBlank())
                                        throw new IllegalArgumentException();
                                    g.setRaca(raca);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("porte")){
                                try{
                                    String porte = JOptionPane.showInputDialog("Insira o porte:").trim();
                                    if(porte == null || porte.isEmpty() || porte.isBlank())
                                        throw new IllegalArgumentException();
                                    g.setPorte(porte);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("estadoDeSaude") || dadoAltera.equalsIgnoreCase("estadoDeSaúde")){
                                try{
                                    String saude = JOptionPane.showInputDialog("Insira o estado de saúde:").trim();
                                    if(saude == null || saude.isEmpty() || saude.isBlank())
                                        throw new IllegalArgumentException();
                                    g.setEstadoSaude(saude);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("dataDeResgate")){
                                try{
                                    String data = JOptionPane.showInputDialog("Insira a data de resgate:\nA data deve estar no formato dia/mes/ano, com o dia e o mes contendo os dois algarismos e o ano completo.").trim();
                                    if(data == null || data.isEmpty() || data.isBlank())
                                        throw new IllegalArgumentException();
                                    if(DataInvalidaException.validarData(data) == false)
                                        throw new DataInvalidaException("Data em um formato invalido.");
                                    g.setDataResgate(data);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida!", "ERRO!", JOptionPane.INFORMATION_MESSAGE);
                
                                } catch(DataInvalidaException DIE){
                                    JOptionPane.showMessageDialog(null, "Data em um formato invalido.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("statusDaAdocao") || dadoAltera.equalsIgnoreCase("statusDaAdoção")){
                                try{
                                    String status = JOptionPane.showInputDialog("Insira o status da adoção:").trim();
                                    if(status == null || status.isEmpty() || status.isBlank())
                                        throw new IllegalArgumentException();
                                    g.setStatusAdocao(status);

                                } catch(IllegalArgumentException IAE){
                                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
                            }
                            else if(dadoAltera.equalsIgnoreCase("idade")){
                                try{
                                    int idade = Integer.parseInt(JOptionPane.showInputDialog("Insira a idade:"));
                                    if(idade <= 0)
                                        throw new IdadeInvalidaException("Idade menor ou igual a zero");
                                    g.setIdade(idade);

                                } catch(IdadeInvalidaException IIE){
                                    JOptionPane.showMessageDialog(null, "Idade deve ser maior que zero!", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                                }
                                
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
        boolean continuar;

        do{
            continuar = false;
            String opcao = JOptionPane.showInputDialog("Por qual carcteristica deseja buscar?\n(nome, especie, raça, porte, estado de saúde, data de resgate, status da adoção, idade)");
            if(opcao == null || opcao.isEmpty() || opcao.isBlank())
                throw new IllegalArgumentException();
            opcao = opcao.replace(" ", "");

            if(opcao.equalsIgnoreCase("nome")){
                try{
                    String nome = JOptionPane.showInputDialog("Insira um nome para a busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.").trim();
                    if(nome == null || nome.isEmpty() || nome.isBlank())
                        throw new IllegalArgumentException();

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getNome().equals(nome)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com esse nome.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
            else if(opcao.equalsIgnoreCase("especie")){
                try{
                    String especie = JOptionPane.showInputDialog("Insira uma especie para a busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.").trim();
                    if(especie == null || especie.isEmpty() || especie.isBlank())
                        throw new IllegalArgumentException();

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getEspecie().equals(especie)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com essa especie.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
            else if(opcao.equalsIgnoreCase("raca") || opcao.equalsIgnoreCase("raça")){
                try{
                    String raca = JOptionPane.showInputDialog("Insira uma raça para a busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.").trim();
                    if(raca == null || raca.isEmpty() || raca.isBlank())
                        throw new IllegalArgumentException();

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getRaca().equals(raca)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com essa raça.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
            else if(opcao.equalsIgnoreCase("porte")){
                try{
                    String porte = JOptionPane.showInputDialog("Insira um porte para a busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.").trim();
                    if(porte == null || porte.isEmpty() || porte.isBlank())
                        throw new IllegalArgumentException();

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getPorte().equals(porte)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com esse porte.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
            else if(opcao.equalsIgnoreCase("estadoDeSaude") || opcao.equalsIgnoreCase("estadoDeSaúde")){
                try{
                    String saude = JOptionPane.showInputDialog("Insira o estado de saúde a ser utilizado na busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.").trim();
                    if(saude == null || saude.isEmpty() || saude.isBlank())
                        throw new IllegalArgumentException();

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getEstadoSaude().equals(saude)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com esse estado de saúde.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
            else if(opcao.equalsIgnoreCase("dataDeResgate")){
                try{
                    String data = JOptionPane.showInputDialog("Insira a data de resgate para a busca.\nLembrando que a data deve estar no formato dia/mes/ano, com dia e mes contendo dois algarismos e o ano completo.").trim();
                    if(data == null || data.isEmpty() || data.isBlank())
                        throw new IllegalArgumentException();
                    if(DataInvalidaException.validarData(data) == false)
                        throw new DataInvalidaException("Data no formato errado.");

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getDataResgate().equals(data)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com essa data de resgate.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida!", "ERRO!", JOptionPane.INFORMATION_MESSAGE);
                
                } catch(DataInvalidaException DIE){
                    JOptionPane.showMessageDialog(null, "Data em um formato invalido.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }
                
        }
        else if(opcao.equalsIgnoreCase("statusDaAdocao") || opcao.equalsIgnoreCase("statusDaAdoção")){
            try{
                    String status = JOptionPane.showInputDialog("Insira o status da adoção para a busca.\nLembrando que o sistema diferencia letras maiusculas de minusculas.").trim();
                    if(status == null || status.isEmpty() || status.isBlank())
                        throw new IllegalArgumentException();

                    List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getStatusAdocao().equals(status)){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com esse status de adoção.");

                } catch(IllegalArgumentException IAE){
                    JOptionPane.showMessageDialog(null, "Entrada invalida.", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

                }

        }
        else if(opcao.equalsIgnoreCase("idade")){
            try{
                int idade = Integer.parseInt(JOptionPane.showInputDialog("Insira a idade para a busca.\nLembrando que a idade deve ser maior que zero."));
                if(idade <= 0)
                    throw new IdadeInvalidaException("Idade menor ou igual a zero");

                List<Animal> buscaFiltrada = new ArrayList<>();
                    int encontrado = 0;

                    for(Animal a : registros){
                        if(a.getIdade() == idade){
                            buscaFiltrada.add(a);
                            encontrado++;
                        }
                    }

                    if(encontrado != 0)
                        mostrarRegistros(buscaFiltrada);
                    else
                        JOptionPane.showMessageDialog(null, "Não existem registros com essa idade.");

            } catch(IdadeInvalidaException IIE){
                JOptionPane.showMessageDialog(null, "Idade deve ser maior que zero!", "ERRO!", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        else
            JOptionPane.showMessageDialog(null, "Opção invalida.");
        
        if(JOptionPane.showInputDialog("Deseja fazer outra busca?(S/N)").trim().equalsIgnoreCase("s"))
            continuar = true;

        } while(continuar);
        
    }
}