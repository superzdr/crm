package cn.wolfcode.crm.util;

/**
 * Created by Albert on 2019/5/30.
 */
public class LogicException extends RuntimeException{
    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }
}
