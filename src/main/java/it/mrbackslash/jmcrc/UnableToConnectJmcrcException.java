package it.mrbackslash.jmcrc;

public class UnableToConnectJmcrcException extends Exception{
    UnableToConnectJmcrcException() {
        super("Unable to connect to the server. Check your internet connection!");
    }
}
