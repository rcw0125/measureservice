package com.talent.security.server.exception;

public class InvalidClientException extends Exception {

	private static final long serialVersionUID = -6245334090395559221L;

	public InvalidClientException() {
    }

    public InvalidClientException(String message) {
        super(message);
    }

    public InvalidClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidClientException(Throwable cause) {
        super(cause);
    }

    public InvalidClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}