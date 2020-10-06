package it.mrbackslash.jmcrc;
import com.sun.istack.internal.NotNull;
import java.util.Random;
import java.io.*;
import java.net.Socket;

/**
* RCON Client for Minecraft servers implemented in Java
*
*   This software is distributed under the Apache License 2.0
*   Copyright 2020 Vittorio Lo Mele
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*
* @see <a href="https://github.com/mrBackSlash-it/jmcrc">GitHub Repository</a>
* @author mrBackSlash-it
 */

public class Jmcrc {
    private static String chost;
    private static int cport;
    private static boolean loggedIn;
    private static int requestId;
    private static Socket socket;
    private static OutputStream out;
    private static DataOutputStream dos;
    private static DataInputStream in;

    /**
     *
     * @param host Hostname or IP Address of the Minecraft Server
     * @param port TCP Port where server RCON is listening, usually 25575
     * @param password RCON Password
     * @return boolean
     * @throws UnableToConnectJmcrcException Throwed when server is unreachable
     */
    public static boolean init(@NotNull String host, int port, @NotNull String password) throws UnableToConnectJmcrcException, IOException, InvalidPayloadJmcrcException {
        if(loggedIn){
            throw new UnableToConnectJmcrcException();
        }
        try{
            socket = new Socket(host, port);
            out = socket.getOutputStream();
            dos = new DataOutputStream(out);
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }catch(Exception x) {
            throw new UnableToConnectJmcrcException();
        }
        //generate request id
        Random rand = new Random();
        requestId = rand.nextInt(2147483647);
        //assemble login packet
        byte[] loginPacket = PacketAssembler.AssemblePacket(requestId, 3, password);
        //write login packet
        dos.write(loginPacket);
        //reading packet length, not useful for validation of login packets
        int useless = in.readInt();
        useless = 0;
        //reading response id, if is the same as request id for login, the operation was successful
        int checkLogin = in.readInt();
        if(checkLogin != requestId){
            return false;
        }
        //set class variables
        chost = host;
        cport = port;
        loggedIn = true;
        return true;
    }

    /**
     * Gets the state of connection
     * @return boolean
     */
    public static boolean isConnected(){
        return loggedIn;
    }

    /**
     * Gets the current server address
     * @return String
     */
    public static String getAddress(){
        return chost;
    }

    /**
     * Gets the current server port
     * @return int
     */
    public static int getPort(){
        return cport;
    }

}
