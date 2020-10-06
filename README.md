### JMCRC (Java implementation of MineCraft Rcon Client)

**How to install (maven):**     
Click packages here on the right, select the latest version and follow the instructions.

**How to install without maven:**  
Download JAR file in releases section and import it in your IDE.

**HOW TO USE:**  
Check out javadoc here ->  [https://mrbackslash-it.github.io/jmcrc/](https://mrbackslash-it.github.io/jmcrc/)    
Check out the example here ->  [https://github.com/mrBackSlash-it/jmcrc/blob/master/src/test/java/LibTester.java](https://github.com/mrBackSlash-it/jmcrc/blob/master/src/test/java/LibTester.java)

**Main methods:**  
```init(String host, int port, String password)``` Connects to the server  
```send(String payload)``` Sends a command   
```disconnect()``` closes tcp socket and disconnects from the server  
```isConencted()``` returns connection state (boolean)
```getAddress() and getPort()``` they return the address and the port of that instance
