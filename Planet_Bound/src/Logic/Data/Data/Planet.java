package Logic.Data.Data;

import java.io.Serializable;
import java.util.Random;

public class Planet implements Serializable{

    final private boolean estacao;
    final private String tipo;
    private Terrain terreno;
    private int qtdRecursos;
    private boolean artefacto = false;
    private boolean sairPlaneta = false;

    public Planet() {
        Random r = new Random();
        int game = r.nextInt(100);

        if (game < 30) { 
            estacao = true;
        } else { 
            estacao = false;
        }
        Random r1 = new Random();
        int random = r1.nextInt(100);

        if (random < 25) { 
            tipo = "NEGRO";
        } else if (random > 25 && random < 50) {
            tipo = "VERMELHO";
        } else if (random > 50 && random < 75) {
            tipo = "AZUL";
        } else {
            tipo = "VERDE";
        }

        int random1 = r1.nextInt((5 - 1) + 1) + 1; 
        qtdRecursos = random1;

        if (this.getIndexTipo() == 2) 
        {
            artefacto = true;
        } else {
            artefacto = false;
        }

    }

    public boolean getEstacao() {
        return estacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setSairPlaneta(boolean sair) {
        this.sairPlaneta = sair;
    }

    public boolean getSairPlaneta() {
        return this.sairPlaneta;
    }

    public int getIndexTipo() {
        if (tipo == "NEGRO") {
            return 0;
        } else if (tipo == "VERMELHO") {
            return 1;
        } else if (tipo == "AZUL") {
            return 2;
        } else if (tipo == "VERDE") {
            return 3;
        }

        return -1;
    }

    public int getQtdRecursos() {
        return qtdRecursos;
    }

    public void setQtdRecursos(int qtd) {
        this.qtdRecursos = qtd;
    }

    public boolean getArtefacto() {
        return artefacto;
    }

    public void setArtefacto(boolean art) {
        this.artefacto = art;
    }

    public Terrain getTerreno() {
        return terreno;
    }

    public void inicializaTerreno() {
        terreno = new Terrain(this.getIndexTipo());
    }

    @Override
    public String toString() {
        String s;

        s = "Planeta do tipo: " + tipo;
        s += "\nQtd recusos disponíveis: " + qtdRecursos;
        s += "\nArtefacto: " + artefacto;
        s += "\n" + "Estacao: " + estacao;

        return s;
    }
}
