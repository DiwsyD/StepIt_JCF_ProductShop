package org.itstep.jcf.shop.exception;

public class CheckoutException extends Exception {

    private static final String DEFAULT_MESSAGE = "Checkout failure: %s";

    public CheckoutException(String message) {
        super(String.format(DEFAULT_MESSAGE, message));
    }

    @Override
    public String getMessage() {
        return String.format(DEFAULT_MESSAGE, super.getMessage());
    }
}
