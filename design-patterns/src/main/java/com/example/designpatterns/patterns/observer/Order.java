package main.java.com.example.designpatterns.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public enum Status {PLACED, PROCESSING, SHIPPED, DELIVERED}

    private final String id;
    private Status status;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(String id) {
        this.id = id;
        this.status = Status.PLACED;
    }

    public void registerObserver(OrderObserver obs) {
        if (obs == null) throw new IllegalArgumentException("observer cannot be null");
        observers.add(obs);
    }

    public void setStatus(Status s) {
        this.status = s;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver o : observers) {
            try {
                o.onStatusChanged(this, this.status);
            } catch (Exception e) {
                // defensive: one failing observer should not block others
                System.err.println("Observer failed: " + e.getMessage());
            }
        }
    }

    public String getId() { return id; }
    public Status getStatus() { return status; }
}
