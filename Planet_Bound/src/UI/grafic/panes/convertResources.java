package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.States.AwaitMove;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class convertResources extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    VBox leftBox; //shipInfo
    VBox rightBox; //shipInfo
    BasePane base;
    Button btnMove;
    Button btnExit;
    int indexMenu = 0;

    public convertResources(ObservableStatesMachine obs) {
        observable = obs;
        obs.addPropertyChangeListener(this);
        base = new BasePane(obs);
    }

    void setLeft() {

        leftBox = base.getBasicVBox();
        Label label;
        label = base.makeLabelTop("Status", 30, Color.RED);
        leftBox.setPadding(new Insets(50));
        leftBox.setAlignment(Pos.TOP_CENTER);

        VBox box = new VBox();
        Label infoCargo = new Label("Cargo: " + observable.getCargo());
        box.getChildren().add(infoCargo);
        Label infoStatus = new Label("\nShip: " + observable.getShipInfo().toString());
        box.getChildren().add(infoStatus);
        box.setBackground(new Background(new BackgroundFill(Color.rgb(204, 153, 0), CornerRadii.EMPTY, Insets.EMPTY)));

        leftBox.getChildren().addAll(label, box);
    }

    class converterListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            if (indexMenu == 0) // DA PRETO RECEBE VERMELHO
            {
                observable.converteRecurso(0, 1);
            } else if (indexMenu == 1) // DA PRETO RECEBE AZUL
            {
                observable.converteRecurso(0, 2);
            } else if (indexMenu == 2) // DA PRETO RECEBE VERDE
            {
                observable.converteRecurso(0, 3);
            } else if (indexMenu == 3) // DA VERMELHO RECEBE PRETO
            {
                observable.converteRecurso(1, 0);
            } else if (indexMenu == 4) // DA VERMELHO RECEBE AZUL
            {
                observable.converteRecurso(1, 2);
            } else if (indexMenu == 5) // DA VERMELHO RECEBE VERDE
            {
                observable.converteRecurso(1, 3);
            } else if (indexMenu == 6) // DA AZUL RECEBE PRETO
            {
                observable.converteRecurso(2, 0);
            } else if (indexMenu == 7) // DA AZUL RECEBE VERMELHO
            {
                observable.converteRecurso(2, 1);
            } else if (indexMenu == 8) // DA AZUL RECEBE VERDE
            {
                observable.converteRecurso(2, 3);
            } else if (indexMenu == 9) // DA VERDE RECEBE PRETO
            {
                observable.converteRecurso(3, 0);
            } else if (indexMenu == 10) // DA VERDE RECEBE VERMELHO
            {
                observable.converteRecurso(3, 1);
            } else if (indexMenu == 11) // DA VERDE RECEBE AZUL
            {
                observable.converteRecurso(3, 2);
            } else if (indexMenu == 12) // REVIVER TRIPULANTE CAPTAIN
            {
                observable.revive(6);
            } else if (indexMenu == 13) // REVIVER TRIPULANTE NAVIGATION
            {
                observable.revive(5);
            } else if (indexMenu == 14) // REVIVER TRIPULANTE EXPLORATION
            {
                observable.revive(4);
            } else if (indexMenu == 15) // REVIVER TRIPULANTE SHIELDS
            {
                observable.revive(3);
            } else if (indexMenu == 16) // REVIVER TRIPULANTE WEAPONS
            {
                observable.revive(2);
            } else if (indexMenu == 17) // REVIVER TRIPULANTE CARGO
            {
                observable.revive(1);
            } else if (indexMenu == 18) // Melhorar Cargo Hold
            {
                observable.melhorarCargo();
            } else if (indexMenu == 19) // Melhorar Weapon System
            {
                observable.melhorarWeaponSys();
            } else if (indexMenu == 20) // Reabastecer Shields
            {
                observable.reabastecerShields();
            } else if (indexMenu == 21) // Comprar Drone
            {
                observable.compraDrone();
            } else if (indexMenu == 22) // Comprar Shield
            {
                observable.compraShield();
            } else if (indexMenu == 23) // Comprar Fuel
            {
                observable.compraFuel();
            }
        }
    }

    private MenuItem createItem(String text, MenuButton button, Label label, String custo, int index) {
        MenuItem item = new MenuItem(text);
        item.setOnAction(e -> {
            label.setText("Custo: " + custo);
            button.setText(text);
            this.indexMenu = index;
        });
        indexMenu++;
        return item;
    }

    public final void setupComponentes() {
        HBox auxRight = new HBox();

        MenuButton menuConverter = new MenuButton("Escolha opção: ");
        menuConverter.setPrefWidth(180);
        Label custoLabel = new Label("Custo: (escolha opção)");

        Menu subConverterMenu = new Menu("Converter Recurso");
        Menu converterPretoMenu = new Menu("Preto ->");
        Menu converterAzulMenu = new Menu("Vermelho ->");
        Menu converterVermelhoMenu = new Menu("Azul ->");
        Menu converterVerdeMenu = new Menu("Verde ->");

        subConverterMenu.getItems().addAll(converterPretoMenu, converterAzulMenu, converterVermelhoMenu, converterVerdeMenu);

        converterPretoMenu.getItems().add(createItem("<- Vermelho", menuConverter, custoLabel, "1 recurso preto", indexMenu));
        converterPretoMenu.getItems().add(createItem("<- Azul", menuConverter, custoLabel, "1 recurso preto", indexMenu));
        converterPretoMenu.getItems().add(createItem("<- Verde", menuConverter, custoLabel, "1 recurso preto", indexMenu));

        converterAzulMenu.getItems().add(createItem("<- Preto", menuConverter, custoLabel, "1 recurso vermelho", indexMenu));
        converterAzulMenu.getItems().add(createItem("<- Azul", menuConverter, custoLabel, "1 recurso vermelho", indexMenu));
        converterAzulMenu.getItems().add(createItem("<- Verde", menuConverter, custoLabel, "1 recurso vermelho", indexMenu));

        converterVermelhoMenu.getItems().add(createItem("<- Preto", menuConverter, custoLabel, "1 recurso Azul", indexMenu));
        converterVermelhoMenu.getItems().add(createItem("<- Vermelho", menuConverter, custoLabel, "1 recurso Azul", indexMenu));
        converterVermelhoMenu.getItems().add(createItem("<- Verde", menuConverter, custoLabel, "1 recurso Azul", indexMenu));

        converterVerdeMenu.getItems().add(createItem("<- Preto", menuConverter, custoLabel, "1 recurso verde", indexMenu));
        converterVerdeMenu.getItems().add(createItem("<- Vermelho", menuConverter, custoLabel, "1 recurso verde", indexMenu));
        converterVerdeMenu.getItems().add(createItem("<- Azul", menuConverter, custoLabel, "1 recurso verde", indexMenu));

        menuConverter.getItems().add(subConverterMenu);

        Menu subReviverMenu = new Menu("Reviver tripulante");

        subReviverMenu.getItems().add(createItem("Reviver Captain", menuConverter, custoLabel, "1 de cada", indexMenu));
        subReviverMenu.getItems().add(createItem("Reviver Navigation", menuConverter, custoLabel, "1 de cada", indexMenu));
        subReviverMenu.getItems().add(createItem("Reviver Exploration", menuConverter, custoLabel, "1 de cada", indexMenu));
        subReviverMenu.getItems().add(createItem("Reviver Shields", menuConverter, custoLabel, "1 de cada", indexMenu));
        subReviverMenu.getItems().add(createItem("Reviver Weapons", menuConverter, custoLabel, "1 de cada", indexMenu));
        subReviverMenu.getItems().add(createItem("Reviver Cargo", menuConverter, custoLabel, "1 de cada", indexMenu));

        menuConverter.getItems().add(subReviverMenu);

        menuConverter.getItems().add(createItem("Melhorar Cargo Hold", menuConverter, custoLabel, "2 de cada", indexMenu));
        menuConverter.getItems().add(createItem("Melhorar Weapon System", menuConverter, custoLabel, "sem custo", indexMenu));
        menuConverter.getItems().add(createItem("Reabastecer Shields", menuConverter, custoLabel, "1 de cada", indexMenu));
        menuConverter.getItems().add(createItem("Comprar drone", menuConverter, custoLabel, "3 de cada", indexMenu));
        menuConverter.getItems().add(createItem("Comprar Shield", menuConverter, custoLabel, "1 preto, 1 azul, 1 verde", indexMenu));
        menuConverter.getItems().add(createItem("Comprar Fuel", menuConverter, custoLabel, "1 preto, 1 vermelho, 1 verde", indexMenu));

        Button converterButton = new Button("Converter");
        Button sairButton = new Button("Sair da troca de recursos");
        this.setAlignment(Pos.CENTER);
        auxRight.getChildren().addAll(menuConverter, custoLabel, converterButton, sairButton);
        auxRight.setPadding(new Insets(50));
        auxRight.setBackground(new Background(
                new BackgroundFill(Color.SKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        rightBox.getChildren().addAll(auxRight);
        menuConverter.requestFocus();
        converterButton.setOnAction(new converterListener());
        sairButton.setOnAction(e -> observable.saiConverter());
    }

    void setRight() {

        rightBox = base.getBasicVBox();
        Label label;
        label = base.makeLabelTop("Options", 30, Color.RED);
        rightBox.setPadding(new Insets(10));
        rightBox.setAlignment(Pos.TOP_CENTER);

        rightBox.getChildren().addAll(label);
        setupComponentes();
    }

    private void createPane(ObservableStatesMachine obs) {

        StackPane top = base.getTop();
        Label lblTop = base.makeLabelTop("Convert Resources");
        top.getChildren().add(lblTop);

        HBox center = new HBox();

        center.setPrefSize(base.getCenter().getPrefWidth(), base.getCenter().getPrefHeight());
        center.setMinSize(base.getCenter().getPrefWidth(), base.getCenter().getPrefHeight());
        //center.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        center.setBackground(new Background(new BackgroundImage(new Image("\\UI\\grafic\\images\\station.jpg"), BackgroundRepeat.ROUND, BackgroundRepeat.ROUND, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        center.setAlignment(Pos.CENTER);

        setLeft();
        setRight();

        
        base.setTopBox(top);
        base.setLeftBox(leftBox);
        base.setRightBox(rightBox);
       
        
        this.getChildren().clear();
        this.getChildren().add(base);
    }

    private void registerListeners() {
//        btnMove.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                observable.moveShip();
//            }
//        });
//
//        btnExit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                observable.voltaInicio();
//            }
//        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (observable.getState() instanceof AwaitMove) {
            createPane(observable);
            registerListeners();
        }
    }
}
