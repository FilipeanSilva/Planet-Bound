package Logic.Data.Ship;

import java.io.Serializable;

public class MiningShip extends Ship implements Serializable{

    public MiningShip() {
        super(53, 18, 3, 1);
        MAXCARGOLVL = 3;
    }

    @Override
    public String getTipo() {
        return "Mineira";
    }

    @Override
    public void maxShields() {
        this.setShields(18);
    }

}
