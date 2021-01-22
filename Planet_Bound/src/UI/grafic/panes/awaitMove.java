package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.Data.Ship.MilitaryShip;
import Logic.Data.Ship.MiningShip;
import Logic.Data.Ship.Ship;
import Logic.States.AwaitMove;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class awaitMove extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    VBox leftBox; //shipInfo
    VBox rightBox; //shipInfo
    BasePane base;
    Button btnMove;
    Button btnExit;

    public awaitMove(ObservableStatesMachine obs) {
        observable = obs;
        obs.addPropertyChangeListener(this);
        base = new BasePane(obs);
    }

    void setLeftBox() {
        leftBox = base.getBasicVBox();
        Label label;
        if (observable.getShipInfo() instanceof MilitaryShip) {
            label = base.makeLabelTop("Military Ship", 30);
            Image img = new Image("\\UI\\grafic\\images\\military.png");
            ImageView imv = new ImageView();
            imv.setImage(img);
            imv.preserveRatioProperty();
            imv.setFitWidth(leftBox.getPrefWidth() / 2);
            imv.setFitHeight(leftBox.getPrefHeight() / 2);
            imv.setScaleX(1.2);
            imv.setScaleY(1.2);
            imv.setSmooth(true);
            imv.setCache(true);
            leftBox.setPadding(new Insets(50));
            leftBox.setAlignment(Pos.TOP_CENTER);
            VBox miliBox = new VBox();
            Ship naveMilitar = new MilitaryShip();
            Label miliInfo = new Label(naveMilitar.toString());
            miliBox.getChildren().add(miliInfo);
            miliBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));
            leftBox.getChildren().addAll(label, imv, miliBox);
        } else if (observable.getShipInfo() instanceof MiningShip) {
            label = base.makeLabelTop("Mining Ship", 30);
            Image img2 = new Image("\\UI\\grafic\\images\\mining.png");
            ImageView imv2 = new ImageView();
            imv2.setImage(img2);
            imv2.preserveRatioProperty();
            imv2.setScaleX(0.6);
            imv2.setScaleY(0.6);
            imv2.setSmooth(true);
            imv2.setCache(true);
            leftBox.setPadding(new Insets(50));
            leftBox.setAlignment(Pos.TOP_CENTER);
            VBox miningBox = new VBox();
            Ship naveMineira = new MiningShip();
            Label miningInfo = new Label(naveMineira.toString());
            miningBox.getChildren().add(miningInfo);
            miningBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));
            leftBox.getChildren().addAll(label, imv2, miningBox);
        }
    }

    void setRightBox() {
        HBox arightBox = new HBox();
        arightBox.setPrefSize(base.getBasicVBox().getPrefWidth(), base.getBasicVBox().getPrefHeight());
        arightBox.setMinSize(base.getBasicVBox().getPrefWidth(), base.getBasicVBox().getPrefHeight());
        arightBox.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        btnMove = new Button("Move Ship");
        btnExit = new Button("Exit");
        arightBox.getChildren().addAll(btnMove, btnExit);
        arightBox.setAlignment(Pos.CENTER);
        arightBox.setSpacing(10);
        rightBox = new VBox(arightBox);
    }

    private void createPane(ObservableStatesMachine obs) {

        Label lblTop = base.makeLabelTop("Await Move");
        StackPane top = base.getTop();
        top.getChildren().add(lblTop);

        setLeftBox();
        setRightBox();

        base.setLeftBox(leftBox);
        base.setRightBox(rightBox);

        this.getChildren().clear();
        this.getChildren().add(base);
    }

    private void registerListeners() {
        btnMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                observable.moveShip();
            }
        });

        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                observable.voltaInicio();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (observable.getState() instanceof AwaitMove) {
            createPane(observable);
            registerListeners();
        }
    }
}
