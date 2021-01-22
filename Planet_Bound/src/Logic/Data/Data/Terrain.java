package Logic.Data.Data;

import java.io.Serializable;
import java.util.Random;

public class Terrain implements Serializable {

    final private char mapa[][];
    private Alien alien;
    private int x_saida, y_saida;
    private int x_drone, y_drone, droneHealth;
    private int x_recurso, y_recurso;
    public boolean recurso = true;
    private int tipoRecurso;

    public static int X_DIM = 6; // 
    public static int Y_DIM = 6; //
    public static int DRONE_HEALTH = 6;

    public Terrain(int tipoRecurso) {

        mapa = new char[X_DIM][Y_DIM];

        x_drone = new Random().nextInt(mapa.length);
        y_drone = new Random().nextInt(mapa[x_drone].length);

        x_recurso = new Random().nextInt(mapa.length);
        y_recurso = new Random().nextInt(mapa[x_recurso].length);
        while (x_recurso == x_drone) {
            x_drone = new Random().nextInt(mapa.length);
        }

        x_saida = x_drone;
        y_saida = y_drone;

        droneHealth = DRONE_HEALTH;

        this.tipoRecurso = tipoRecurso;

    }

    public int getXBase() {
        return x_saida;
    }

    public int getYBase() {
        return y_saida;
    }

    public int getX_drone() {
        return x_drone;
    }

    public int getY_drone() {
        return y_drone;
    }

    public int getX_recurso() {
        return x_recurso;
    }

    public int getY_recurso() {
        return y_recurso;
    }

    public void setX_drone(int x) {
        x_drone = x;
    }

    public void setY_drone(int y) {
        x_drone = y;
    }

    public void setX_recurso(int x) {
        x_recurso = x;
    }

    public void setY_recurso(int y) {
        y_recurso = y;
    }

    public boolean getRecurso() {
        return recurso;
    }

    public void setRecurso(boolean rec) {
        if (rec) {
            recurso = true;
        } else {
            recurso = false;
        }
    }

    public void moveAlien() {

        int distanciaX = x_drone - alien.getX();
        if (distanciaX < 0) {
            distanciaX *= -1;
        }

        int distanciaY = y_drone - alien.getY();
        if (distanciaY < 0) {
            distanciaY *= -1;
        }
        if ((distanciaX > 1 && distanciaY >= 0) || (distanciaY > 1 && distanciaX >= 0)) {
            if (y_drone > alien.getY()) {
                alien.setY(alien.getY() + 1);
            } else if (y_drone < alien.getY()) {
                alien.setY(alien.getY() - 1);
            } else if (x_drone > alien.getX()) {
                alien.setX(alien.getX() + 1);
            } else if (x_drone < alien.getX()) {
                alien.setX(alien.getX() - 1);
            }
        } else {
            if ((distanciaX > 1 && distanciaY >= 0)) {
                alien.setX(alien.getX() + 1);
            } else if ((distanciaY > 1 && distanciaX >= 0)) {
                alien.setY(alien.getY() + 1);
            }
        }
    }

