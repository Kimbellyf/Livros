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
    private List<Author> authorsO;
    private List<Category> categoriesO;
    private List<PublishedDate> publishedDateO;
    private String pageCount;
    private List<String> authors;
    private List<String> categories;
    /*    public Book(String valueOf, String s, String makeDetails) {
    }
    //private String    ; lista de authorsO
    //lista categoriesO
    //lista publishdate - q contem date
*/


    public Book() {
       this.shortDescription ="";
       this.longDescription = "";

    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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

    public List<Author> getAuthorsO() {
        return authorsO;
    }

    public void setAuthorsO(List<Author> authorsO) {
        this.authorsO = authorsO;
    }
    public List<Category> getCategoriesO() {
        return categoriesO;
    }

    public void setCategoriesO(List<Category> categoriesO) {
        this.categoriesO = categoriesO;
    }
    public List<PublishedDate> getPublishedDateO() {
        return publishedDateO;
    }

    public void setPublishedDateO(List<PublishedDate> publishedDateO) {
        this.publishedDateO = publishedDateO;
    }
    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
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
                ", authorsO=" + authorsO +
                ", categoriesO=" + categoriesO +
                ", publishedDateO=" + publishedDateO +
                '}';
    }
}
