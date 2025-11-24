package org.example.exception;
/// menejo de errores
public class DnaHashCalculationException extends RuntimeException {

    public DnaHashCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}