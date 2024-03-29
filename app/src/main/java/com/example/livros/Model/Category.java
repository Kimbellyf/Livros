package com.example.livros.Model;

import java.util.List;

public class Category {
    public String id;
    public String content;
    private String name;
    private List<Book> booksofcategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksofcategory() {
        return booksofcategory;
    }

    public void setBooksofcategory(List<Book> booksofcategory) {
        this.booksofcategory = booksofcategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", booksofcategory=" + booksofcategory +
                '}';
    }
}
