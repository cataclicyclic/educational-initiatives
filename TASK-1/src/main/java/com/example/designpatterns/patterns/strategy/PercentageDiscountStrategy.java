package main.java.com.example.designpatterns.patterns.strategy;

public class PercentageDiscountStrategy implements DiscountStrategy {
    private final double percent;

    public PercentageDiscountStrategy(double percent) {
        if (percent < 0 || percent > 1) throw new IllegalArgumentException("percent must be 0..1");
        this.percent = percent;
    }

    @Override
    public double applyDiscount(Cart cart) {
        return cart.total() * (1 - percent);
    }
}
