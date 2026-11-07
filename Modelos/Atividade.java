package TrabalhoFinalPOO.Modelos;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.ArrayList;
import TrabalhoFinalPOO.Usuarios.Voluntario;

public class Atividade{
    private String nomeAtividade, tipoAtividade, data;
    private List<Voluntario> voluntariosCadastrados = new ArrayList<>();

    public Atividade(String nomeAtividade, String tipoAtividade, String data){
        this.nomeAtividade = nomeAtividade;
        this.tipoAtividade = tipoAtividade;
        this.data = data;
    }

    public String getNomeAtividade(){
        return this.nomeAtividade;
    }

    public String getData(){
        return this.data;
    }

    public void setNomeAtividade(String nome){
        this.nomeAtividade = nome;
    }

    public void setTipoAtividade(String tipo){
        this.tipoAtividade = tipo;
    }

    public void setData(String data){
        this.data = data;
    }

    public void adiconarVoluntario(Voluntario voluntario){
        this.voluntariosCadastrados.add(voluntario);
    }
    
    public void verVoluntarios(){
        if(this.voluntariosCadastrados.size() != 0){
            StringBuilder todosVoluntarios = new StringBuilder();

            for(Voluntario v : this.voluntariosCadastrados){
                todosVoluntarios.append(v).append("\n-------------------------------------------\n");
            }

            JTextArea area = new JTextArea(todosVoluntarios.toString());
            area.setEditable(false);

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(200, 200));

            JOptionPane.showMessageDialog(null, scroll, "Voluntarios cadastrados nessa ativivdade.", JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null, "Não existem voluntarios nessa atividade.");
    }
    
    @Override 
    public String toString(){
        return "Nome da atividade: " + this.nomeAtividade + ".\nTipo de atividade: " + this.tipoAtividade + ".\nData da atividade: " + this.data + ".";
    }
}
