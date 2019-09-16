package com.example.livros.Model;

import java.util.List;

public class PublishCompany {

    private String name;
    private List<Book> booksByCompany;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksByCompany() {
        return booksByCompany;
    }

    public void setBooksByCompany(List<Book> booksByCompany) {
        this.booksByCompany = booksByCompany;
    }

}
