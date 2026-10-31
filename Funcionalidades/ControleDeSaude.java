package TrabalhoFinalPOO.Funcionalidades;

import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import TrabalhoFinalPOO.Modelos.HistoricoMedico;
import TrabalhoFinalPOO.Modelos.Cirurgia;
import TrabalhoFinalPOO.Modelos.Consulta;
import TrabalhoFinalPOO.Modelos.Tratamento;
import TrabalhoFinalPOO.Excecoes.*;

public class ControleDeSaude{
    private List<Consulta> consultas = new ArrayList<>();
    private List<Cirurgia> cirurgias = new ArrayList<>();
    private List<Tratamento> tratamentos =  new ArrayList<>();
    private List<HistoricoMedico> registroMedicoCompleto = new ArrayList<>();
}