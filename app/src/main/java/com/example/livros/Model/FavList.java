package com.example.livros.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavList {
    public static final List<Book> ITEMS = new ArrayList<Book>();
    public static final Map<String, Book> ITEM_MAP = new HashMap<String, Book>();
    //private static final int COUNT = 25;

    public static List<Book> getITEMS() {
        return ITEMS;
    }

    public static Map<String, Book> getItemMap() {
        return ITEM_MAP;
    }

}
