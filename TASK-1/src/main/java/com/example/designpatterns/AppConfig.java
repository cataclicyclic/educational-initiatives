package main.java.com.example.designpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
    private final int heartbeatSeconds;

    private AppConfig(int heartbeatSeconds) {
        this.heartbeatSeconds = heartbeatSeconds;
    }

    public int getHeartbeatSeconds() {
        return heartbeatSeconds;
    }

    public static AppConfig load() {
        Properties p = new Properties();
        try (InputStream is = AppConfig.class.getResourceAsStream("/app.properties")) {
            if (is != null) {
                p.load(is);
                int hb = Integer.parseInt(p.getProperty("heartbeat.seconds", "10"));
                return new AppConfig(hb);
            }
        } catch (IOException e) {
            logger.warn("Failed to load app.properties, using defaults.", e);
        }
        return new AppConfig(10);
    }
}
