package com.example.livros.Model;

public class Person {
    private String name;
    private FavList favList;
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", favList=" + favList +
                ", user=" + user +
                '}';
    }
}
