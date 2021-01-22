package UI.grafic.panes;

import Logic.Data.ObservableStatesMachine;
import Logic.Data.StatesMachine;
import Logic.Files.FileUtility;
import Logic.States.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class StartPane extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    Button btnStartGame = new Button();
    Button btnLoadGame = new Button();
    Button btnSaveGame = new Button();
    Button btnLog = new Button();
    Button btnSair = new Button();
    VBox leftBox;
    VBox rightBox;
    BasePane base;
    StackPane top;
    StackPane bottom;

    public StartPane(ObservableStatesMachine obs) {

        observable = obs;
        obs.addPropertyChangeListener(this);
        base = new BasePane(obs);
        createPane(obs);
        registerListeners();
    }

    void createPane(ObservableStatesMachine obs) {
        BasePane base = new BasePane(obs);

        //Define VBOX da esquerda
        leftBox = base.getBasicVBox();
        VBox auxLeft = new VBox();
        auxLeft.setPrefSize(leftBox.getPrefWidth(), leftBox.getPrefHeight() * 0.90);
        auxLeft.setMaxSize(leftBox.getPrefWidth(), leftBox.getPrefHeight() * 0.90);

        BackgroundImage myBI = new BackgroundImage(new Image("\\UI\\grafic\\images\\planetBound.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        //auxLeft.setBackground(new Background(myBI));

        ImageView imv = new ImageView(new Image("\\UI\\grafic\\images\\planetBound.jpg"));
        imv.setScaleX(1);
        imv.setScaleY(1);
        imv.preserveRatioProperty();
        imv.setFitWidth(auxLeft.getPrefWidth() * 0.6);
        imv.setFitHeight(auxLeft.getPrefHeight());
        imv.setSmooth(true);
        imv.setCache(true);
        auxLeft.getChildren().add(imv);
        auxLeft.setAlignment(Pos.CENTER);

        //leftBox.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        leftBox.setAlignment(Pos.CENTER);
        leftBox.getChildren().add(auxLeft);

        //Define VBOX da direita
        HBox arightBox = new HBox();
        arightBox.setPrefSize(base.getBasicVBox().getPrefWidth(), base.getBasicVBox().getPrefHeight());
        arightBox.setMinSize(base.getBasicVBox().getPrefWidth(), base.getBasicVBox().getPrefHeight());
        arightBox.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        btnStartGame.setText("Start Game");
        btnLoadGame.setText("Load Game");
        btnSaveGame.setText("Save Game");
        btnLog.setText("Show Logs");
        btnSair.setText("Exit");
        arightBox.getChildren().addAll(btnStartGame, btnLoadGame, btnSaveGame, btnLog, btnSair);
        arightBox.setAlignment(Pos.CENTER);
        arightBox.setSpacing(10);
        rightBox = new VBox(arightBox);

        //Define TOP
        top = base.getTop();
        Label lbl = base.makeLabelTop("Select options: ");
        top.getChildren().add(lbl);

        bottom = base.getBottom();
        

        base.setTopBox(top);
        base.setLeftBox(leftBox);
        base.setRightBox(rightBox);

        this.getChildren().add(base);
    }

    void registerListeners() {

        btnLoadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {

                    try {
                        StatesMachine stateMachineGame
                                = (StatesMachine) FileUtility.retrieveGameFromFile(selectedFile);
                        if (stateMachineGame != null) {
                            observable.setStatesMachine(stateMachineGame);
                            observable.update();
                        }
                        Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                        dialogoResultado.setHeaderText("Sucess!");
                        dialogoResultado.setContentText("Game was upload!");
                        dialogoResultado.showAndWait();

                    } catch (IOException | ClassNotFoundException ex) {
                        Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                        dialogoResultado.setHeaderText("Load");
                        dialogoResultado.setContentText("Operation failed: " + ex);
                        dialogoResultado.showAndWait();
                    }

                }
            }
        }
        );

        btnSaveGame.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                FileChooser fileChooser = new FileChooser();
                String data = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
                fileChooser.setInitialFileName("PlanetBound-" + data);
                File selectedFile = fileChooser.showSaveDialog(null);
                if (selectedFile != null) {
                    try {
                        FileUtility.saveGameToFile(selectedFile, observable.getStatesMachine());
                        Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                        dialogoResultado.setHeaderText("Sucess!");
                        dialogoResultado.setContentText("Game was saved!");
                        dialogoResultado.showAndWait();
                    } catch (IOException ex) {
                        Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                        dialogoResultado.setHeaderText("Save");
                        dialogoResultado.setContentText("Operation failed: " + ex);
                        dialogoResultado.showAndWait();
                    }
                }
            }
        });

        btnStartGame.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                observable.startGame();
            }
        }
        );

        btnLog.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t
            ) {
                System.out.print("[LOGS]: \n" + observable.showAllLogs());
            }
        }
        );

        btnSair.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t
            ) {
                Platform.exit();
            }
        }
        );

        leftBox.setOnMouseEntered(
                (t) -> {
                    Label lbl = base.makeLabelTop("Game Image");
                    top.getChildren().clear();
                    top.getChildren().add(lbl);

                    //leftBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(4))));
                }
        );

        leftBox.setOnMouseExited(
                (t) -> {
                    Label lbl = base.makeLabelTop("Select options: ");
                    top.getChildren().clear();
                    top.getChildren().add(lbl);

                    //leftBox.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
                }
        );

        rightBox.setOnMouseEntered(
                (t) -> {
                    Label lbl = base.makeLabelTop("Options");
                    top.getChildren().clear();
                    top.getChildren().add(lbl);
                }
        );

        rightBox.setOnMouseExited(
                (t) -> {
                    Label lbl = base.makeLabelTop("Select options: ");
                    top.getChildren().clear();
                    top.getChildren().add(lbl);
                }
        );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StackPane bot = base.getBottom();
        Label lblBot = new Label("[(Current State): " + observable.getState().getClass().getSimpleName() + "] \t [(Last Log): " + observable.showLogs() + "]");
        bot.getChildren().clear();
        bot.getChildren().add((lblBot));
        base.setBottomBox(bot);

        if (observable.getState() instanceof AwaitRoom) {
        } else if (observable.getState() instanceof AwaitChoose) {
        } else if (observable.getState() instanceof AwaitMove) {
        } else if (observable.getState() instanceof OnOrbit) {
        } else if (observable.getState() instanceof OnPlanet) {
        } else if (observable.getState() instanceof ConvertResources) {
        }
    }
}
