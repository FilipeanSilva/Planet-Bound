package Logic.Data;

import Logic.Data.Ship.Ship;
import Logic.States.IState;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class ObservableStatesMachine extends PropertyChangeSupport implements Serializable {

    StatesMachine statesMachine;

    public ObservableStatesMachine(StatesMachine state) {
        super(state);
        this.statesMachine = state;
    }

    public void setStatesMachine(StatesMachine gModel) {
        this.statesMachine = gModel;
        firePropertyChange(null, null, null);
    }

    public StatesMachine getStatesMachine() {
        return statesMachine;
    }

    private void setChanged() {
        firePropertyChange(null, null, null);
    }

    public void update() {
        setChanged();
    }

    public void startGame() {
        statesMachine.comeca();
        setChanged();
    }

    public void finish() {
        statesMachine.acaba();
        setChanged();
    }

    public String showLogs() {
        return statesMachine.getDadosJogo().getLastLog();
    }

    public String showAllLogs() {
        return statesMachine.getDadosJogo().mostraTodosLogs();
    }

    public void setShip(int nave) {
        statesMachine.escolheNave(nave);
        setChanged();
    }

    public Ship getShipInfo() {
        return statesMachine.getDadosJogo().getNave();
    }

    public String getEvento() {
        statesMachine.aplicaEvento();
        return statesMachine.getDadosJogo().getLastLog();
    }

    public char[][] getMapa() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getMapa();
    }

    public int getVidaDrone() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getVidaDrone();
    }

    public void moveShip() {
        statesMachine.moveNave();
        setChanged();
    }

    public void entraPlaneta() {
        statesMachine.entraPlaneta();
        setChanged();
    }

    public void converteRecursos() {
        statesMachine.converter();
        setChanged();
    }

    public int getDroneX() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getDroneX();
    }

    public int getDroneY() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getDroneY();
    }

    public void move(char c) {
        statesMachine.emMovimento(c);
        setChanged();
    }

    public int getXRecurso() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getXRecurso();
    }

    public int getYRecurso() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getYRecurso();
    }

    public int getXBase() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getXBase();
    }

    public int getYBase() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getYBase();
    }

    public void setRecursoApanhado() {
        statesMachine.getDadosJogo().getPlaneta().getTerreno().setRecurso(true);
        setChanged();
    }

    public int getAlienX() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getAlienX();
    }

    public int getAlienY() {
        return statesMachine.getDadosJogo().getPlaneta().getTerreno().getAlienY();
    }

    public String getPlanet() {
        return statesMachine.getDadosJogo().getPlaneta().toString();
    }

    public String getPlanetType() {
        return statesMachine.getDadosJogo().getPlaneta().getTipo();
    }

    public IState getState() {
        return statesMachine.getEstado();
    }

    public void voltaInicio() {
        statesMachine.voltaInicio();
        setChanged();
    }

    public void saiOrbita() {
        statesMachine.saiOrbita();
        setChanged();
    }

    public void saiPlaneta() {
        statesMachine.saiPlaneta();
        setChanged();
    }

    public String getCargo() {
        return statesMachine.getDadosJogo().getNave().cargoHoldToString();
    }

    public void saiConverter() {
        statesMachine.saiConverter();
        setChanged();
    }

    public void converteRecurso(int rec1, int rec2) {
        statesMachine.converteRecurso(rec1, rec2);
        setChanged();
    }

    public void revive(int index) {
        statesMachine.revive(index);
        setChanged();
    }

    public void melhorarCargo() {
        statesMachine.melhorarCargo();
        setChanged();
    }

    public void melhorarWeaponSys() {
        statesMachine.melhorarWeaponSys();
        setChanged();
    }

    public void reabastecerShields() {
        statesMachine.melhorarWeaponSys();
        setChanged();
    }

    public void compraDrone() {
        statesMachine.melhorarWeaponSys();
        setChanged();
    }

    public void compraShield() {
        statesMachine.melhorarWeaponSys();
        setChanged();
    }

    public void compraFuel() {
        statesMachine.melhorarWeaponSys();
        setChanged();
    }
}
