JamChat
==========
Client library for the JamChat project

<b>*This project is a work in progress!*</b>

JamChat is a simple client-server communication service written in Java. The project is split into 3 separate parts.
<p/>
The core client is a library for a user front-end to utilize. This contains the core methods for the program and lets
the GUI or Terminal client front-end handle presentation and other aspects. This brings us to the second part of the
project; the user interface. The user interface should use the client core library for its main functions, while handling
what it does with the information from the core itself.
<p/>
The final part of the project is the server. As the server requires no user interface, the core is not a library and
should be run by itself. The server should manage a list of known clients and handle connecting them and maintaining the
connection between them.
