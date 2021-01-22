package Logic.Data.Ship;

import java.io.Serializable;

public class MilitaryShip extends Ship implements Serializable{

    public MilitaryShip() {
        super(35, 9, 1, 2);
        MAXCARGOLVL = 1;
    }

    @Override
    public String getTipo() {
        return "Militar";
    }

    @Override
    public void maxShields() {
        this.setShields(9);
    }

}
