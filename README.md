# 5-in-a-row

Connect Five (also known as 5 in a row) is a two-player connection board game, in which the players take turns dropping colored discs into a nine-column, six-row vertically suspended grid. 
The pieces fall straight down, occupying the lowest available space within the column. The objective of the game is to be the first to form a horizontal, vertical, or diagonal line of four of one's own discs.

# The Task

Using a client-server architecture, implement a plain text version of the game using any language.

- The server application holds the state and businees logic of the game, receiving the movements from the players and deciding whether a player has won, or the game is over. The state of the game, and who's turn it is , will be returned to the client upon request. The communication between the clients and server should be over HTTP.
- The server upon start waits for the clients to connect, if one of the clienst disconnects, the game is over.
- The client prompts the player to enter her name upon start, and displays whether it is waitnig for a second player, or the game can start.
- On each turn, the client displays the state of the board and prompts the corresponding player for input or displays that is it waiting for the other players input.
- The client receives the input from the standard input (stdin).
- The client displays when the game is over and the name of the winner.

# My Implementation

### To Run This Application

- Run the Spring Boot Application class to boot up the server.
- Run the Client Java class to boot up the client.
- You can run up to two clinet applications at a time.

### Languages/tools

 - Java
 - Spring Boot 2.0
 
 The Java client interacts with the backend Spring Boot server through a REST API that communicates over HTTP protocol.
 All of the game logic is handled on the backend server and the client merely requests the game logic from the server and processes/displays the response. 
 
 ## Code Structure
 
 ### Server
 
 The server application is made up of 5 classes:
 
- Application --> the entry point for the Spring Boot app.
- Game --> models the game. e.g a game can have players, a winner, a game status, a players turn and a number of players.
- GameController --> holds the routes or endpoints and intercepts incoming requests.
- GameService --> holds the game logic.
- Player --> models the player of the game. e.g a player can have a name and piece.

### Client

the client application is a Java class named Client. The client prompts the player for input requests the game logic from the server usnig Java's HTTP Client.

- At anytime you can check the status of the game by entering "check-status" in the console. This command will check and return the current game status and game state. (will be useful after aplayer makes amove).
- At any time a client can leave the game by entering "quit" in the console. This will remove the player from the game and the game will end.

# Testing

Unfortunately, due to other responsibilities and time constraints, I did not get a chance to write any unit or integration tests.
I carried out manual testing throughout the development process and I am confident the application works as the requirements states but further testig is definitely needed to test all possible inputs and outputs.
In the future, I would consider writing automated unit and integration tests with the TestNG framework.

# Considerations

I thought this was a really interesting project to work on and I definitely learned a lot throughout the development process as I encountered some scenarios/issues that I have never seen before.

# Improvements

As this application communicates over HTTP and HTTP is uni-directional, the client must request the data from the server and the server will then respond to the client.
This can be quite inconveinient as the client needs to "check the game status" after a player joins, makes a move or leaves the game etc..
To improve this client/server interaction I would consider:

- Long polling the requests and only responding to the client when the data is made availabe on the server.
- Implementing web socket communication as web sockets allow for bi-directional communciatin.
