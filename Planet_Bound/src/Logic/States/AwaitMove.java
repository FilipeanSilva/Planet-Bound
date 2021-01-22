package Logic.States;

import Logic.Data.Data.GameData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AwaitMove extends StateAdapter {

    public AwaitMove(GameData dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IState acaba() {
        dadosJogo.addToLog("Acaba");
        return new endGame(dadosJogo);
    }

    @Override
    public IState voltaInicio() {
        dadosJogo.addToLog("Voltou ao inicio!\n");
        return new AwaitRoom(dadosJogo);
    }

    @Override
    public IState aplicaEvento() {

        Random rand = new Random();
        int random = rand.nextInt((6 - 1) + 1) + 1;

        int multiplicador = 1; // serve para multiplicar quando não tem um shields ou weapons officer. 
        if (dadosJogo.getNave().getTripulante(2).getVivo() == false || dadosJogo.getNave().getTripulante(3).getVivo() == false) {
            multiplicador = 2;
        }

        if (random == 1) { // Evento 1 - Crew Death

            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i <= dadosJogo.getNave().getTripulantes().size(); i++) {
                list.add(i);
            }
            Collections.shuffle(list);

            boolean morreu = false;
            int i;
            for (i = 0; i <= list.size() - 1 && morreu == false;) {
                if (dadosJogo.getNave().mataTripulante(list.get(i)) == true) {
                    morreu = true;
                } else {
                    i++;
                }
            }

            if (morreu == false) {
                dadosJogo.addToLog("(EVENTO) - Crew death -) Não há nenhum tripulante a matar porque já estão todos mortos.");
            } else {
                dadosJogo.addToLog("(EVENTO) - Crew death - O tripulante " + dadosJogo.getNave().getTripulante(list.get(i)).getNome() + " Morreu.");
            }

        } else if (random == 2) { // Evento 2 - Salvage Ship

            int random2 = rand.nextInt(3);

            if (dadosJogo.getNave().adicionarRecursosCargo(random2, 1) == 0) {
                dadosJogo.addToLog("(EVENTO) - Salvage Ship - Encontrou uma nave perdida! Foi adicionado 1 recurso do tipo " + dadosJogo.getNave().getTipoRecurso(random2) + " à nave.");
            } else {
                dadosJogo.addToLog("(EVENTO) - Salvage Ship - Encontrou uma nave perdida! Tentou adicionar 1 do recurso do tipo " + dadosJogo.getNave().getTipoRecurso(random2) + " porém a cargo está cheia.");
            }

        } else if (random == 3) { // Evento 3 - Cargo Loss

            int random3 = rand.nextInt(3);
            int randomQtd = rand.nextInt((3 - 1) + 1) + 1; // qtd aleatoria

            int qtdNaoRemovida = dadosJogo.getNave().removerRecursosCargo(random3, randomQtd);// Retorna a qtd que nao consegui retornar,

            if (qtdNaoRemovida == randomQtd) // ou seja se é == randomQtd nao removeu nada
            {
                dadosJogo.addToLog("(EVENTO) - Cargo Loss - CARGO LOSS! Não foi possível remover " + randomQtd + " recurso(s) do tipo " + dadosJogo.getNave().getTipoRecurso(random3) + " porque não existe nenhum recurso desse tipo na nave.");
            } else {
                dadosJogo.addToLog("(EVENTO) - Cargo Loss - CARGO LOSS! Foram removido(s) " + (randomQtd - qtdNaoRemovida) + " recurso(s) do tipo " + dadosJogo.getNave().getTipoRecurso(random3) + " da nave.");
            }

        } else if (random == 4) { // Evento 4 - Fuel Loss

            dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() - 1);

            dadosJogo.addToLog("(EVENTO) - Fuel Loss - Foi removido 1 celula de fuel.");

        } else if (random == 5) { // Evento 5 - No event

            dadosJogo.addToLog("(EVENTO) - No event - Tiveste sorte! Nenhum evento ocorreu.");

        } else if (random == 6) { // Evento 6 - Crew Rescue

            // Revive um tripulante random
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i <= dadosJogo.getNave().getTripulantes().size(); i++) {
                list.add(i);
            }
            Collections.shuffle(list);

            boolean reviveu = false;
            int i;
            for (i = 0; i <= list.size() - 1 && reviveu == false;) {
                if (dadosJogo.getNave().reviveTripulante(list.get(i)) == true) {
                    reviveu = true;
                } else {
                    i++;
                }
            }

            if (reviveu == false) {
                dadosJogo.addToLog("(EVENTO) - Crew Rescue - Felizmente todos os tripulantes encontram-se vivos.");
            } else {
                dadosJogo.addToLog("(EVENTO) - Crew Rescue - O tripulante " + dadosJogo.getNave().getTripulante(list.get(i)).getNome() + " voltou à vida.");
            }

        }

        dadosJogo.inicializaPlaneta();
        return this;
    }

    @Override
    public IState moveNave() {

        if (dadosJogo.getNave().getFuel() > 0) {

            Random r = new Random();
            int game = r.nextInt(80);

            if (game < 8) // prob 1/8 wormhole
            {
                int random;

                if (dadosJogo.getNave().getTripulante(3).getVivo() == true) {
                    if (dadosJogo.getNave().getFuel() >= 3) {
                        if (dadosJogo.getNave().getShields() >= 2) {
                            dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() - 3);
                            dadosJogo.getNave().setShields(dadosJogo.getNave().getShields() - 2);

                            dadosJogo.addToLog("A nave movimentou se por um wormhole e perdeu 3 celulas de fuel e 2 celulas de shield.");
                            dadosJogo.inicializaPlaneta();
                            return new OnOrbit(dadosJogo);
                        } else {
                            random = r.nextInt(5);
                            dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() - 3);
                            dadosJogo.getNave().mataTripulante(random + 1);

                            dadosJogo.addToLog("A nave movimentou se por um wormhole e perdeu 3 celulas de fuel e "
                                    + "o tripulante " + dadosJogo.getNave().getTripulante(random + 1).getNome() + " morreu.");
                            dadosJogo.inicializaPlaneta();
                            return new OnOrbit(dadosJogo);
                        }
                    }
                } else {
                    if (dadosJogo.getNave().getFuel() >= 4) {
                        if (dadosJogo.getNave().getShields() >= 4) {
                            dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() - 4);
                            dadosJogo.getNave().setShields(dadosJogo.getNave().getShields() - 4);

                            dadosJogo.addToLog("A nave movimentou se por um wormhole e perdeu 4 celulas de fuel e 4 celulas de shield.");
                            dadosJogo.inicializaPlaneta();
                            return new OnOrbit(dadosJogo);
                        } else {
                            random = r.nextInt(5);
                            dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() - 4);
                            dadosJogo.getNave().mataTripulante(random + 1);

                            dadosJogo.addToLog("A nave movimentou se por um wormhole e perdeu 4 celulas de fuel e "
                                    + "o tripulante " + dadosJogo.getNave().getTripulante(random + 1).getNome() + " morreu.");
                            dadosJogo.inicializaPlaneta();
                            return new OnOrbit(dadosJogo);
                        }
                    }
                }

                dadosJogo.addToLog("A nave tentou movimentar se por um wormhole porém não conseguiu porque não tinha recursos. Inicia evento!");
                aplicaEvento();
                return new OnOrbit(dadosJogo);

            } else { // prob 8/9
                dadosJogo.addToLog("");
                aplicaEvento();
                return new OnOrbit(dadosJogo);
            }

        } else {
            dadosJogo.addToLog("Ficaste sem fuel\n\t\t\tGame Over");
            return new AwaitRoom(dadosJogo);
        }

    }

    @Override
    public IState converter() {
        dadosJogo.addToLog("Converte recursos");
        return new ConvertResources(dadosJogo);//TODO
    }

}
