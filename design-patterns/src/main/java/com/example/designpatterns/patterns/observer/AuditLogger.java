package main.java.com.example.designpatterns.patterns.observer;

public class AuditLogger implements OrderObserver {
    @Override
    public void onStatusChanged(Order order, Order.Status newStatus) {
        System.out.printf("[AUDIT] order=%s status=%s\n", order.getId(), newStatus);
    }
}
