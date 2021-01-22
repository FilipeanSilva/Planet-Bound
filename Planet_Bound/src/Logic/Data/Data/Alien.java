package Logic.Data.Data;

import java.io.Serializable;
import java.util.Random;

public class Alien implements Serializable{

    private int x, y;

    public Alien(int X_DIM, int Y_DIM) {

        Random r = new Random();
        int random = r.nextInt();

        x = new Random().nextInt(X_DIM);
        y = new Random().nextInt(Y_DIM);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
