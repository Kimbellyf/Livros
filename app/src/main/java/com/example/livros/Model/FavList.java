package com.example.livros.Model;

import java.util.List;

public class FavList {
    public List<Book> books;
    //public List<Ad> books;

    public FavList(List<Book> books) {
        this.books = books;
    }
    public FavList() {
    }

    public List<Book> getAd() {
        return books;
    }
    public void setAd(List<Book> ads) {
        this.books = books;
    }
}