    public boolean alienAttack() {

        if (alien == null) {
            alien = new Alien(X_DIM, Y_DIM);
        }
        Random r = new Random();
        while (alien.getX() == x_drone && alien.getY() == y_drone) {
            int aux = r.nextInt(6);
            alien.setX(aux);
            aux = r.nextInt(6);
            alien.setY(aux);
        }
        this.moveAlien();

        int random = r.nextInt(6);
        if (alien.getX() - 1 == x_drone && alien.getY() == y_drone) {
            if (random <= 2) {
                droneHealth--;
            } else {
                alien = null;
            }
        } else if (alien.getX() + 1 == x_drone && alien.getY() == y_drone) {
            if (random <= 2) {
                droneHealth--;
            } else {
                alien = null;
            }
        } else if (alien.getY() - 1 == y_drone && alien.getX() == x_drone) {
            if (random <= 2) {
                droneHealth--;
            } else {
                alien = null;
            }
        } else if (alien.getY() + 1 == y_drone && alien.getX() == x_drone) {
            if (random <= 2) {
                droneHealth--;
            } else {
                alien = null;
            }
        }
        if (droneHealth == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean moveDrone(char direcao) {

        if (x_drone == 0 && y_drone == 0) {
            if ((direcao == 'W') || (direcao == 'A')) {
                return false;
            } else {
                if (direcao == 'S') {
                    x_drone += 1;
                }
                if (direcao == 'D') {
                    y_drone += 1;
                }

                return true;
            }
        } else if (x_drone == mapa.length - 1 && y_drone == mapa[0].length - 1) {
            if ((direcao == 'S') || (direcao == 'D')) {
                return false;
            } else {
                if (direcao == 'A') {
                    y_drone -= 1;
                }
                if (direcao == 'W') {
                    x_drone -= 1;
                }

                return true;
            }
        } else if (x_drone == 0 && y_drone == mapa[0].length - 1) {
            if ((direcao == 'W') || (direcao == 'D')) {
                return false;
            } else {
                if (direcao == 'A') {
                    y_drone -= 1;
                }
                if (direcao == 'S') {
                    x_drone += 1;
                }

                return true;
            }
        } else if (x_drone == mapa.length - 1 && y_drone == 0) {
            if ((direcao == 'S') || (direcao == 'A')) {
                return false;
            } else {
                if (direcao == 'W') {
                    x_drone -= 1;
                }
                if (direcao == 'D') {
                    y_drone += 1;
                }

                return true;
            }
        } else if (x_drone == 0) {
            if (direcao == 'W') {
                return false;
            } else {
                if (direcao == 'A') {
                    y_drone -= 1;
                }
                if (direcao == 'S') {
                    x_drone += 1;
                }
                if (direcao == 'D') {
                    y_drone += 1;
                }

                return true;
            }
        } else if (y_drone == 0) {
            if (direcao == 'A') {
                return false;
            } else {
                if (direcao == 'W') {
                    x_drone -= 1;
                }
                if (direcao == 'S') {
                    x_drone += 1;
                }
                if (direcao == 'D') {
                    y_drone += 1;
                }

                return true;
            }
        } else if (x_drone == mapa.length - 1) {
            if (direcao == 'S') {
                return false;
            } else {
                if (direcao == 'A') {
                    y_drone -= 1;
                }
                if (direcao == 'W') {
                    x_drone -= 1;
                }
                if (direcao == 'D') {
                    y_drone += 1;
                }

                return true;
            }
        } else if (y_drone == mapa[0].length - 1) {
            if (direcao == 'D') {
                return false;
            } else {
                if (direcao == 'A') {
                    y_drone -= 1;
                }
                if (direcao == 'W') {
                    x_drone -= 1;
                }
                if (direcao == 'S') {
                    x_drone += 1;
                }

                return true;
            }
        } else {
            if (direcao == 'A') {
                y_drone -= 1;
            }
            if (direcao == 'W') {
                x_drone -= 1;
            }
            if (direcao == 'S') {
                x_drone += 1;
            }
            if (direcao == 'D') {
                y_drone += 1;
            }

            return true;
        }

    }

    public boolean verificaApanhouRecurso() {

        if ((x_drone == x_recurso) && (y_drone == y_recurso)) {
            recurso = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean verificaSaida() {
        if ((x_drone == x_saida) && (y_drone == y_saida)) {
            return true;
        } else {
            return false;
        }
    }

    public char[][] getMapa() {
        char[][] mapa_aux = new char[X_DIM][Y_DIM];

        for (int i = 0; i < mapa_aux.length; i++) {
            for (int j = 0; j < mapa_aux[i].length; j++) {
                if (!Character.isLetter(mapa_aux[i][j])) {
                    mapa_aux[i][j] = '*';
                }
            }
        }

        mapa_aux[x_saida][y_saida] = 'X';

        if (recurso) {
            mapa_aux[x_recurso][y_recurso] = 'R';
        }

        if (alien != null) {
            mapa_aux[alien.getX()][alien.getY()] = 'A';
        }

        if (droneHealth > 0) {
            mapa_aux[x_drone][y_drone] = 'D';
        }

        return mapa_aux;
    }

    public int getVidaDrone() {
        return this.droneHealth;
    }

    public int getXRecurso() {
        return x_recurso;
    }

    public int getYRecurso() {
        return y_recurso;
    }

    public int getAlienX() {
        if (alien == null) {
            return -1;
        }
        return alien.getX();
    }

    public int getAlienY() {
        if (alien == null) {
            return -1;
        }
        return alien.getY();
    }

    public int getDroneY() {
        return y_drone;
    }

    public int getDroneX() {
        return x_drone;
    }
}
