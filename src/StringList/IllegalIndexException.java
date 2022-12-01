package StringList;

public class IllegalIndexException extends IllegalArgumentException {
    public IllegalIndexException() {
        super();
    }

    public IllegalIndexException(String s) {
        super(s);
    }

    public IllegalIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalIndexException(Throwable cause) {
        super(cause);
    }
}
