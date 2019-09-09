package besquared.api.exception;

/**
 * PlugwiseHAException
 */
public class PlugwiseHAException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PlugwiseHAException(String message) {
        super(message);
    }

    public PlugwiseHAException(Throwable cause) {
        super(cause);
    }

    public PlugwiseHAException(String message, Throwable cause) {
        super(message, cause);
    }
}