package main.java.com.example.designpatterns.patterns.strategy;

public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(Cart cart) {
        return cart.total();
    }
}
