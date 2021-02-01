package me.voidless.voidlib;

public class MessageTooLongException extends Exception {
    public MessageTooLongException(final String msg) {
        super(msg);
    }
}
