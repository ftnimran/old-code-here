package com.res.exception;


public class RESBusinessException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new runtime exception with
     * <code>null</code> as its detail message. The cause is not initialized,
     * and may subsequently be initialized by a call to {@link #initCause}.
     */
    public RESBusinessException() {
        super();
    }

    /**
     * Constructor which takes the message as input and a business message code.
     * It in turn calls the corresponding parent constructor
     *
     * @param String
     * @param String
     */
    public RESBusinessException(final String message) {
        super(message);
    }

    /**
     * Constructor which takes a message and another exception as input. The
     * input message and input exception's stack trace are stuffed into this.
     *
     * @param String
     * @param String
     * @param Throwable
     */
    public RESBusinessException(final String message, final Throwable cause) {        
        super(message, cause);
    }

}
