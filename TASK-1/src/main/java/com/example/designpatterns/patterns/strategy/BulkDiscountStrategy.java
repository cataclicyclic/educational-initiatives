package main.java.com.example.designpatterns.patterns.strategy;

public class BulkDiscountStrategy implements DiscountStrategy {
    private final int threshold;
    private final double percent;

    public BulkDiscountStrategy(int threshold, double percent) {
        this.threshold = threshold;
        this.percent = percent;
    }

    @Override
    public double applyDiscount(Cart cart) {
        int totalItems = cart.itemsCount();
        if (totalItems >= threshold) {
            return cart.total() * (1 - percent);
        }
        return cart.total();
    }
}
