### JMCRC (Java implementation of MineCraft Rcon Client)
**Remotely control any Minecraft server with RCON enabled!**  

**How to enable RCON:**
In server.properties of your minecraft server set those values
```
enable-rcon=true
rcon.password=<your password>
rcon.port=<1-65535>
```

**How to install (maven):**     
Click packages here on the right, select the latest version and follow the instructions.

**How to install without maven:**  
Download JAR file in releases section and import it in your IDE.

**HOW TO USE:**  
Check out javadoc here ->  [https://mrbackslash-it.github.io/jmcrc/](https://mrbackslash-it.github.io/jmcrc/)    
Check out the example here ->  [https://github.com/mrBackSlash-it/jmcrc/blob/1.1/src/test/java/it/mrbackslash/JmcrcTester/LibTester.java](https://github.com/mrBackSlash-it/jmcrc/blob/1.1/src/test/java/it/mrbackslash/JmcrcTester/LibTester.java)

**Main methods:**  
```init(String host, int port, String password)``` Connects to the server  
```send(String payload)``` Sends a command   
```disconnect()``` closes tcp socket and disconnects from the server  
```isConencted()``` returns connection state (boolean)
```getAddress() and getPort()``` they return the address and the port of that instance
