package planet_bound;

import Logic.Data.ObservableStatesMachine;
import Logic.Data.StatesMachine;
import Logic.States.OnPlanet;
import UI.grafic.panes.PaneOrganizer;
import UI.text.UI;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

//public class Planet_Bound {
//
//    public static void main(String[] args) {
//        UI ui = new UI(new StatesMachine());
//        ui.run();
//    }
 

public class Planet_Bound extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StatesMachine statesMachine = new StatesMachine();

        ObservableStatesMachine observable = new ObservableStatesMachine(statesMachine);

        PaneOrganizer root = new PaneOrganizer(observable);
        Scene scene = new Scene(root);
    
        primaryStage.setResizable(false);
        primaryStage.setTitle("Planet Bound");
        primaryStage.getIcons().add(new Image("UI/grafic/images/icon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(e -> {
            if (observable.getState() instanceof OnPlanet) {
                if (e.getCode() == KeyCode.A) {
                    observable.move('A');
                } else if (e.getCode() == KeyCode.W) {
                    observable.move('W');
                } else if (e.getCode() == KeyCode.D) {
                    observable.move('D');
                } else if (e.getCode() == KeyCode.S) {
                    observable.move('S');
                }
            }
        });
    }
}
