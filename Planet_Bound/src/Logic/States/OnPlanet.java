package Logic.States;

import Logic.Data.Data.GameData;
import java.util.Random;

public class OnPlanet extends StateAdapter {

    public OnPlanet(GameData dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IState acaba() {
        dadosJogo.addToLog("Acaba");
        return new endGame(dadosJogo);
    }

    @Override
    public IState emMovimento(char direcao) {
        if (dadosJogo.getNave().getDrone() == true) {
            if (dadosJogo.getPlaneta().getTerreno().moveDrone(direcao)) {

                if (dadosJogo.getPlaneta().getTerreno().alienAttack()) {
                    dadosJogo.addToLog("Drone movido para a direção '" + direcao + "' com sucesso.");
                } else {
                    dadosJogo.getNave().setDrone(false);
                    dadosJogo.addToLog("O drone morreu. Compre uma drone nova para continuar a explorar planetas.");
                    return this;
                }
            } else {
                dadosJogo.addToLog("Não conseguiu mover a drone para a direção '" + direcao + "'. Verfique se indicou uma direção válida.");
                return this;
            }
        } else {
            dadosJogo.addToLog("A nave perdeu a drone de exploração. Compre uma drone nova para continuar a explorar planetas.");
            return this;
        }
        int auxRecurso = 0;

        if (dadosJogo.getPlaneta().getTerreno().verificaApanhouRecurso()) {

            Random r = new Random();
            if (dadosJogo.getPlaneta().getArtefacto() == true) {
                if (dadosJogo.getPlaneta().getQtdRecursos() == 0) {
                    dadosJogo.getNave().adicionaArtefacto();
                    dadosJogo.getPlaneta().setArtefacto(false);
                    dadosJogo.addToLog("Foi minado com sucesso 1 Artefacto do Planeta!");
                } else {

                    int random2 = r.nextInt((10 - 1) + 1) + 1; // 1..10

                    if (random2 <= 7) {

                        dadosJogo.getPlaneta().setQtdRecursos(dadosJogo.getPlaneta().getQtdRecursos() - 1);

                        int randomRecurso;
                        int indexRecurso = 0;

                        if (dadosJogo.getPlaneta().getIndexTipo() == 0) {
                            //PLANETA PRETO MINA RECURSOS PRETOS E AZUIS
                            randomRecurso = r.nextInt(2);
                            if (randomRecurso == 0) {
                                indexRecurso = 0;
                            } else {
                                indexRecurso = 2;
                            }

                        } else if (dadosJogo.getPlaneta().getIndexTipo() == 1) {
                            //PLANETA VERMELHO MINA RECURSOS VERMELHOS E AZUIS
                            randomRecurso = r.nextInt(2);
                            if (randomRecurso == 0) {
                                indexRecurso = 1;
                            } else {
                                indexRecurso = 2;
                            }
                        } else if (dadosJogo.getPlaneta().getIndexTipo() == 2) {
                            //PLANETA AZUL MINA TODOS PRETOS, VERDES, AZUIS
                            randomRecurso = r.nextInt(3);
                            if (randomRecurso == 0) {
                                indexRecurso = 0;
                            } else if (randomRecurso == 1) {
                                indexRecurso = 2;
                            } else {
                                indexRecurso = 3;
                            }
                        } else if (dadosJogo.getPlaneta().getIndexTipo() == 3) {
                            //PLANETA VERDE MINA RECURSOS VERMELHOS E VERDES
                            randomRecurso = r.nextInt(2);
                            if (randomRecurso == 0) {
                                indexRecurso = 1;
                            } else {
                                indexRecurso = 3;
                            }
                        }

                        dadosJogo.getNave().adicionarRecursosCargo(indexRecurso, 1);
                        dadosJogo.addToLog("Foi minado com sucesso 1 recurso do tipo '" + dadosJogo.getNave().getTipoRecurso(indexRecurso) + "' do Planeta.");
                    } else // O planeta ainda tem mais recursos mas ha uma probabilidade de 30% de minar um artefacto.
                    {
                        dadosJogo.getNave().adicionaArtefacto();
                        dadosJogo.getPlaneta().setArtefacto(false);
                        dadosJogo.addToLog("Foi minado com sucesso 1 Artefacto do Planeta!");
                    }
                }

            } else {
                dadosJogo.getPlaneta().setQtdRecursos(dadosJogo.getPlaneta().getQtdRecursos() - 1);
                int randomRecurso;
                int indexRecurso = 0;

                if (dadosJogo.getPlaneta().getIndexTipo() == 0) {
                    //PLANETA PRETO MINA RECURSOS PRETOS E AZUIS
                    randomRecurso = r.nextInt(2);
                    if (randomRecurso == 0) {
                        indexRecurso = 0;
                    } else {
                        indexRecurso = 2;
                    }

                } else if (dadosJogo.getPlaneta().getIndexTipo() == 1) {
                    //PLANETA VERMELHO MINA RECURSOS VERMELHOS E AZUIS
                    randomRecurso = r.nextInt(2);
                    if (randomRecurso == 0) {
                        indexRecurso = 1;
                    } else {
                        indexRecurso = 2;
                    }
                } else if (dadosJogo.getPlaneta().getIndexTipo() == 2) {
                    //PLANETA AZUL MINA PRETOS, VERDES, AZUIS
                    randomRecurso = r.nextInt(3);
                    if (randomRecurso == 0) {
                        indexRecurso = 0;
                    } else if (randomRecurso == 1) {
                        indexRecurso = 2;
                    } else {
                        indexRecurso = 3;
                    }
                } else if (dadosJogo.getPlaneta().getIndexTipo() == 3) {
                    //PLANETA VERDE MINA RECURSOS VERMELHOS E VERDES
                    randomRecurso = r.nextInt(2);
                    if (randomRecurso == 0) {
                        indexRecurso = 1;
                    } else {
                        indexRecurso = 3;
                    }
                }
                auxRecurso = indexRecurso;
                dadosJogo.getNave().adicionarRecursosCargo(indexRecurso, 1);
                dadosJogo.addToLog("Foi minado com sucesso 1 recurso do tipo '" + dadosJogo.getNave().getTipoRecurso(indexRecurso) + "' do Planeta.");
            }
        }

        if (dadosJogo.getPlaneta().getTerreno().verificaSaida() && dadosJogo.getPlaneta().getTerreno().recurso == false) {
            dadosJogo.getPlaneta().setSairPlaneta(true);
            dadosJogo.addToLog(" Mineracao completa! Recurso: " + dadosJogo.getNave().getTipoRecurso(auxRecurso));
            return new OnOrbit(dadosJogo);
        }

        // FIM DO PROCESSO DE VERIFICAÇÂO DE QUE TIPO DE RECURSO MINOU (NORMAL OU ARTEFACTO) 
        return this;
    }

    @Override
    public IState saiPlaneta() {

        if (dadosJogo.getNave().ganhou()) {
            dadosJogo.addToLog("Ganhou o jogo!");
            return new AwaitChoose(dadosJogo);
        }

        if (dadosJogo.getNave().getDrone() == true) {
            if (dadosJogo.getPlaneta().getTerreno().verificaSaida()) {
                dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() - 1); // Tira 1 de fuel da nave para voltar para orbita
                dadosJogo.addToLog("A drone saiu do planeta com sucesso.");
                return new OnOrbit(dadosJogo);
            } else {
                dadosJogo.addToLog("É necessário estar na celula de saida(X) para a drone sair do planeta.");
                return this;
            }
        } else {
            dadosJogo.addToLog("Saiu do planeta sem a Drone.");
            return new OnOrbit(dadosJogo);
        }
    }
    
        @Override
    public IState voltaInicio() {
        dadosJogo.addToLog("Voltou ao inicio!\n");
        return new AwaitRoom(dadosJogo);
    }
}
