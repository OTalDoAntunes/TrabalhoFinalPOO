package TrabalhoFinalPOO.Principal;

import javax.swing.*;
import TrabalhoFinalPOO.Excecoes.*;
import TrabalhoFinalPOO.Funcionalidades.*;
import TrabalhoFinalPOO.Modelos.*;
import TrabalhoFinalPOO.Usuarios.*;

public class Teste{
    public static void main(String args[]){
        try{
            GestaoDeAnimais gestaoAnimais = new GestaoDeAnimais();
            ProcessoDeAdocao adocao = new ProcessoDeAdocao();
            AgendamentoDeAtividades gerenciadorAtividades = new AgendamentoDeAtividades();

            Voluntario voluntario = new Voluntario("Antunes", "400678");
            Veterinario veterinario = new Veterinario("Jorge", "hjJSsGJ");
            AdiministradorDoAbrigo adm = new AdiministradorDoAbrigo("Geraldao", "OitoOitoMeiaSete");

            gestaoAnimais.cadastrarAnimais(voluntario);
            gestaoAnimais.cadastrarAnimais(veterinario);
            gestaoAnimais.cadastrarAnimais(adm);
            //System.out.println("editando elementos");
            //gestao.editarRegistros();
            //System.out.println("Verificando a ediçao e removendo");
            //gestao.editarRegistros();
            //System.out.println("verificando a remocao");
            //gestao.editarRegistros();

            //gestao.buscar();
            //adocao.solicitarAdocao();
            //adocao.devolucao();

            //Voluntario.cadastrar();
            //Voluntario.cadastrar();

            //gerenciadorAtividades.cadastrarAtividade(adm);
            //gerenciadorAtividades.cadastrarAtividade(adm);
            //gerenciadorAtividades.cadastrarAtividade(adm);

            //gerenciadorAtividades.editarAtividade(adm);

            //voluntario.candidatarAtividades();
            //voluntario.candidatarAtividades();

            //voluntario.verHistoricoParticipacoes();

            Veterinario.cadastrar();
            Veterinario.cadastrar();

            veterinario.registraAtividadesMedicas();
        } catch(Exception e){

        }
    }
}