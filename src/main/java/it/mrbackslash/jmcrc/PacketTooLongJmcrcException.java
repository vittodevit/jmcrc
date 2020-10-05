package it.mrbackslash.jmcrc;

public class PacketTooLongJmcrcException extends Exception{
    PacketTooLongJmcrcException(){
        super("The packet you are trying to send is too long!");
    }
}
