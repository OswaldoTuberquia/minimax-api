package co.edu.umb.academia.ia.minimax.exceptions;

public class ResultadoException extends Exception {

    private static final long serialVersionUID = 1L;

    public ResultadoException(String message) {
        super(message);
    }

    public ResultadoException(String message, Throwable cause) {
        super(message, cause);

    }
}
