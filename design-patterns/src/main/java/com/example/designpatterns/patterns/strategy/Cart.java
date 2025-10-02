package main.java.com.example.designpatterns.patterns.strategy;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(CartItem item) {
        if (item == null) throw new IllegalArgumentException("item cannot be null");
        items.add(item);
    }

    public double total() {
        return items.stream().mapToDouble(CartItem::subtotal).sum();
    }

    public int itemsCount() {
        return items.stream().mapToInt(CartItem::getQty).sum();
    }
}
