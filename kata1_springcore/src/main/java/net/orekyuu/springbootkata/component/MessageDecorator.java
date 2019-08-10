package net.orekyuu.springbootkata.component;

public class MessageDecorator {
    private final String message;

    public MessageDecorator(String message) {
        this.message = message;
    }

    public String createDecoratedMessage() {
        return "[" + message + "]";
    }
}
