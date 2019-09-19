package com.example.livros.View.authors.dummy;

import android.util.Log;

import com.example.livros.Model.Author;
import com.example.livros.Model.Category;
import com.example.livros.View.categories.dummy.CategoriesContent;

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
public class AuthorsContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Author> ITEMS = new ArrayList<Author>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Author> ITEM_MAP = new HashMap<String, Author>();

    private static final int COUNT = ITEMS.size();


    private static void addItem(Author item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Author createDummyItem(int position) {
        //return new Author(String.valueOf(position), "Item " + position, makeDetails(position));
        return new Author();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
    public static void setListITEMS(List<Author> authoriesO) {
        if (authoriesO.isEmpty()){

        }else{
            for (Author author: authoriesO){
                Log.d("authoriedstring", String.valueOf(author));
                AuthorsContent.addItem(author);
            }

        }
        //ITEMS = authoriesO;
    }
}
