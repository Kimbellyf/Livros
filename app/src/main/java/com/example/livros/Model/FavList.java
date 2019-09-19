package com.example.livros.Model;


import com.example.livros.View.booksgeneral.NewBookItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavList {
    public static final List<NewBookItem> ITEMS = new ArrayList<NewBookItem>();
    public static final Map<String, NewBookItem> ITEM_MAP = new HashMap<String, NewBookItem>();
    private static final int COUNT = ITEMS.size();

    public static List<NewBookItem> getITEMS() {
        return ITEMS;
    }

    public static Map<String, NewBookItem> getItemMap() {
        return ITEM_MAP;
    }


    public static void addItem(NewBookItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }
    public void additemaq(NewBookItem itemhere){
        addItem(itemhere);
    }

    private static NewBookItem createBookItem(int position) {
        //return new Book(String.valueOf(position), "Item " + position, makeDetails(position));
        return new NewBookItem();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
