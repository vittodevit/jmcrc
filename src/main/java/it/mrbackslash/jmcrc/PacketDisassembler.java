/*
This software is distributed under the Apache License 2.0
Copyright 2020 Vittorio Lo Mele

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package it.mrbackslash.jmcrc;
import java.nio.ByteBuffer;

/**
 * PacketDisassembler class. Disassembles responses from the server. [VERSION 1.0]
 * @see <a href="https://github.com/mrBackSlash-it/jmcrc">GitHub Repository</a>
 * @author mrBackSlash-it
*/
public class PacketDisassembler {
    //constants
    public static final byte PACKET_S_LENGTH = 0;
    public static final byte PACKET_S_REQUEST_ID = 1;
    public static final byte PACKET_S_TYPE = 2;
    public static final byte PACKET_S_PAYLOAD = 3;

    /**
     *
     * @param packet byte[], contains the raw response from the server
     * @param type can be PACKET_S_LENGTH, PACKET_S_REQUEST_ID, PACKET_S_TYPE, PACKET_S_PAYLOAD depending on what you want to extract
     * @return byte[], the extracted data
     * @throws InvalidPacketJmcrcException invalid packet or type
     */
    public static byte[] disassemblePacket(byte[] packet, byte type) throws InvalidPacketJmcrcException{
        byte[] data;
        byte[] bRemainderLength = new byte[4];
        System.arraycopy(packet, 0, bRemainderLength, 0, 4);
        int PayloadLength = littleEndianToInt(bRemainderLength) - 8;
        try{
            //switch to select disassembly type
            switch(type){
                case PACKET_S_LENGTH:
                    data = new byte[4];
                    System.arraycopy(packet, 0, data, 0, 4);
                    break;
                case PACKET_S_REQUEST_ID:
                    data = new byte[4];
                    System.arraycopy(packet, 4, data, 0, 4);
                    break;
                case PACKET_S_TYPE:
                    data = new byte[4];
                    System.arraycopy(packet, 8, data, 0, 4);
                    break;
                case PACKET_S_PAYLOAD:
                    data = new byte[PayloadLength];
                    System.arraycopy(packet, 12, data, 0, PayloadLength);
                    break;
                default:
                    throw new InvalidPacketJmcrcException();
            }
        }catch(Exception x){
            throw new InvalidPacketJmcrcException();
        }
        return data;
    }

    /**
     * Converts little-endian int contained in a byte array (byte[4]) into int.
     * @param data byte array containing a byte[4] array
     * @return int
     */
    public static int littleEndianToInt(byte[] data){
        //if the array is not 4 bytes (not an int) returns 0
        if(data.length != 4){
            return 0;
        }
        return ByteBuffer.wrap(data).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
    }

    /**
     * Converts raw byte array to string
     * @param data byte array containing US-ASCII string
     * @return String ("UNSUPPORTED ENCODING" if the string is not US-ASCII"
     */
    public static String rawPayloadToASCII(byte[] data){
        try{
            return new String(data, "US-ASCII");
        }catch(Exception x){
            return "UNSUPPORTED ENCODING";
        }
    }
}
