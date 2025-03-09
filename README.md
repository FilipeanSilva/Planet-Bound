# Planet Bound

## Project Description
This project implements the *Planet Bound* game, developed as part of the *Advanced Programming* course for the academic year 2019/20. The game was developed in **Java** using **NetBeans** and **JavaFX 11**.

The main objective was to create an application that allows users to play *Planet Bound* following specific rules, including the use of design patterns and a state machine to control the game logic.

## Implemented Features
- **Data model** based on a state machine.
- **Graphical User Interface (GUI)** for user interaction.
- **Polymorphic state system** to manage game phases.
- **Save and load game functionality** using files.
- **Random events** that influence the game flow.

## Project Structure
The project is divided into the following packages:

- **`logic`**: Contains the core game logic.
- **`logic.states`**: Implements the state machine hierarchy.
- **`logic.data`**: Responsible for storing game data.
- **`ui.gui`**: Implements the graphical user interface using JavaFX.

## Main Classes
- **`StateMachine`**: Manages state transitions.
- **`GameData`**: Stores all relevant game information.
- **`IStates`**: Interface for different game states.
- **`ObservableStateMachine`**: Observes and updates the game state in the GUI.
- **`PaneOrganizer`**: Responsible for organizing the graphical interface.

## Requirements
To run the project, you need:
- **Java 11+**
- **NetBeans IDE** (or another Java-compatible IDE)
- **JavaFX 11+**

## How to Run
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-repository/planet-bound.git
   cd planet-bound
   ```
2. **Open the project in NetBeans or another compatible IDE.**
3. **Compile and run the application.**

If the executable `.jar` file is available:
```sh
java -jar PlanetBound.jar
```

## Project Status
The project is functional, with all major features implemented. However, due to tight deadlines, testing was not conducted as extensively as desired.

## Author
**Filipe Silva**

## License
This project was developed for academic purposes and does not have a defined license.

