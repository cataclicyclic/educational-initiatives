package main.java.com.example.designpatterns.patterns.builder;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private final String title;
    private final String author;
    private final List<String> sections;
    private final boolean footerEnabled;

    private Report(Builder b) {
        this.title = b.title;
        this.author = b.author;
        this.sections = List.copyOf(b.sections);
        this.footerEnabled = b.footerEnabled;
    }

    public static class Builder {
        private final String title;
        private String author = "unknown";
        private final List<String> sections = new ArrayList<>();
        private boolean footerEnabled = false;

        public Builder(String title) {
            if (title == null || title.isBlank()) throw new IllegalArgumentException("title is required");
            this.title = title;
        }

        public Builder withAuthor(String author) { this.author = author; return this; }
        public Builder addSection(String header, String body) {
            this.sections.add(header + ": " + body);
            return this;
        }
        public Builder enableFooter(boolean enable) { this.footerEnabled = enable; return this; }

        public Report build() { return new Report(this); }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report: ").append(title).append("\nAuthor: ").append(author).append("\n");
        for (String s : sections) sb.append("- ").append(s).append("\n");
        if (footerEnabled) sb.append("--- End of Report ---\n");
        return sb.toString();
    }
}
