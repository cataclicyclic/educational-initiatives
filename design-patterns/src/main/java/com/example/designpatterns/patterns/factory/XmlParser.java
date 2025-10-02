package main.java.com.example.designpatterns.patterns.factory;

public class XmlParser implements DocumentParser {
    @Override
    public void parse(String content) {
        if (content == null) throw new IllegalArgumentException("content cannot be null");
        // dummy parse: just print length for demo
        System.out.println("Parsing XML content length=" + content.length());
    }
}
