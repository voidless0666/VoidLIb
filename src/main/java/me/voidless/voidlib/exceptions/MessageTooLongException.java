package me.voidless.voidlib.exceptions;

public class MessageTooLongException extends Exception {
    public MessageTooLongException(final String msg) {
        super(msg);
    }
}
