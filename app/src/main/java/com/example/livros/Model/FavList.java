package com.example.livros.Model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavList {
    /**
     * An array of sample (dummy) items.
     */
    public static final List<BookItem> ITEMS = new ArrayList<BookItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, BookItem> ITEM_MAP = new HashMap<String, BookItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createBookItem(i));
        }
    }

    private static void addItem(BookItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static BookItem createBookItem(int position) {
        return new BookItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
     /* A dummy item representing a piece of content.
            */
    public static class BookItem {
        public final String id;
        public final String content;
        public final String details;

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
