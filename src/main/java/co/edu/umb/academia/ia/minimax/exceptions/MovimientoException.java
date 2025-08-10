package co.edu.umb.academia.ia.minimax.exceptions;

public class MovimientoException extends Exception {

    private static final long serialVersionUID = 1L;

    public MovimientoException(String message) {
        super(message);
    }

    public MovimientoException(String message, Throwable cause) {
        super(message, cause);

    }
}
