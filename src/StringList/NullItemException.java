package StringList;

public class NullItemException extends NullPointerException {
    public NullItemException() {
        super();
    }

    public NullItemException(String s) {
        super(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
