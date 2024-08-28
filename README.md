# JTrash

![Java Version](https://img.shields.io/badge/Java-17%2B-green)

## Description 📖

**JTrash** is a dynamic and engaging card game developed in Java. It uses the Java Swing library to provide a rich and interactive graphical user interface. The game is structured using the Model-View-Controller (MVC) pattern, ensuring a clear separation of concerns between the game's logic, user interface, and user input.

## Architecture 🏗️

- **Model:** Manages the core game logic, including game state, rules, and data structures. It ensures consistency and handles internal operations.
- **View:** Provides the graphical user interface using Java Swing components, offering an interactive and visually appealing experience. It updates dynamically in response to changes in the model.
- **Controller:** Processes user input and coordinates interactions between the model and view. It updates the model based on user actions and refreshes the view accordingly.

The Observer-Observable pattern is employed to manage real-time updates and feedback. The model acts as the observable, notifying view components (observers) of changes, such as game state updates or player actions, to keep the user interface synchronized with the game's state.

## Features 🌟

- **Interactive UI:** Rich graphical interface built with Java Swing.
- **Dynamic Gameplay:** Play against up to 3 artificial players with adjustable difficulty levels.
- **Scoring System:** Earn points based on game outcomes; wins increase your score, losses decrease it.
- **Customizable Settings:** Choose the number of hands required to win and adjust the game difficulty.

## How To Play 🎮

1. **Starting the Game:**
   - Load an existing player profile or create a new one.
   - Choose to play against 1, 2, or 3 artificial players.
   - Select a difficulty level between 1 and 10, which defines the number of hands needed to win.

2. **Scoring:**
   - Each game win awards 50 points.
   - Each game loss deducts 10 points.

3. **Rules:**
   - The game follows the [Trash card game rules](https://www.wikihow.com/Play-Trash).

## Installation 🛠️

1. **Clone the Repository**
   ```bash
   git clone https://github.com/LucaMica02/JTrash.git
   ```
3. **Install JDK**
  Ensure you have JDK version 17 or later installed. Download it from the official [Java website](https://www.oracle.com/java/technologies/downloads/?er=221886).
4. **Run The Project**
  - Navigate to the project directory:
    ```bash
    cd path/to/JTrash
    ```
  - Execute the Game:
    ```bash
    java -cp bin JTrash.JTrash
    ```

## Conclusion 🔚
**Have fun and enjoy playing the game! 🎉🃏**
