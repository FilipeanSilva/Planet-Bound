package UI.text;

import Logic.Data.StatesMachine;
import Logic.States.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    final private StatesMachine maqEstados;
    private boolean sair = false;

    Scanner sc = new Scanner(System.in);

    public UI(StatesMachine maqEstados) {
        this.maqEstados = maqEstados;
    }

    public void showLog() {
        System.out.print("\n[LOG]" + maqEstados.getDadosJogo().getLastLog() + "\n");
    }

    public void uiAwaitRoom() {
        while (true) {
            System.out.print("\n\t\t\tBemVindo\n\t\t ---> PLANET BOUND <---\n");
            System.out.println("\n0: Sair\n1: Comeca jogo\n2: Carregar jogo\n3: Salva jogo\n4: Ver logs");
            System.out.print("-> ");
            char c = ' ';
            c = sc.next().charAt(0);
            if ((c == '0')) {
                sair = true;
                return;
            }
            if ((c == '4')) {
                System.out.println(maqEstados.getDadosJogo().mostraTodosLogs());
            }
            if ((c == '1')) { //1 - Define tipo de nave
                maqEstados.comeca();
                return;
            }
        }
    }

    public void uiAwaitChoose() {

        int nave = 0;
        do {
            try {
                System.out.println("\n-> Tipo de nave [1 -> Militar, 2 -> Mineira]: ");
                nave = 0;
                System.out.print("-> ");
                nave = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Erro!!! Tem de introduzir um numero!");
                sc = new Scanner(System.in);
            }
        } while (nave != 1 && nave != 2);
        maqEstados.escolheNave(nave);
        return;
    }

    public void uiAwaitMove() {
        System.out.println("\nStatus da nave: \n" + maqEstados.getDadosJogo().getNave());
        System.out.println("\n1 -> Mover Nave " + maqEstados.getDadosJogo().getNave().getTipo() + " no espaço.\n0 -> Sair do Jogo");
        System.out.print("-> ");
        char c = ' ';
        c = sc.next().charAt(0);
        if ((c == '1')) {

            maqEstados.moveNave();
            this.showLog();
        }
        if ((c == '0')) {
            //sair = true;
            maqEstados.voltaInicio();
            this.showLog();
        }
    }


    public void uiOnOrbit() {
        char c = ' ';

        System.out.println("\n" + maqEstados.getDadosJogo().getPlaneta());

        System.out.println("\n\n1 - Enviar drone");
        System.out.println("2 - Converter Recursos");
        System.out.println("0 - Sair de Orbita");
        System.out.print("-> ");

        c = sc.next().charAt(0);

        switch (c) {
            case '1':
                maqEstados.entraPlaneta();
                this.showLog();
                break;
            case '2':
                maqEstados.converter();
                break;

            case '0':
                maqEstados.saiOrbita();
        }
    }

    public void uiConvertResources() {

        char c = ' ';
        System.out.println("\nRecursos disponíveis na carga:");
        System.out.println(maqEstados.getDadosJogo().getNave().cargoHoldToString());

        System.out.println("\nMenu:");
        System.out.println("1 - Converter Recurso (recursoRemover, recursoReceber)\n"
                + "2 - Reviver tripulante (int index)\n"
                + "3 - Melhorar Cargo Hold (apenas 1 por estação)\n"
                + "4 - Melhorar Weapon System\n"
                + "5 - Reabastecer Shields\n"
                + "6 - Comprar drone\n"
                + "7 - Comprar Shield\n"
                + "8 - Comprar Fuel\n"
                + "9 - (Modo cheat) Add Recurso: int recurso(0-PRETO, 1-VERMELHO, 2-BLUE, 3-GREEN, int qtd\n"
                + "0 - Sair");
        System.out.print("-> ");
        c = sc.next().charAt(0);

        switch (c) {
            case '0':
                maqEstados.saiConverter();
                break;
            case '1':
                int recurso1 = sc.nextInt();
                int recurso2 = sc.nextInt();

                maqEstados.converteRecurso(recurso1, recurso2);

                this.showLog();
                break;
            case '2':
                int index = sc.nextInt();
                maqEstados.revive(index);

                this.showLog();
                break;
            case '3':

                maqEstados.melhorarCargo(); // ATENCAO QUE SO PODE MELHORAR 1 VEZ POR ESTACAO

                this.showLog();
                break;
            case '4':
                maqEstados.melhorarWeaponSys();
                this.showLog();
                break;
            case '5':
                maqEstados.reabastecerShields();
                this.showLog();
                break;
            case '6':
                maqEstados.compraDrone();
                this.showLog();
                break;
            case '7':
                maqEstados.compraShield();
                this.showLog();
                break;
            case '8':
                maqEstados.compraFuel();
                this.showLog();
                break;
            case '9':
                int rec = sc.nextInt();
                int qtd = sc.nextInt();

                System.out.println("Não foram adicionados " + maqEstados.getDadosJogo().
                        getNave().adicionarRecursosCargo(rec, qtd) + " dos " + qtd + " recursos porque não cabem no cargo.");
                break;
        }
    }

    public void uiOnPlanet() {

        boolean sair = false;

        while (sair != true) {
            char[][] mapa = maqEstados.getDadosJogo().getPlaneta().getTerreno().getMapa();
            if (maqEstados.getDadosJogo().getPlaneta().getSairPlaneta()) {
                sair = true;
                maqEstados.saiPlaneta();
                break;
            }
            System.out.println("\n");
            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa[i].length; j++) {
                    System.out.print(mapa[i][j] + "\t");
                }
                System.out.println("\n");
            }

            System.out.println("\n-> W(Cima) A(Esquerda) S(Baixo) D(Direita)");
            System.out.println("0 - Sair do planeta");
            System.out.print("-> ");
            char c = ' ';
            c = sc.next().charAt(0);
            c = Character.toUpperCase(c);
            if ((c == '0')) {
                sair = true;
                maqEstados.saiPlaneta();
                this.showLog();
            } else if (c == 'W' || c == 'A' || c == 'S' || c == 'D') {
                maqEstados.emMovimento(c);
                this.showLog();
            }
            if (maqEstados.getDadosJogo().getPlaneta().getSairPlaneta()) {

            }
        }
    }

    public void run() {
        while (!sair) {
            IState estado = maqEstados.getEstado();
            if (estado instanceof AwaitRoom) {
                uiAwaitRoom();
            } else if (estado instanceof AwaitChoose) {
                uiAwaitChoose();
            } else if (estado instanceof AwaitMove) {
                uiAwaitMove();
            }  else if (estado instanceof OnOrbit) {
                uiOnOrbit();
            } else if (estado instanceof ConvertResources) {
                uiConvertResources();
            } else if (estado instanceof OnPlanet) {
                uiOnPlanet();
            }else if (estado instanceof endGame) {
                return;
            }
        }

    }

}
