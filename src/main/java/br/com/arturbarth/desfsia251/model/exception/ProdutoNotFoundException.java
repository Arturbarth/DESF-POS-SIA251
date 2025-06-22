package br.com.arturbarth.desfsia251.model.exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
