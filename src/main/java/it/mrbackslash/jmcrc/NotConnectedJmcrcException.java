package it.mrbackslash.jmcrc;

public class NotConnectedJmcrcException extends Exception{
    NotConnectedJmcrcException(){
        super("You are not connected to any server!");
    }
}
