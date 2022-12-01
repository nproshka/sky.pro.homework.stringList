package StringList;

public class RepositoryIsFullException extends RuntimeException {
    public RepositoryIsFullException() {
        super();
    }

    public RepositoryIsFullException(String message) {
        super(message);
    }

    public RepositoryIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryIsFullException(Throwable cause) {
        super(cause);
    }

    protected RepositoryIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
