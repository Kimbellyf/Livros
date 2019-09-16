package com.example.livros.Model;

import java.util.List;

public class Book {
    public boolean selected;
    public String id;
    private String title;
    private String isbn;
    private String thumbnailUrl;
    private String shortDescription;
    private String longDescription ;
    private String  status ;
    private List<Author> authors;
    private List<PublishedDate> publishedDate;



    public void setPublishedDate(List<PublishedDate> publishedDate) {
        this.publishedDate = publishedDate;
    }



/*    public Book(String valueOf, String s, String makeDetails) {
    }
    //private String    ; lista de authors
    //lista categories
    //lista publishdate - q contem date
*/
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
    public List<PublishedDate> getPublishedDate() {
        return publishedDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "selected=" + selected +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
