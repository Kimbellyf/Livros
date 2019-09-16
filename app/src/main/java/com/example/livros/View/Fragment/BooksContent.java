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
    public static final List<Book> ITEMS = new ArrayList<Book>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Book> ITEM_MAP = new HashMap<String, Book>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createBookItem(i));
        }
    }

    private static void addItem(Book item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Book createBookItem(int position) {
        //return new Book(String.valueOf(position), "Item " + position, makeDetails(position));
        return new Book();
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
    public static class BookItem {
        public  String id;
        public  String content;
        public  String details;
        public boolean selected;

        private String title;
        private String isbn;
        private String thumbnailUrl;
        private String shortDescription;
        private String longDescription ;
        private String  status ;

        public BookItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        public BookItem() {

        }


        public String getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getDetails() {
            return details;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
