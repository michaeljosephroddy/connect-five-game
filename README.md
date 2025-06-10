# Connect-Five Documentation

## Project Description
This implementation of Connect Five uses a client-server architecture to provide a plain-text version of the game. Below are the core functionalities and design principles.

### Server Responsibilities:
- Maintains the game state and business logic.
- Processes player moves and determines game outcomes (win/loss/draw).
- Responds to client requests with the game state and player turn information.
- Ends the game if a client disconnects.

### Client Responsibilities:
- Prompts the player to enter their name upon startup.
- Notifies the player whether it is waiting for a second player or if the game is ready to start.
- Displays the game board and manages player input.
- Informs players when it’s their turn or when waiting for the opponent’s move.
- Displays the game result and winner’s name upon completion.

## How to Run the Application
1. **Start the Server:** Run the Spring Boot `Application` class to initialize the server.
2. **Start the Client:** Run the `Client` Java class to initialize the client.
3. **Connect Two Clients:** You can connect up to two client instances simultaneously to play the game.

## Technologies Used
- **Programming Language:** Java
- **Framework:** Spring Boot 2.0

The client communicates with the backend Spring Boot server via a REST API over HTTP. All game logic is handled on the server, while the client focuses on processing and displaying server responses.

#### Key Commands:
- **Check Game Status:**
  - Enter `check-status` in the console to retrieve the current game state.
- **Quit Game:**
  - Enter `quit` in the console to leave the game. This action ends the game on the server.

## Testing
Unit tests were implemented using the **TestNG Framework** to validate the functionality of the game’s key features, ensuring reliable and accurate performance.

## Suggested Improvements
The application’s current communication relies on HTTP, which is unidirectional. Enhancements to client-server interaction include:

1. **Long Polling:**
   - Reduce redundant requests by allowing the server to respond only when new data is available.
2. **WebSocket Communication:**
   - Enable real-time, bi-directional communication, allowing the server to push updates directly to clients.

