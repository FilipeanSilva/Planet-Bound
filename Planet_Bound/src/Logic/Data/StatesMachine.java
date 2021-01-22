package Logic.Data;

import Logic.Data.Data.GameData;
import Logic.States.AwaitRoom;
import Logic.States.IState;
import java.io.Serializable;

public class StatesMachine implements Serializable {

    GameData dadosJogo;
    IState estado;

    public StatesMachine() {
        dadosJogo = new GameData();
        estado = new AwaitRoom(dadosJogo);
    }

    // ESTADOS
    public IState getEstado() {
        return estado;
    }

    public void setEstado(IState estado) {
        this.estado = estado;
    }

    public void escolheNave(int nave) {
        estado = estado.escolheNave(nave);
    }

    public void moveNave() {
        estado = estado.moveNave();
    }

    public void aplicaEvento() {
        estado = estado.aplicaEvento();
    }

    public void converter() {
        estado = estado.converter();
    }

    public void saiConverter() {
        estado = estado.saiConverter();
    }

    public void converteRecurso(int x, int y) {
        estado = estado.converteRecurso(x, y);
    }

    public void revive(int index) {
        estado = estado.revive(index);
    }

    public void melhorarCargo() {
        estado = estado.melhorarCargo();
    }

    public void melhorarWeaponSys() {
        estado = estado.melhorarWeaponSys();
    }

    public void reabastecerShields() {
        estado = estado.reabastecerShields();
    }

    public void compraDrone() {
        estado = estado.compraDrone();
    }

    public void compraShield() {
        estado = estado.compraShield();
    }

    public void compraFuel() {
        estado = estado.compraFuel();
    }

    public void entraPlaneta() {
        estado = estado.entraPlaneta();
    }

    public void emMovimento(char direcao) {
        estado = estado.emMovimento(direcao);
    }

    public void saiPlaneta() {
        estado = estado.saiPlaneta();
    }

    public void saiOrbita() {
        estado = estado.saiOrbita();
    }

    public void voltaInicio() {
        estado = estado.voltaInicio();
    }

    public void comeca() {
        estado = estado.comeca();
    }
    
    public void acaba() {
        estado = estado.acaba();
    }

    //CONTROL
    public GameData getDadosJogo() {
        return dadosJogo;
    }

    public void setJogo(GameData jogo) {
        this.dadosJogo = jogo;
    }

    @Override
    public String toString() {
        return dadosJogo.toString();
    }
}
