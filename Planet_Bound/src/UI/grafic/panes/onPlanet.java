package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.States.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class onPlanet extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    BasePane base;
    Button exit;

    public onPlanet(ObservableStatesMachine obs) {
        observable = obs;
        obs.addPropertyChangeListener(this);
    }

    private void createPane(ObservableStatesMachine obs) {

        StackPane top = base.getTop();
        Label lblTop = base.makeLabelTop("On Planet");
        top.getChildren().add(lblTop);

        HBox center = new HBox();

        center.setPrefSize(base.getCenter().getPrefWidth(), base.getCenter().getPrefHeight());
        center.setMinSize(base.getCenter().getPrefWidth(), base.getCenter().getPrefHeight());
        center.setAlignment(Pos.CENTER);

        HBox auxcenter = new HBox();

        auxcenter.setPrefSize((center.getPrefWidth() / 2), (center.getPrefHeight())); // A altura nao muda n sei porque
        auxcenter.setMinSize((center.getPrefWidth() / 2), (center.getPrefHeight()));
        auxcenter.setAlignment(Pos.CENTER);

        exit = new Button("Exit");
        center.getChildren().addAll(auxcenter, exit);
        center.setSpacing(33);

        GridPane terreno = new GridPane();
        terreno.setAlignment(Pos.CENTER);

        ColumnConstraints alwaysGrow = new ColumnConstraints();
        alwaysGrow.setHgrow(Priority.ALWAYS);

        GridPane gridpane = new GridPane();
        gridpane.getColumnConstraints().addAll(alwaysGrow, alwaysGrow, alwaysGrow, alwaysGrow, alwaysGrow, alwaysGrow);
        gridpane.setPrefWidth(auxcenter.getPrefWidth());
        gridpane.setPrefHeight(auxcenter.getPrefHeight());
        gridpane.setAlignment(Pos.CENTER);

        char[][] mapa = observable.getMapa();

        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                gridpane.add(new Cell(observable, j, i, auxcenter), i, j); // column=1 row=0
            }
        }

        auxcenter.getChildren().add(gridpane);
        auxcenter.setBackground(new Background(new BackgroundImage(new Image("\\UI\\grafic\\images\\terrain.png"), BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        base.getChildren().clear();
        base.setTopBox(top);
        base.setCenter(center);

        this.getChildren().add(base);
    }

    private void registerListeners() {
        exit.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t
            ) {
                observable.saiPlaneta();
            }
        }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (observable.getState() instanceof OnPlanet) {
            if (base == null) {
                base = new BasePane(observable);
                createPane(observable);
            }
            registerListeners();
        }
    }
}
