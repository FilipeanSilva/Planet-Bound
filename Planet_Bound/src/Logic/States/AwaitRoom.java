/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic.States;

import Logic.Data.Data.GameData;

/**
 *
 * @author fanss
 */
public class AwaitRoom extends StateAdapter {

    public AwaitRoom(GameData dadosJogo) {
        super(dadosJogo);
        dadosJogo.addToLog("Pagina inicial");
    }

    @Override
    public IState comeca() {
        dadosJogo.addToLog("Comecou jogo");
        return new AwaitChoose(dadosJogo);
    }

    @Override
    public IState acaba() {
        dadosJogo.addToLog("Acaba");
        return new endGame(dadosJogo);
    }

}
