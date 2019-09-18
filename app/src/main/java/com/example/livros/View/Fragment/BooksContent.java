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
        //return new Book(String.valueOf(position), "Item " + position, makeDetails(position));
        return new BookItem();
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
            this.title="Book name example";
            this.longDescription="Long description";
            this.shortDescription="Short description";
            this.status="PUBLISH";
            this.isbn="00";
            this.thumbnailUrl="data:image/jpeg;base64,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";
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
