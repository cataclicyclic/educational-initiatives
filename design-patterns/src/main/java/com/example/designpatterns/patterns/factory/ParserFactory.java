package main.java.com.example.designpatterns.patterns.factory;

import java.util.Locale;

public class ParserFactory {
    public DocumentParser createParser(String type) {
        if (type == null) throw new IllegalArgumentException("type cannot be null");
        switch (type.toLowerCase(Locale.ROOT)) {
            case "json":
                return new JsonParser();
            case "xml":
                return new XmlParser();
            default:
                throw new IllegalArgumentException("Unknown parser type: " + type);
        }
    }
}
