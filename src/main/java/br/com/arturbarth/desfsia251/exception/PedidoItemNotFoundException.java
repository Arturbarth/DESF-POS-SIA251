package br.com.arturbarth.desfsia251.exception;

public class PedidoItemNotFoundException extends RuntimeException {

    public PedidoItemNotFoundException(String message) {
        super(message);
    }

    public PedidoItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
