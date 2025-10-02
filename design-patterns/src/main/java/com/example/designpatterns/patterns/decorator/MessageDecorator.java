package main.java.com.example.designpatterns.patterns.decorator;

public abstract class MessageDecorator implements MessageService {
    protected MessageService delegate;

    public MessageDecorator(MessageService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String message) {
        delegate.send(message);
    }
}
