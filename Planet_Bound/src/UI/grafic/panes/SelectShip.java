package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.Data.Ship.MilitaryShip;
import Logic.Data.Ship.MiningShip;
import Logic.Data.Ship.Ship;
import Logic.States.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SelectShip extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    VBox left;
    VBox right;
    BasePane base;

    public SelectShip(ObservableStatesMachine obs) {

        observable = obs;
        obs.addPropertyChangeListener(this);

        createPane(obs);
        registerListeners();
    }

    void createPane(ObservableStatesMachine obs) {
        base = new BasePane(obs);
        StackPane top = base.getTop();

        Label lblTop = base.makeLabelTop("Select Ship: ");
        top.getChildren().add(lblTop);

        left = base.getBasicVBox();
        right = base.getBasicVBox();

        Label miliLabel = base.makeLabelTop("Military Ship", 30);
        ImageView imv = new ImageView(new Image("\\UI\\grafic\\images\\military.png"));
        imv.preserveRatioProperty();
        imv.setFitWidth(left.getPrefWidth()/2);
        imv.setFitHeight(left.getPrefHeight()/2);
        imv.setScaleX(1.2);
        imv.setScaleY(1.2);
        imv.setSmooth(true);
        imv.setCache(true);
        left.setPadding(new Insets(50));
        left.setAlignment(Pos.TOP_CENTER);
        VBox miliBox = new VBox();
        Ship naveMilitar = new MilitaryShip();
        Label miliInfo = new Label(naveMilitar.toString());
        miliBox.getChildren().add(miliInfo);
        miliBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        left.getChildren().addAll(miliLabel, imv, miliBox);

        Label miningLabel = base.makeLabelTop("Mining Ship", 30);
        ImageView imv2 = new ImageView(new Image("\\UI\\grafic\\images\\mining.png"));
        imv2.preserveRatioProperty();
        imv2.setFitWidth(left.getPrefWidth()/2);
        imv2.setFitHeight(left.getPrefHeight()/2);
        imv2.setScaleX(0.6);
        imv2.setScaleY(0.6);
        imv2.setSmooth(true);
        imv2.setCache(true);
        right.setPadding(new Insets(50));
        right.setAlignment(Pos.TOP_CENTER);
        VBox miningBox = new VBox();
        Ship naveMineira = new MiningShip();
        Label miningInfo = new Label(naveMineira.toString());
        miningBox.getChildren().add(miningInfo);
        miningBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));
        right.getChildren().addAll(miningLabel, imv2, miningBox);

        base.setLeftBox(left);
        base.setRightBox(right);
        this.getChildren().add(base);
    }

    void registerListeners() {

        left.setOnMouseEntered((t) -> {
            left.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(4))));
        });

        left.setOnMouseExited((t) -> {
            left.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        });

        right.setOnMouseEntered((t) -> {
            right.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(4))));
        });

        right.setOnMouseExited((t) -> {
            right.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        });

        left.setOnMouseClicked((t) -> {
            observable.setShip(1);
        });

        right.setOnMouseClicked((t) -> {
            observable.setShip(2);
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (observable.getState() instanceof AwaitRoom) {
        } else if (observable.getState() instanceof AwaitChoose) {
        } else if (observable.getState() instanceof awaitMove) {
        } else if (observable.getState() instanceof OnOrbit) {
        } else if (observable.getState() instanceof OnPlanet) {
        } else if (observable.getState() instanceof ConvertResources) {
        }
    }
}
