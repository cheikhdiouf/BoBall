package sn.dev.exception;

import java.io.Serial;

public class BadRequestAlertException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final Integer errorCode;

    public BadRequestAlertException(String message, Integer errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
