package com.example.livros.View.categories.categories.dummy;

import android.util.Log;

import com.example.livros.Model.Author;
import com.example.livros.Model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CategoriesContent {


    public static List<Category> ITEMS = new ArrayList<Category>();

    public static final Map<String, Category> ITEM_MAP = new HashMap<String, Category>();

    private static final int COUNT = ITEMS.size();



    public static void addItem(Category item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Category createDummyItem(int position) {
        //return new Category(String.valueOf(position), "Item " + position, makeDetails(position));
        return new Category();
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static void setListITEMS(List<Category> categoriesO) {
        if (categoriesO.isEmpty()){

        }else{
            for (Category category: categoriesO){
                Log.d("cataddstring", String.valueOf(category));
                CategoriesContent.addItem(category);
            }

        }
        //ITEMS = categoriesO;
    }
}
