package main.java.com.example.designpatterns.patterns.strategy;

public class CartItem {
    private final String name;
    private final int qty;
    private final double pricePerUnit;

    public CartItem(String name, int qty, double pricePerUnit) {
        this.name = name;
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
    }

    public double subtotal() {
        return qty * pricePerUnit;
    }

    public int getQty() {
        return qty;
    }
}
