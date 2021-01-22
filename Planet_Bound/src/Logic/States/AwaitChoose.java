package Logic.States;

import Logic.Data.Data.GameData;

public class AwaitChoose extends StateAdapter {

    public AwaitChoose(GameData dadosJogo) {
        super(dadosJogo);
    }

    @Override
    public IState escolheNave(int nave) {
        dadosJogo.setNave(nave);
        dadosJogo.addToLog("Escolheu nave");
        return new AwaitMove(dadosJogo);
    }

    @Override
    public IState acaba() {
        dadosJogo.addToLog("Acabou");
        return new endGame(dadosJogo);
    }

    @Override
    public IState voltaInicio() {
        dadosJogo.addToLog("Voltou ao inicio!\n");
        return new AwaitRoom(dadosJogo);
    }
}
