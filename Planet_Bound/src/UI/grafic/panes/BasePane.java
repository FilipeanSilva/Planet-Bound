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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Screen;

public class BasePane extends StackPane implements PropertyChangeListener {

    ObservableStatesMachine observable;
    BorderPane javafxexamplePane;

    VBox leftBox = new VBox();
    VBox rightBox = new VBox();
    HBox center = new HBox();
    StackPane bottom = new StackPane();
    StackPane top = new StackPane();
    Label lblBot;
    MenuBar menuBar;

    public BasePane(ObservableStatesMachine obs) {
        observable = obs;
        obs.addPropertyChangeListener(this);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        BackgroundSize backgroundSize = new BackgroundSize(getMaxScreenX(), getMaxScreenY(), false, false, false, false);
        BackgroundImage myBI = new BackgroundImage(new Image("\\UI\\grafic\\images\\space.gif"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);

        setBackground(new Background(myBI));

        javafxexamplePane = buildPane(observable);
        menuBar = getMenuBar();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(menuBar, javafxexamplePane);
        this.getChildren().addAll(vbox);
    }

    private BorderPane buildPane(ObservableStatesMachine obs) {

        BorderPane bp = new BorderPane();
        leftBox = getBasicVBox();

        rightBox = getBasicVBox();

        bottom.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 51), CornerRadii.EMPTY, Insets.EMPTY)));
        bottom.setPrefSize(getMaxScreenX(), (getMaxScreenY()) * 0.125);
        bottom.setMinSize(getMaxScreenX(), (getMaxScreenY()) * 0.125);

        top.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 51), CornerRadii.EMPTY, Insets.EMPTY)));
        top.setPrefSize(getMaxScreenX(), (getMaxScreenY()) * 0.125);
        top.setMinSize(getMaxScreenX(), (getMaxScreenY()) * 0.125);

        center.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.3), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        center.setAlignment(Pos.CENTER_LEFT);
        center.setPrefSize(getMaxScreenX(), getMaxScreenY() * 0.75);
        center.setMinSize(getMaxScreenX(), getMaxScreenY() * 0.75);
        center.getChildren().addAll(leftBox, rightBox);

        bp.setCenter(center);
        bp.setBottom(bottom);
        bp.setTop(top);

        return bp;
    }

    public Label makeLabelTop(String txt) {
        Label lbl = new Label(txt);
        lbl.setTextFill(Color.rgb(204, 153, 0));
        lbl.setFont(new Font("Impact", 45));
        return lbl;
    }

    public Label makeLabelTop(String txt, int size) {
        Label lbl = new Label(txt);
        lbl.setTextFill(Color.rgb(204, 153, 0));
        lbl.setFont(new Font("Impact", size));
        return lbl;
    }

    public Label makeLabelTop(String txt, int size, Color cor) {
        Label lbl = new Label(txt);
        lbl.setTextFill(cor);
        lbl.setFont(new Font("Impact", size));
        return lbl;
    }

    public Label makeLabelBottom(String txt) {
        Label lbl = new Label(txt);
        lbl.setTextFill(Color.WHITE);
        lbl.setFont(new Font("Arial", 33));
        return lbl;
    }

    public VBox getBasicVBox() {
        VBox aux = new VBox();
        aux.setPrefSize(center.getPrefWidth() / 2, center.getPrefHeight());
        aux.setMinSize(center.getPrefWidth() / 2, center.getPrefHeight());
        aux.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.5), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        return aux;
    }

    public HBox getBasicCenter() {
        HBox aux = new HBox();
        aux.setBorder(new Border(new BorderStroke(Color.rgb(255, 255, 255, 0.3), BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        aux.setAlignment(Pos.CENTER_LEFT);
        aux.getChildren().addAll(getBasicVBox(), getBasicVBox());
        return aux;
    }

    public int getMaxScreenX() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        return ((int) screenBounds.getMaxX() / 2);
    }

    public int getMaxScreenY() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        return ((int) screenBounds.getMaxY() / 2);
    }

    public void setLeftBox(VBox leftBox) {
        this.leftBox = leftBox;
        rearrangeCenter();
    }

    public void setRightBox(VBox rightBox) {
        this.rightBox = rightBox;
        rearrangeCenter();
    }

    public void setTopBox(StackPane top) {
        this.top = top;
        getPane();
    }

    public StackPane getTop() {
        return this.top;
    }

    public void setBottomBox(StackPane bottom) {
        this.bottom = bottom;
        getPane();
    }

    public StackPane getBottom() {
        return this.bottom;
    }

    public void setCenter(HBox center) {
        this.center = center;
        getPane();
    }

    public HBox getCenter() {
        return center;
    }

    public void rearrangeCenter() {
        center.getChildren().clear();
        center.getChildren().addAll(leftBox, rightBox);
        getPane();
    }

    public void getPane() {
        VBox vbox = new VBox();
        vbox.getChildren().clear();
        this.getChildren().clear();

        BorderPane bp = new BorderPane();

        bp.setTop(top);
        bp.setCenter(center);
        bp.setBottom(bottom);

        javafxexamplePane = bp;

        vbox.getChildren().addAll(menuBar, javafxexamplePane);
        this.getChildren().addAll(vbox);
    }

    private MenuBar getMenuBar() {
        MenuBar menuBar = new MenuBar();

        // game menu
        Menu gameMenu = new Menu("_Game");

        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        MenuItem readMenuItem = new MenuItem("Load");
        readMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

        gameMenu.getItems().addAll(newMenuItem, readMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

        newMenuItem.setOnAction(new BasePane.NewObjMenuBarListener());
        readMenuItem.setOnAction(new BasePane.LoadObjMenuBarListener());
        saveMenuItem.setOnAction(new BasePane.SaveObjMenuBarListener());
        exitMenuItem.setOnAction(new BasePane.ExitListener());

        // help menu
        Menu helpMenu = new Menu("_Help");

        MenuItem helpContentMenuItem = new MenuItem("Help Contents");
        helpContentMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));

        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));

        helpMenu.getItems().addAll(helpContentMenuItem, aboutMenuItem);

        menuBar.getMenus().addAll(gameMenu, helpMenu);

        helpContentMenuItem.setOnAction(new BasePane.HelpContentListener());
        aboutMenuItem.setOnAction(new BasePane.AboutListener());

        return menuBar;
    }

    class NewObjMenuBarListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent t) {
           observable.voltaInicio();
        }
    }

    class LoadObjMenuBarListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            ButtonType sim = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType nao = new ButtonType("No", ButtonBar.ButtonData.NO);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure you want to load another Game?"
                    + "\nYou will lose all unsaved progress from the current game.", sim, nao);
            alert.setHeaderText("Atencion!");
            alert.setTitle("");
            alert.showAndWait();

            if (alert.getResult() == sim) {
                FileChooser fileChooser = new FileChooser();
//            fileChooser.setInitialDirectory(new File("/dados"));
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

                } else {
//                System.out.println("Operação cancelada");
                }
            }
        }
    }

    class SaveObjMenuBarListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            FileChooser fileChooser = new FileChooser();
//            fileChooser.setInitialDirectory(new File("./dados"));
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
            } else {
//                System.out.println("Operação cancelada");
            }
        }
    }

    class ExitListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Platform.exit();
        }
    }

    class HelpContentListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("Help");
            dialogoResultado.setTitle("");
            dialogoResultado.setContentText("Reag Planet Bound rules");
            dialogoResultado.showAndWait();
        }
    }

    class AboutListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
            dialogoResultado.setHeaderText("About");
            dialogoResultado.setTitle("");
            dialogoResultado.setContentText("TP 2020 - Filipe Silva (2016020567)");
            dialogoResultado.showAndWait();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (observable.getState() instanceof AwaitRoom) {
        } else if (observable.getState() instanceof AwaitChoose) {
        } else if (observable.getState() instanceof AwaitMove) {
        } else if (observable.getState() instanceof OnOrbit) {
        } else if (observable.getState() instanceof OnPlanet) {
        } else if (observable.getState() instanceof ConvertResources) {
        }
    }

}
