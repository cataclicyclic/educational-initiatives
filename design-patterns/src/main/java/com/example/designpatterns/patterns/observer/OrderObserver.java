package main.java.com.example.designpatterns.patterns.observer;

public interface OrderObserver {
    void onStatusChanged(Order order, Order.Status newStatus);
}
