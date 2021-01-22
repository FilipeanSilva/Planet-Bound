package Logic.States;

import Logic.Data.Data.GameData;
import java.io.Serializable;

public abstract class StateAdapter implements IState, Serializable {

    protected GameData dadosJogo;

    public StateAdapter(GameData dadosJogo) {
        this.dadosJogo = dadosJogo;
    }

    public GameData getJogo() {
        return dadosJogo;
    }

    public void setJogo(GameData jogo) {
        this.dadosJogo = jogo;
    }

    @Override
    public IState escolheNave(int nave) {
        return this;
    }

    @Override
    public IState moveNave() {
        return this;
    }

    @Override
    public IState aplicaEvento() {
        return this;
    }

    @Override
    public IState converter() {
        return this;
    }

    @Override
    public IState saiConverter() {
        return this;
    }

    @Override
    public IState converteRecurso(int x, int y) {
        return this;
    }

    @Override
    public IState revive(int x) {
        return this;
    }

    @Override
    public IState melhorarCargo() {
        return this;
    }

    @Override
    public IState melhorarWeaponSys() {
        return this;
    }

    @Override
    public IState reabastecerShields() {
        return this;
    }

    @Override
    public IState compraDrone() {
        return this;
    }

    @Override
    public IState compraShield() {
        return this;
    }
    
    @Override
    public IState comeca() {
        return this;
    }
    
    @Override
    public IState acaba() {
        return this;
    }

    @Override
    public IState compraFuel() {
        return this;
    }

    @Override
    public IState entraPlaneta() {
        return this;
    }

    @Override
    public IState emMovimento(char direcao) {
        return this;
    }
    
    @Override
    public IState voltaInicio() {
        return this;
    }

    @Override
    public IState saiPlaneta() {
        return this;
    }

    @Override
    public IState saiOrbita() {
        return this;
    }    
}
