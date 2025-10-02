package main.java.com.example.designpatterns.patterns.decorator;

public class SimpleMessageService implements MessageService {
    @Override
    public void send(String message) {
        System.out.println("Sending message: " + message);
    }
}
