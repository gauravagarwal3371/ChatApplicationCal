`Java Chat Server`

Java Chat Server is a simple chat application built using Java and Spring Boot. It allows users to join a chat room,
send messages, and view chat history.

`Features`
* User authentication with basic username/password login.
* Creation of a single chat room upon server startup.
* Persistent storage of chat messages in an H2 Database.
* Sending and receiving messages in the chat room.
* RESTful endpoints for message sending and retrieval.
* WebSocket logic is implemented for real-time chat communication
* Unit testing.

`Technologies Used`
* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* JUnit 5
* Mockito
* Websocket

`Usage`
* Register a new user by making a POST request to /api/users/register with a JSON body containing the username and password.
* Login to the application using username and password with a POST request to api/users/login
* Send messages to the chat room by making a POST request to /api/chat/send with a JSON body containing the message and sender.
* Retrieve chat history by making a GET request to /api/chat/history.
* Retrieve messages for a particular user by making a GET request to api/chat/get/{username}
* WebSocket Controller is created for adding user and sending messages to the chat window
  
`Server Start and Testing Process` 
* Clone the code from repository.
* Do mvn clean install for downloading all the dependencies.
* Start the server and open a browser with the url http://localhost:8080
* You will be directed to login page. First you have to create the user using /api/users/register. You can import the collection for dummy data.
* Once the user is created you can login with the username and password.
* Then you will be redirected to chat application where you can enter any username and then it will take you inside the chat room.
* You can create multiple users for testing purpose and start sending text and receive text in real time.
* The owner of the message has the ability to delete the message that was sent before.
* Meanwhile when a new user enters the room he/she will be getting all the chat of other members. (We can change these feature as per our requirement)
* Rest Api's are also created and that api's can be tested using postman collection present in the repo.
