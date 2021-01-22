package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.States.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.layout.StackPane;

public class PaneOrganizer extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    BasePane base;

    StartPane startPane;
    SelectShip selectShip;
    awaitMove awaitMove;
    onnOrbit onOrbit;
    onPlanet onplanet;
    convertResources converte;

    public PaneOrganizer(ObservableStatesMachine obs) {
        observable = obs;
        observable.addPropertyChangeListener(this);

        observable = obs;

        iniciaPanes(obs);

        obs.update();
    }

    private void iniciaPanes(ObservableStatesMachine obs) {
        base = new BasePane(obs);
        startPane = new StartPane(obs);
        selectShip = new SelectShip(obs);
        awaitMove = new awaitMove(obs);
        onOrbit = new onnOrbit(obs);
        onplanet = new onPlanet(obs);
        converte = new convertResources(obs);

        this.getChildren().add(base);
        this.getChildren().add(startPane);
        this.getChildren().add(selectShip);
        this.getChildren().add(awaitMove);
        this.getChildren().add(onOrbit);
        this.getChildren().add(onplanet);
        this.getChildren().add(converte);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        System.out.println("[(Current State): " + observable.getState().getClass().getSimpleName() + "] \t [(Last Log): " + observable.showLogs() + "]");

        if (observable.getState() instanceof AwaitRoom) {
            startPane.toFront();
            startPane.setVisible(true);
            selectShip.toBack();
            selectShip.setVisible(false);
            awaitMove.toBack();
            awaitMove.setVisible(false);
            onOrbit.toBack();
            onOrbit.setVisible(false);
            onplanet.toBack();
            onplanet.setVisible(false);

            converte.toBack();
            converte.setVisible(false);
        } else if (observable.getState() instanceof AwaitChoose) {
            startPane.toBack();
            startPane.setVisible(false);
            selectShip.toFront();
            selectShip.setVisible(true);
            awaitMove.toBack();
            awaitMove.setVisible(false);
            onOrbit.toBack();
            onOrbit.setVisible(false);
            onplanet.toBack();
            onplanet.setVisible(false);

            converte.toBack();
            converte.setVisible(false);
        } else if (observable.getState() instanceof AwaitMove) {
            startPane.toBack();
            startPane.setVisible(false);

            selectShip.toBack();
            selectShip.setVisible(false);

            awaitMove.toFront();
            awaitMove.setVisible(true);

            onOrbit.toBack();
            onOrbit.setVisible(false);
            onplanet.toBack();
            onplanet.setVisible(false);

            converte.toBack();
            converte.setVisible(false);
        } else if (observable.getState() instanceof OnOrbit) {
            startPane.toBack();
            startPane.setVisible(false);
            selectShip.toBack();
            selectShip.setVisible(false);
            awaitMove.toBack();
            awaitMove.setVisible(false);
            onOrbit.toFront();
            onOrbit.setVisible(true);
            onplanet.toBack();
            onplanet.setVisible(false);

            converte.toBack();
            converte.setVisible(false);
        } else if (observable.getState() instanceof OnPlanet) {
            startPane.toBack();
            startPane.setVisible(false);
            selectShip.toBack();
            selectShip.setVisible(false);
            awaitMove.toBack();
            awaitMove.setVisible(false);
            onOrbit.toBack();
            onOrbit.setVisible(false);
            onplanet.toFront();
            onplanet.setVisible(true);
            converte.toBack();
            converte.setVisible(false);
        } else if (observable.getState() instanceof ConvertResources) {
            startPane.toBack();
            startPane.setVisible(false);
            selectShip.toBack();
            selectShip.setVisible(false);
            awaitMove.toBack();
            awaitMove.setVisible(false);
            onOrbit.toBack();
            onOrbit.setVisible(false);
            onplanet.toBack();
            onplanet.setVisible(false);
            converte.toFront();
            converte.setVisible(true);
        }
    }
}
