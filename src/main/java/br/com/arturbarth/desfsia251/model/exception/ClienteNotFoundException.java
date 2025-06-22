package br.com.arturbarth.desfsia251.model.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(String message) {
        super(message);
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
