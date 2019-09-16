package com.example.livros.Teste.dummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class NewTwoBooksContent {
    public static final List<BookItem> ITEMS = new ArrayList<>();


    private static void addItem(BookItem item) {
        ITEMS.add(0, item);
    }
    public static List<BookItem> getITEMS() {
        return ITEMS;
    }
}
