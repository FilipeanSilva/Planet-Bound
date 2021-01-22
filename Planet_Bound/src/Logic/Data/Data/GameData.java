package Logic.Data.Data;

import Logic.Data.Ship.*;
import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {

    private Ship nave;
    private Planet planeta;

    private ArrayList<String> log;

    public GameData() {
        log = new ArrayList<String>();
    }

    public void setNave(int nave) {
        if (nave == 1) {
            this.nave = new MilitaryShip();
        } else if (nave == 2) {
            this.nave = new MiningShip();
        }
    }

    public Ship getNave() {
        return nave;
    }

    public void inicializaPlaneta() {
        this.planeta = new Planet();
    }

    public Planet getPlaneta() {
        return planeta;
    }

    public ArrayList getLog() {
        return log;
    }

    public String mostraTodosLogs() {
        StringBuilder str = new StringBuilder();
        for (String string : log) {
            str.append(string + "\n");
        }
        return str.toString();
    }

    public void addToLog(String str) {
        log.add(str);
    }

    public String getLastLog() {
        return log.get(log.size() - 1);
    }

    @Override
    public String toString() {
        String s = " ";

        return s;
    }
}
