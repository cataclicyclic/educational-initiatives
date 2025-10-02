package main.java.com.example.designpatterns.patterns.observer;

public class EmailNotifier implements OrderObserver {
    private final String to;

    public EmailNotifier(String to) {
        this.to = to;
    }

    @Override
    public void onStatusChanged(Order order, Order.Status newStatus) {
        System.out.printf("Email to %s: Order %s changed to %s\n", to, order.getId(), newStatus);
    }
}
