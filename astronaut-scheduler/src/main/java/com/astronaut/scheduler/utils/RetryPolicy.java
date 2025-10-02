package com.astronaut.scheduler.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class RetryPolicy {
    private static final Logger logger = LoggerFactory.getLogger(RetryPolicy.class);
    private final int maxAttempts;
    private final long initialBackoffMillis;

    public RetryPolicy(int maxAttempts, long initialBackoffMillis) {
        this.maxAttempts = Math.max(1, maxAttempts);
        this.initialBackoffMillis = Math.max(100, initialBackoffMillis);
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
                Thread.sleep(backoff);
                backoff *= 2;
            }
        }
        throw last;
    }
}
