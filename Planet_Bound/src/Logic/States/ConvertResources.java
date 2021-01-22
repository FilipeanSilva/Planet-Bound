package Logic.States;

import Logic.Data.Data.GameData;

public class ConvertResources extends StateAdapter {

    public ConvertResources(GameData dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IState acaba() {
        return new endGame(dadosJogo);
    }
    
    @Override
    public IState voltaInicio() {
        dadosJogo.addToLog("Voltou ao inicio!\n");
        return new AwaitRoom(dadosJogo);
    }

    @Override
    public IState converteRecurso(int rec1, int rec2) {

        if (dadosJogo.getPlaneta().getEstacao() == true) {
            if (dadosJogo.getNave().getTripulante(1).getVivo() == true) {
                if (dadosJogo.getNave().removerRecursosCargo(rec1, 1) == 0) {
                    dadosJogo.getNave().adicionarRecursosCargo(rec2, 1);
                    dadosJogo.addToLog("Foi removido 1 recurso do tipo '" + dadosJogo.getNave().getTipoRecurso(rec1) + "' e foi"
                            + "adicionado 1 recurso do tipo '" + dadosJogo.getNave().getTipoRecurso(rec2) + ".");
                } else {
                    dadosJogo.addToLog("Não tem 1 recurso do tipo '" + dadosJogo.getNave().getTipoRecurso(rec1) + "' para trocar.");
                }
            } else {
                dadosJogo.addToLog("Não é possível converter recursos visto que o tripulante que trata do processo está morto.");
            }
        } else {
            dadosJogo.addToLog("Não é possível converter recursos visto que não está numa estação.");
        }

        return this;
    }

    @Override
    public IState revive(int x) {

        if (dadosJogo.getPlaneta().getEstacao() == true) {
            if (dadosJogo.getNave().totalRecurso(0) >= 1 && dadosJogo.getNave().totalRecurso(1) >= 1
                    && dadosJogo.getNave().totalRecurso(2) >= 1 && dadosJogo.getNave().totalRecurso(3) >= 1) {
                if (dadosJogo.getNave().reviveTripulante(x) == true) {
                    for (int i = 0; i <= 3; i++) {
                        dadosJogo.getNave().removerRecursosCargo(i, 1);
                    }
                    dadosJogo.addToLog(("O Tripulante " + dadosJogo.getNave().getTripulante(x) + " foi revivido pelo custo de 1 de cada tipo recurso."));
                } else {
                    dadosJogo.addToLog(("O Tripulante " + dadosJogo.getNave().getTripulante(x) + " não foi foi revivido visto que já se encontra vivo."));
                }
            } else {
                dadosJogo.addToLog(("O Tripulante " + dadosJogo.getNave().getTripulante(x) + " não foi revivido visto que não tem os recursos necessários para a operação."));
            }
        }
        return this;
    }

    @Override
    public IState melhorarCargo() {

        if (dadosJogo.getPlaneta().getEstacao() == true) {
            if (dadosJogo.getNave().getTripulante(1).getVivo() == true) {

                if (dadosJogo.getNave().totalRecurso(0) >= 2 && dadosJogo.getNave().totalRecurso(1) >= 2 && dadosJogo.getNave().totalRecurso(2) >= 2 && dadosJogo.getNave().totalRecurso(3) >= 2) {

                    if (dadosJogo.getNave().expandirCargoHold() == true) {
                        for (int i = 0; i <= 3; i++) {
                            dadosJogo.getNave().removerRecursosCargo(i, 2);
                        }
                        dadosJogo.addToLog(("O nivel do Cargo Hold da nave foi aumentado com sucesso."));
                    } else {
                        dadosJogo.addToLog(("O nivel do Cargo Hold da nave já está no máximo."));
                    }

                } else {
                    dadosJogo.addToLog(("Não tem recursos suficientes para aumentar o nivel do Cargo Hold da nave."));
                }
            } else {
                dadosJogo.addToLog("Não é possível melhorar o Cargo Hold visto que o tripulante que trata do processo está morto.");
            }
        } else {
            dadosJogo.addToLog("Não é possível melhorar a Cargo Hold visto que não está numa estação.");
        }
        return this;
    }

    @Override
    public IState melhorarWeaponSys() {
        //TODO
        return this;
    }

    @Override
    public IState reabastecerShields() {

        if (dadosJogo.getPlaneta().getEstacao() == true) {
            if (dadosJogo.getNave().getTripulante(3).getVivo() == true) {
                if (dadosJogo.getNave().totalRecurso(0) >= 1 && dadosJogo.getNave().totalRecurso(1) >= 1
                        && dadosJogo.getNave().totalRecurso(2) >= 1 && dadosJogo.getNave().totalRecurso(3) >= 1) {

                    for (int i = 0; i <= 3; i++) {
                        dadosJogo.getNave().removerRecursosCargo(i, 1);
                    }
                    dadosJogo.getNave().maxShields();
                    dadosJogo.addToLog(("Os Shields da nave foram reabastecidos"));
                } else {
                    dadosJogo.addToLog(("Não tem recursos suficientes para reabastecer os Shields da nave."));
                }
            } else {
                dadosJogo.addToLog("Não é possível reabastecer os Shields visto que o tripulante que trata do processo está morto.");
            }
        } else {
            dadosJogo.addToLog("Não é possível reabastecer o total de Shields visto que não está numa estação, porém pode comprar 1 celula de Shield.");
        }
        return this;
    }

    @Override
    public IState compraDrone() {

        if (dadosJogo.getPlaneta().getEstacao() == true) {
            if (dadosJogo.getNave().getDrone() == false) {
                if (dadosJogo.getNave().totalRecurso(0) >= 3 && dadosJogo.getNave().totalRecurso(1) >= 3
                        && dadosJogo.getNave().totalRecurso(2) >= 3 && dadosJogo.getNave().totalRecurso(3) >= 3) {
                    for (int i = 0; i <= 3; i++) {
                        dadosJogo.getNave().removerRecursosCargo(i, 3);
                    }
                    dadosJogo.getNave().setDrone(true);
                    dadosJogo.addToLog(("Foi comprado um Drone de mineração."));
                } else {
                    dadosJogo.addToLog(("Não tem recursos suficientes para comprar o Drone de mineração."));
                }
            } else {
                dadosJogo.addToLog(("Operação sem sucesso visto que já possui um drone de mineração."));
            }
        } else {
            dadosJogo.addToLog("Não é possível comprar um Drone visto que não está numa estação.");
        }
        return this;
    }

    @Override
    public IState compraShield() {

        if (dadosJogo.getNave().getTripulante(3).getVivo() == true) {
            if (dadosJogo.getNave().totalRecurso(0) >= 1
                    && dadosJogo.getNave().totalRecurso(2) >= 1 && dadosJogo.getNave().totalRecurso(3) >= 1) {

                dadosJogo.getNave().removerRecursosCargo(0, 1);
                dadosJogo.getNave().removerRecursosCargo(2, 1);
                dadosJogo.getNave().removerRecursosCargo(3, 1);

                dadosJogo.getNave().setShields(dadosJogo.getNave().getShields() + 1);
                dadosJogo.addToLog(("Foi comprado 1 shield para a nave."));
            } else {
                dadosJogo.addToLog(("Não tem recursos suficientes para comprar 1 Shield para a nave."));
            }

        } else {
            dadosJogo.addToLog("Não é possível comprar Shields visto que o tripulante que trata do processo está morto.");
        }

        return this;
    }

    @Override
    public IState compraFuel() {

        if (dadosJogo.getNave().totalRecurso(0) >= 1
                && dadosJogo.getNave().totalRecurso(1) >= 1 && dadosJogo.getNave().totalRecurso(3) >= 1) {

            dadosJogo.getNave().removerRecursosCargo(0, 1);
            dadosJogo.getNave().removerRecursosCargo(1, 1);
            dadosJogo.getNave().removerRecursosCargo(3, 1);

            dadosJogo.getNave().setFuel(dadosJogo.getNave().getFuel() + 1);
            dadosJogo.addToLog(("Foi comprado 1 Fuel para a nave."));
        } else {
            dadosJogo.addToLog(("Não tem recursos suficientes para comprar 1 Fuel para a nave."));
        }

        return this;
    }

    @Override
    public IState saiConverter() {
        return new OnOrbit(dadosJogo);
    }

}
