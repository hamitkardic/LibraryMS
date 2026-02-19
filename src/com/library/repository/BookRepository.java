package com.library.repository;

import com.library.model.Book;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private static final String BOOKS_FILE = "books.json";

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        try {
            if (!Files.exists(Paths.get(BOOKS_FILE))) return books;

            String content = new String(Files.readAllBytes(Paths.get(BOOKS_FILE)));
            if (content.isBlank()) return books;

            content = content.replace("[", "").replace("]", "");
            String[] entries = content.split("\\},\\{");

            for (String entry : entries) {
                entry = entry.replace("{", "").replace("}", "");
                String[] fields = entry.split(",");

                String title = "";
                String author = "";

                for (String field : fields) {
                    String[] pair = field.split(":");
                    if (pair.length < 2) continue;

                    String key = pair[0].replace("\"", "").trim();
                    String value = pair[1].replace("\"", "").trim();

                    if (key.equals("title")) title = value;
                    if (key.equals("author")) author = value;
                }

                if (!title.isEmpty() && !author.isEmpty()) {
                    books.add(new Book(title, author));
                }
            }

        } catch (IOException ignored) {}

        return books;
    }

    public void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKS_FILE))) {
            writer.println("[");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                writer.print("  {\"title\":\"" + book.getTitle() +
                        "\",\"author\":\"" + book.getAuthor() + "\"}");

                if (i < books.size() - 1) writer.println(",");
                else writer.println();
            }
            writer.println("]");
        } catch (IOException ignored) {}
    }
}
