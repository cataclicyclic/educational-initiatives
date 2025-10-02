package main.java.com.example.designpatterns.patterns.decorator;

public class CompressionDecorator extends MessageDecorator {

    public CompressionDecorator(MessageService delegate) {
        super(delegate);
    }

    @Override
    public void send(String message) {
        String compressed = compress(message);
        super.send(compressed);
    }

    private String compress(String message) {
        // Dummy compression logic for demo
        return "COMPRESSED(" + message + ")";
    }
}
