# Elena-Server

Library to implement an asynchronous distributed slave network.
The client source code is available [here](https://github.com/lanquemar/Elena-Client).


# Description

This library permits to create an asynchronous distributed slave network. For instance, when you open a socket from the Elena server to another server (called target server), all the incoming and outgoing traffic pass through the client - you can see a client as a proxy. In a nutshell, the server controls all the clients' network interactions and keeps all the processing fully hidden from the clients.
Note that this network mechanism could prevent the IP address blacklist.


# Requirements

+ jdk >= 1.8.0
+ maven >= 3.3.9

# Build

In the project directory, run
```
mvn clean install
```

If no error occurred, you have a new directory called "target" which contains a JAR file named "Elena-Server.jar"


# Installation

Import this file as library to your current project.
*(Ask Google if you don't know how to import JAR library according to your IDE)*


# Usage

## Event handling

**You have to write a class which inherits EventHandler.**

Then, you must override four methods :

```java
void onHello(Client client, long timestamp, String hostname, String username);
```
*This method will be called when the client will open a socket to Elena server.*

Variables description
> + client: Instance of Client class
> + timestamp: Actual timestamp
> + hostname: Not use
> + username: Not use

```java
void onSocketOpen(Client client, Socket socket, int errorCode);
```
*This method will be called when the socket will be completely open between the client and the target server.*

Variables description
> + client: Current client
> + socket: Current socket
> + errorCode: Opening error code

```java
void onSocketClose(Client client, Socket socket, int errorCode);
```
*This method will be called when the socket will be completely close between client and the target server.*

Variables description
> + client: Current client
> + socket: Current socket
> + errorCode: Closing error code

```java
void onRecv(Client client, Socket socket, byte[] data);
```
*This method will be called when the client socket will receive new data from the target server.*

Variables description
> + client: Current client
> + socket: Current socket
> + data: Data received

If you are in trouble with your class implementation, you have an example here.


## Protocol

**Now, you can write the protocol that your clients will follow.**

For that, you have to interact with some methods :

### Client class
```java
void openSocket(String host, int port);
```
*This method will open a socket.*

Variables description
> + host: Target server URL or IP address
> + port: Target server port

### Socket class
```java
void write(byte[] data);
```
*This methods will send data in the socket.*

Variables description
> + data: Data to send

```java
void close();
```
*This method will close the socket.*

You can check some examples out by clicking here.


## Initialization

**You have to declare a Server instance with your previous class in parameter.**

```java
Server server = new Server(yourClass);
```

# TODO

  + Implement SSL handshake and CA verification
  + Add customizable data in Client class
  + Add customizable data in Socket class 


# Contact

If you have any question about the project, feel free to contact me on Twitter: [@bla5r](https://twitter.com/bla5r) 
