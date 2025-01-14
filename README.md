# 5-in-a-Row Documentation

## Overview
Connect Five (also known as 5-in-a-row) is a two-player connection board game. Players alternate turns dropping colored discs into a grid consisting of nine columns and six rows. The pieces fall to the lowest available space in the column. The objective is to be the first to form a horizontal, vertical, or diagonal line of five consecutive discs.

---

## The Task
Using a client-server architecture, implement a plain-text version of the game in any programming language.

### Requirements:
1. **Server Application:**
   - Manages the game state and business logic.
   - Receives player moves and determines if a player has won or if the game is over.
   - Returns the game state and turn information to the client upon request.
   - Waits for clients to connect upon startup. If a client disconnects, the game ends.

2. **Client Application:**
   - Prompts the player for their name upon startup.
   - Indicates whether it is waiting for a second player or ready to start.
   - Displays the board state on each turn and prompts the player for input or informs them it is waiting for the opponent’s move.
   - Receives input from the standard input (stdin).
   - Displays the game result and the winner's name when the game ends.

---

## My Implementation

### To Run the Application
1. Run the Spring Boot Application class to start the server.
2. Run the Client Java class to start the client.
3. Up to two client applications can connect simultaneously.

### Technologies Used
- **Java**
- **Spring Boot 2.0**

The client interacts with the backend Spring Boot server via a REST API over HTTP. The server handles all game logic, while the client processes and displays server responses.

---

## Code Structure

### Server
The server application consists of the following classes:

1. **`Application`**
   - Entry point for the Spring Boot application.
2. **`Game`**
   - Models the game, including players, game status, turn management, and winner determination.
3. **`GameController`**
   - Manages API routes and handles incoming requests.
4. **`GameService`**
   - Contains the core game logic.
5. **`Player`**
   - Models a player, including their name and assigned piece.

### Client
The client is implemented as a Java class named `Client`. It handles user input, sends requests to the server, and displays the server's responses.

#### Features:
- **Check Game Status:**
  - Enter `check-status` in the console to view the current game state and status after a move.
- **Quit Game:**
  - Enter `quit` in the console to leave the game. The server will end the game.

---

## Testing
- Unit tests were written using the **TestNG Framework** to verify the functionality of the game’s main features.

---

## Potential Improvements
Currently, the application communicates over HTTP, which is unidirectional. This means the client must actively request updates from the server. To enhance client-server interaction:

1. **Long Polling:**
   - The server responds only when new data is available, reducing unnecessary requests.
2. **WebSocket Communication:**
   - WebSockets enable bi-directional communication, allowing the server to push updates to the client in real time.

---

## Reflections
Working on this project was a valuable learning experience. It introduced me to new challenges and scenarios, deepening my understanding of client-server architecture and HTTP communication.
