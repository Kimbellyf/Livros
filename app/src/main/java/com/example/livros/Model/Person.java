package com.example.livros.Model;

public class Person {
    private String name;
    private FavList favList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FavList getFavList() {
        return favList;
    }

    public void setFavList(FavList favList) {
        this.favList = favList;
    }
}
