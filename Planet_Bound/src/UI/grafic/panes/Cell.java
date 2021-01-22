package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.States.OnPlanet;
import javafx.scene.control.Label;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class Cell extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    int x;
    int y;
    Label label;
    ImageView terrenoImageView;
    Image img;

    public Cell(ObservableStatesMachine observable, int i, int j, HBox center) {
        observable.addPropertyChangeListener(this);

        this.x = i;
        this.y = j;
        this.observable = observable;
        // label = new Label(String.format("%d:%d", i, j));
        label = new Label();

        terrenoImageView = new ImageView();
        terrenoImageView.setFitHeight(center.getPrefHeight() / 6);
        terrenoImageView.setFitWidth(center.getPrefWidth() / 6);
        Image img = null;
        this.getChildren().add(label);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        if (observable.getState() instanceof OnPlanet) {
            if (observable.getDroneX() == x && observable.getDroneY() == y) {
                img = new Image("\\UI\\grafic\\images\\drone.png");
                terrenoImageView.setImage(img);
                this.getChildren().clear();
                this.getChildren().addAll(terrenoImageView);

                //this.setStyle("-fx-background-color: green; -fx-border-width: 1px; -fx-border-style: solid; -fx-pref-height: 67px");
            } else if (observable.getAlienX() == x && observable.getAlienY() == y) {
                img = new Image("\\UI\\grafic\\images\\alien.png");
                terrenoImageView.setImage(img);
                this.getChildren().clear();
                this.getChildren().addAll(terrenoImageView);

            } else if (observable.getXRecurso() == x && observable.getYRecurso() == y) {
                if (observable.getXRecurso() == observable.getDroneX() && observable.getYRecurso() == observable.getDroneY()) {
                    observable.setRecursoApanhado();
                    img = new Image("\\UI\\grafic\\images\\cel.png");
                    terrenoImageView.setImage(img);
                    this.getChildren().clear();
                    this.getChildren().addAll(terrenoImageView);
                }
                img = new Image("\\UI\\grafic\\images\\recurso.png");
                terrenoImageView.setImage(img);
                this.getChildren().clear();
                this.getChildren().addAll(terrenoImageView);

            } else if (observable.getXBase() == x && observable.getYBase() == y) {
                img = new Image("\\UI\\grafic\\images\\base.png");
                terrenoImageView.setImage(img);
                this.getChildren().clear();
                this.getChildren().addAll(terrenoImageView);

            } else {
                img = new Image("\\UI\\grafic\\images\\cel.png");
                terrenoImageView.setImage(img);
                this.getChildren().clear();
                this.getChildren().addAll(terrenoImageView);

            }
        }
    }
}
