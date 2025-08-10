package co.edu.umb.academia.ia.minimax.exceptions;

public class PartidaException extends Exception {

    private static final long serialVersionUID = 1L;

    public PartidaException(String message) {
        super(message);
    }

    public PartidaException(String message, Throwable cause) {
        super(message, cause);

    }
}
