package main.java.com.example.designpatterns.patterns.decorator;

public class EncryptionDecorator extends MessageDecorator {

    public EncryptionDecorator(MessageService delegate) {
        super(delegate);
    }

    @Override
    public void send(String message) {
        String encrypted = encrypt(message);
        super.send(encrypted);
    }

    private String encrypt(String message) {
        // Dummy encryption logic for demo
        return "ENCRYPTED(" + message + ")";
    }
}
