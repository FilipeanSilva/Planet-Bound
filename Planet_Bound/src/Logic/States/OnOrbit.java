package Logic.States;

import Logic.Data.Data.GameData;

public class OnOrbit extends StateAdapter {

    public OnOrbit(GameData dadosJogo) {
        super(dadosJogo);

    }

    @Override
    public IState converter() {
        dadosJogo.addToLog("Converte recursos");
        return new ConvertResources(dadosJogo);
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
    public IState entraPlaneta() {

        if ((dadosJogo.getNave().getTripulante(4).getVivo() == true) && dadosJogo.getNave().getDrone() == true) {
            if (dadosJogo.getPlaneta().getQtdRecursos() > 0 || dadosJogo.getPlaneta().getArtefacto() == true) {

                dadosJogo.getPlaneta().inicializaTerreno();
                dadosJogo.addToLog("O drone entrou no Planeta com sucesso.");
                return new OnPlanet(dadosJogo);
            } else {
                dadosJogo.addToLog("Já não existem recursos disponíveis para minar neste planeta.");
                return this;
            }
        } else {
            dadosJogo.addToLog("Não conseguiu entrar no planeta pois não possui tripulante do tipo 'Exploration' ou então não tem drone.");
            return this;
        }

    }

    @Override
    public IState saiOrbita() {
                dadosJogo.addToLog("Sai orbita");
        return new AwaitMove(dadosJogo);
    }

}
