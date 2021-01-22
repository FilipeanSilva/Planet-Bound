package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.Data.Ship.MilitaryShip;
import Logic.Data.Ship.MiningShip;
import Logic.Data.Ship.Ship;
import Logic.States.OnOrbit;
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
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class onnOrbit extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;

    VBox leftBox; //shipInfo
    VBox rightBox; //shipInfo

    BasePane base;

    Button btnDrone;
    Button btnConverte;
    Button btnVolta;

    public onnOrbit(ObservableStatesMachine obs) {
        observable = obs;
        obs.addPropertyChangeListener(this);
        base = new BasePane(obs);
    }

    void setLeftBox() {
        leftBox = base.getBasicVBox();
        Label label;
        if (observable.getShipInfo() instanceof MilitaryShip) {
            label = base.makeLabelTop("Military Ship", 30);
            Image img = new Image("UI/grafic/images/military.png");
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
            Image img2 = new Image("UI/grafic/images/mining.png");
            ImageView imv2 = new ImageView();
            imv2.setImage(img2);
            imv2.preserveRatioProperty();
            imv2.setFitWidth(leftBox.getPrefWidth() / 2);
            imv2.setFitHeight(leftBox.getPrefHeight() / 2);
            imv2.setScaleX(0.6);
            imv2.setScaleY(0.6);
            imv2.setSmooth(true);
            imv2.setCache(true);
            leftBox.setPadding(new Insets(50));
            leftBox.setAlignment(Pos.TOP_CENTER);
            VBox miningBox = new VBox();
            Ship naveMineira = new MiningShip();
            Label miningInfo = new Label(observable.getShipInfo().toString());
            miningBox.getChildren().add(miningInfo);
            miningBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));
            leftBox.getChildren().addAll(label, imv2, miningBox);
        }
    }

    void setTopRightBox(VBox box) {

        Label label;

        ImageView imv = null;
        if (observable.getPlanetType().compareTo("NEGRO") == 0) {
            label = base.makeLabelTop("Planet:", 30, Color.rgb(64, 64, 64));
            imv = new ImageView(new Image("\\UI\\grafic\\images\\darkP.gif"));

        } else if (observable.getPlanetType().compareTo("VERMELHO") == 0) {
            label = base.makeLabelTop("Planet:", 30, Color.RED);
            imv = new ImageView(new Image("\\UI\\grafic\\images\\redP.gif"));

        } else if (observable.getPlanetType().compareTo("AZUL") == 0) {
            label = base.makeLabelTop("Planet:", 30, Color.BLUE);
            imv = new ImageView(new Image("UI/grafic/images/blueP.gif"));

        } else {
            label = base.makeLabelTop("Planet:", 30, Color.GREEN);
            imv = new ImageView(new Image("\\UI\\grafic\\images\\greenP.gif"));
        }

        imv.preserveRatioProperty();
        imv.setFitHeight((box.getMinHeight() / 2));
        imv.setFitWidth((box.getMinWidth()) / 2);
        imv.setSmooth(true);
        imv.setCache(true);
        box.setPadding(new Insets(2));
        box.setAlignment(Pos.TOP_CENTER);

        VBox infoBox = new VBox();
        Label lblInfo = new Label(observable.getPlanet().toString());

        infoBox.getChildren().add(lblInfo);
        infoBox.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));

        box.setSpacing(33);
        box.getChildren().addAll(label, imv, infoBox);
    }

    void setBotRightBox(HBox box) {

        btnDrone = new Button("Send drone");
        btnConverte = new Button("Converte resources");
        btnVolta = new Button("Exit");

        box.getChildren().addAll(btnDrone, btnConverte, btnVolta);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(10);
    }

    void setRightBox() {

        rightBox = base.getBasicVBox();

        VBox RightTopBox = base.getBasicVBox();
        RightTopBox.setPrefSize(rightBox.getPrefWidth(), rightBox.getPrefHeight() * 0.9);
        RightTopBox.setMinSize(rightBox.getPrefWidth(), rightBox.getPrefHeight() * 0.9);
        //RightTopBox.setBorder(new Border(new BorderStroke(Color.PINK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

        HBox RightBotBox = new HBox();
        RightBotBox.setPrefSize(rightBox.getPrefWidth(), rightBox.getPrefHeight() * 0.10);
        RightBotBox.setMinSize(rightBox.getPrefWidth(), rightBox.getPrefHeight() * 0.10);
        RightBotBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(3))));

        setTopRightBox(RightTopBox);
        setBotRightBox(RightBotBox);

        rightBox = new VBox(RightTopBox, RightBotBox);
    }

    private void createPane(ObservableStatesMachine obs) {

        Label lblTop = base.makeLabelTop("On orbit");
        StackPane top = base.getTop();
        top.getChildren().add(lblTop);

        Label lblBot = base.makeLabelTop((observable.showLogs()), 18, Color.RED);
        StackPane bot = base.getBottom();
        bot.getChildren().clear();
        bot.getChildren().add(lblBot);

        HBox center = base.getCenter();
        BackgroundImage myBI = new BackgroundImage(new Image("\\UI\\grafic\\images\\orbit.gif"), BackgroundRepeat.ROUND, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        center.setBackground(new Background(myBI));

        setLeftBox();
        setRightBox();

        base.setLeftBox(leftBox);
        base.setTopBox(top);
        base.setRightBox(rightBox);

        this.getChildren().clear();
        this.getChildren().add(base);
    }

    private void registerListeners() {
        btnDrone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                observable.entraPlaneta();
            }
        });

        btnConverte.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                observable.converteRecursos();
            }
        });

        btnVolta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                observable.saiOrbita();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (observable.getState() instanceof OnOrbit) {
            createPane(observable);
            registerListeners();
        }
    }
}
