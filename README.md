# COMP3111F23G47 - COMP3111 Project

![Project Group Sign Up Form](./Project_Group_Sign_Up_Form.png)

## Welcome to our COMP3111 Project GitHub page!

### Maze Game

Our project is an auto-generated maze game developed using JavaFX and Java Swing. The game comprises three main functions:

1. **Function A:** Maze Generation Demonstration.
2. **Function B:** Shortest Path Calculation Demonstration.
3. **Function C:** Main Gameplay.

Map folders store:
1. Maze map in a CSV file
2. Entry and Exit points in a CSV file
3. Shortest path in a CSV file

### How the Main Menu Looks

![Main Menu](https://i.imgur.com/bbbnEdc.png)

### How to Play

We have implemented three functions. To view the demonstration of each function, click the corresponding button.

### Function A

![Function A](https://i.imgur.com/zTxh5DQ.png)

After clicking **Function A**, if a maze map is present in the map folder, the window will displace the current stored maze map with Extry and Exit point highlighted in red and green. Else, a playable maze will be automatically generated with the Entry Point highlighted in red, and the Exit Point highlighted in green. You can always click the **Generate new map** button to generate a new maze.

### Function B

![Function B](https://i.imgur.com/vVbSKTD.png)

After clicking the **Function B** button, a maze with a highlighted yellow path will be displayed. This path represents the shortest route from the maze entry point to the exit point. You can generate a new maze by clicking the **Generate new map** button in **Function A**, and **Function B** will recalculate the shortest path accordingly.

### Function C

![Function C](https://i.imgur.com/tvRSd84.png)

After clicking the **Function C** button, the game starts and displays the maze generated in **Function A**. The Entry Point is highlighted in red, and the Exit Point is highlighted in green. Tom is in grey and is spawned at the Exit Point, while Jerry is in orange and is spawned at the Entry Point.

### Gameplay

Tom is controlled by the computer, which moves towards Jerry by the shortest path. Jerry is controlled by the player, who can navigate using the keyboard keys **UP**, **DOWN**, **LEFT**, and **RIGHT**. Tom's speed is slightly faster than Jerry's. Tom's objective is to catch Jerry. When Tom and Jerry occupy the same location, the game ends, and the player loses. Jerry's goal is to reach the Exit Point. Once Jerry reaches the Exit Point, the game ends, and the player wins.
