package main.java.com.example.designpatterns.patterns.factory;

public class JsonParser implements DocumentParser {
    @Override
    public void parse(String content) {
        if (content == null) throw new IllegalArgumentException("content cannot be null");
        // dummy parse: just print length for demo
        System.out.println("Parsing JSON content length=" + content.length());
    }
}
