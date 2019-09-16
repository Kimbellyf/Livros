package com.example.livros.Model;

public class PublishedDate {
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PublishedDate{" +
                "date='" + date + '\'' +
                '}';
    }
}
