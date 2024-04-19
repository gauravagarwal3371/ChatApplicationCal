`Java Chat Server`

Java Chat Server is a simple chat application built using Java and Spring Boot. It allows users to join a chat room,
send messages, and view chat history.

`Features`
* User authentication with basic username/password login.
* Creation of a single chat room upon server startup. No need to create multiple rooms.
* Persistent storage of chat messages in an H2 Database.
* Sending and receiving messages in the chat room.
* RESTful endpoints for message sending and retrieval.
* Unit testing.

`Technologies Used`
* Java
* Spring Boot
* Spring Data JPA
* H2 Database
* Maven
* JUnit

`Usage`
* Register a new user by making a POST request to /api/users/register with a JSON body containing the username and password.
* Send messages to the chat room by making a POST request to /api/chat/send with a JSON body containing the message and sender.
* Retrieve chat history by making a GET request to /api/chat/history.
