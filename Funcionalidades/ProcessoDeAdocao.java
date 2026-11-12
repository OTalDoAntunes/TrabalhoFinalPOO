package TrabalhoFinalPOO.Funcionalidades;

import javax.swing.*;

public class ProcessoDeAdocao{
    private static StringBuilder devolucoes = new StringBuilder();

    public static StringBuilder getDevolucoes(){
        return devolucoes;
    }

    public static void verDevolucoes(){
        JTextArea area =  new JTextArea(devolucoes.toString());
        area.setEditable(false);
    
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(280, 280));
    
        JOptionPane.showMessageDialog(null, scroll, "Todas as devoluções feitas:", JOptionPane.INFORMATION_MESSAGE);
    }
}
