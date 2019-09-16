package com.example.livros.Model;

import java.util.List;

public class Author {
    private String name;
    private  List<Book> booksByAuthor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getGetBooksByAuthor() {
        return booksByAuthor;
    }

    public void setbooksByAuthor(List<Book> getBooksByAuthor) {
        this.booksByAuthor = getBooksByAuthor;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", booksByAuthor=" + booksByAuthor +
                '}';
    }
}
