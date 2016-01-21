package base.os;

public class Exception extends RuntimeException {

    public Exception()
    {
        super();
    }

    public Exception(String detailMessage, Throwable throwable)
    {
        super(detailMessage, throwable);
    }

    public Exception(String detailMessage)
    {
        super(detailMessage);
    }

    public Exception(Throwable throwable)
    {
        super(throwable);
    }
}
