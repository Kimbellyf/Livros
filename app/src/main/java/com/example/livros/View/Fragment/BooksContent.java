package com.example.livros.View.Fragment;

import com.example.livros.Model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BooksContent {
   /* An array of sample (dummy) items.
            */
    public static final List<NewBookItem> ITEMS = new ArrayList<NewBookItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, NewBookItem> ITEM_MAP = new HashMap<String, NewBookItem>();

    //private static final int COUNT = ITEMS.size();
    private static final int COUNT = 25;


    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createBookItem(i));
        }
    }


    private static void addItem(NewBookItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
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
    /**
     * A dummy item representing a piece of content.
     */
}
