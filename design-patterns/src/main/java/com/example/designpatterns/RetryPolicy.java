package main.java.com.example.designpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class RetryPolicy {
    private static final Logger logger = LoggerFactory.getLogger(RetryPolicy.class);

    private final int maxAttempts;
    private final long initialBackoffMillis;

    public RetryPolicy(int maxAttempts, long initialBackoffMillis) {
        if (maxAttempts < 1) throw new IllegalArgumentException("maxAttempts must be >= 1");
        this.maxAttempts = maxAttempts;
        this.initialBackoffMillis = initialBackoffMillis;
    }

    public <T> T execute(Callable<T> callable) throws Exception {
        Exception last = null;
        long backoff = initialBackoffMillis;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return callable.call();
            } catch (Exception e) {
                last = e;
                logger.warn("Attempt {}/{} failed: {}", attempt, maxAttempts, e.getMessage());
                if (attempt == maxAttempts) break;
                try {
                    Thread.sleep(backoff);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw ie;
                }
                backoff *= 2; // exponential backoff
            }
        }
        throw last;
    }
}
