package Logic.Data.Ship;

import Logic.Data.Data.Crew;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Ship implements Serializable {

    private Map<Integer, Crew> tripulantes;

    private boolean[] artefactos;

    private boolean drone;

    private int fuel;
    private int shields;

    private int[] weaponSystem;

    private int[][] cargoHold; //[][0] - recursos pretos, [][1] vermelhos, [][2] azuis e [][3] 4 verdes.  
    private int cargoLevel; // controla o nivel do cargoHold

    // CONSTANTES ?
    public static int nRecursos = 4; // Numero de recursos existentes no jogo por cada nivel de cargo
    public static int MAXCARGOLVL; // O nivel maximo que a cargo pode ter
    public static int nRecursosLvl = 6; //Numero de recursos que pode armazenar de um certo tipo por cada nivel de cargo.
    public static int qtdWeaponSystem = 9; //Numero que define a quantidade de armazenamento máximo de cada weapon system.

    public Ship(int fuel, int shields, int maxCargoLvl, int nWeapons) {
        tripulantes = new HashMap<Integer, Crew>();

        tripulantes.put(1, new Crew("Cargo"));
        tripulantes.put(2, new Crew("Weapons"));
        tripulantes.put(3, new Crew("Shields"));
        tripulantes.put(4, new Crew("Exploration"));
        tripulantes.put(5, new Crew("Navigation"));
        tripulantes.put(6, new Crew("Captain"));

        artefactos = new boolean[5];

        drone = true;
        cargoLevel = 0;

        cargoHold = new int[maxCargoLvl + 1][nRecursos]; // o java inicia tudo a 0 por defeito

        this.fuel = fuel;
        this.shields = shields;

        weaponSystem = new int[nWeapons];
        for (int i = 0; i < weaponSystem.length; i++) {
            weaponSystem[i] = 9;
        }

    }

    public Crew getTripulante(int index) {
        return tripulantes.get(index);
    }

    public Map<Integer, Crew> getTripulantes() {
        return tripulantes;
    }
   

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int qtd) {
        this.fuel = qtd;
    }

    public int getShields() {
        return shields;
    }

    public void setShields(int qtd) {
        this.shields = qtd;
    }

    public void maxShields() {

    }

    public void setDrone(boolean drone) {
        this.drone = drone;
    }

    public boolean getDrone() {
        return drone;
    }

    public String getTipo() {
        return "Nave";
    }

    public int[][] getCargoHold() {
        return cargoHold;
    }

    public int getCargoLevel() {
        return cargoLevel;
    }

    public void setCargoLevel(int x) {
        this.cargoLevel = x;
    }

    public int[] getWeaponSystem() {
        return weaponSystem;
    }

    public void setWeaponSystem(int index, int qtd) {
        this.weaponSystem[index] = qtd;
    }

    public boolean mataTripulante(int index) {
        if (tripulantes.get(index).getVivo() == true) {
            tripulantes.get(index).setVivo(false);
            return true;
        } else {
            return false;
        }

    }

    public boolean reviveTripulante(int index) {
        if (tripulantes.get(index).getVivo() == false) {
            tripulantes.get(index).setVivo(true);
            return true;
        } else {
            return false;
        }
    }
   
    public boolean expandirCargoHold() {

        if (this.getCargoLevel() < MAXCARGOLVL) { // só expande até ao nivel 1

            this.setCargoLevel(this.getCargoLevel() + 1);
            return true;
        } else {
            return false;
        }
    }

    private boolean adicionaRecurso(int recurso) {
        int[][] aux = this.getCargoHold();

        for (int i = 0; i <= this.getCargoLevel(); i++) {
            if ((aux[i][recurso]) < nRecursosLvl) {
                aux[i][recurso] += 1;
                return true;
            }
        }

        return false;
    }

    public int adicionarRecursosCargo(int recurso, int qtd) {
        while (qtd != 0 && this.adicionaRecurso(recurso) == true) {
            qtd--;
        }
        return qtd; 
    }

    private boolean removeRecurso(int recurso) {
        int[][] aux = this.getCargoHold();

        for (int i = this.getCargoLevel(); i >= 0; i--) {
            if ((aux[i][recurso]) > 0) {
                aux[i][recurso] -= 1;
                return true;
            }
        }

        return false;
    }

    public int removerRecursosCargo(int recurso, int qtd) {
        while (qtd != 0 && this.removeRecurso(recurso) == true) {
            qtd--;
        }
        return qtd;
    }

    public int totalRecurso(int recurso) { 

        int total = 0;

        for (int i = 0; i < cargoHold.length; i++) { 
            total += cargoHold[i][recurso];
        }

        return total;
    }

    public String getTipoRecurso(int index) {
        String str = "";

        if (index == 0) {
            return str = "PRETO";
        } else if (index == 1) {
            return str = "VERMELHO";
        } else if (index == 2) {
            return str = "AZUL";
        } else if (index == 3) {
            return str = "VERDE";
        }

        return str;
    }

    public String cargoHoldToString() {
        String str = "";

        int[][] aux = this.getCargoHold();

        for (int i = this.getCargoLevel(); i >= 0; i--) {
            str += "CARGO LVL [" + i + "]: ";
            for (int x = 0; x < nRecursos; x++) {
                str += "[" + aux[i][x] + "]";
            }
            str += "\n";
        }

        return str;
    }

    public String tripulantesToString() {
        String str = "Tripulantes: ";

        for (Map.Entry<Integer, Crew> entry : tripulantes.entrySet()) {
            Integer key = entry.getKey();
            Crew value = entry.getValue();

            str += "[" + key + " - " + value + "]";
        }
        return str;

    }

    public boolean adicionaArtefacto() {
        for (int i = 0; i < artefactos.length; i++) {
            if (artefactos[i] == false) {
                artefactos[i] = true;
                return true;
            }
        }
        return false;
    }

    public boolean ganhou() {
        for (int i = 0; i < artefactos.length; i++) {
            if (artefactos[i] == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String str = "";

        str += this.tripulantesToString();
        str += "\nFuel: " + fuel;
        str += "\nShields: " + shields;
        str += "\nWeapons: ";
        for (Integer i : weaponSystem) {
            str += "[" + i + "]";
        }
        str += "\nDrone: " + drone;
        str += "\nArtefactos : ";
        for (Boolean b : artefactos) {
            str += "[" + b + "]";
        }
        str += this.cargoHoldToString();

        return str;
    }

}
