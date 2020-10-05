package it.mrbackslash.jmcrc;

public class InvalidPayloadJmcrcException extends Exception{
    InvalidPayloadJmcrcException() {
        super("The payload you entered is invalid! It's either too long or it contains non-ASCII characters.");
    }
}
