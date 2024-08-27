# JTrash

# Description
JTrash is a dynamic and engaging card game developed in Java, utilizing the Java Swing library for a rich graphical user interface. The game is designed with a robust architecture based on the Model-View-Controller (MVC) pattern, ensuring a clear separation between game logic, user interface, and user input.

# Architecture
Model: Represents the core game logic, including game state, rules, and data structures. It manages the game's internal operations and maintains consistency across the game.
View: Implements the graphical user interface using Java Swing components, providing an interactive and visually appealing experience for players. It updates in response to changes in the model.
Controller: Handles user input and interacts with both the model and view. It processes user actions, updates the model accordingly, and refreshes the view.

The game uses the Observer-Observable pattern to manage dynamic updates and real-time feedback. The game’s model acts as the observable, notifying various view components (observers) of changes, such as updates in game state or player actions. This ensures that the user interface reflects the current state of the game accurately and promptly.

# How To Play
You can load a current player or save a new player, you can choose to play against 1, 2 or 3 artificial players, and choose a level between 1 and 10, which represents the number of hands you need to win to win the game.
The game follows the rules described in https://www.wikihow.com/Play-Trash
For each game won the player earns 50 points, for each game lost he loses 10.

# How To Run
1 - Clone the repo
2 - Install JDK version 17+
4 - Open the terminal and from the path your/path/JTrash
3 - run the command: "java -cp bin JTrash.JTrash"
