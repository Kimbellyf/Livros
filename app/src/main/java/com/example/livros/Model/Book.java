package com.example.livros.Model;

import android.util.Log;

import java.util.ArrayList;
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
    private List<Author> allAuthorsOfBook = new ArrayList<>();
    private List<Category> categoriesO = new ArrayList<Category>();
    private List<PublishedDate> publishedDateO;
    private String pageCount;
    //private List<String> authors;
    //private List<Author> authors;
    private List<String> authors = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    /*    public Book(String valueOf, String s, String makeDetails) {
    }
    //private String    ; lista de allAuthorsOfBook
    //lista categoriesO
    //lista publishdate - q contem date
    */
    private List<Author> authorsO = new ArrayList<Author>();


    public Book() {
       this.shortDescription ="";
       this.longDescription = "";
       this.title="Book name example";
       this.longDescription="Long description";
       this.shortDescription="Short description";
       this.status="PUBLISH";
       this.isbn="00";
       this.thumbnailUrl="";

    }


    public Book(String id, String title, String shortDescription) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<String> getAuthors() {
        return authors;

    }
    public void newsobjauthors(){
        //add author a outra lista
        if (authors.isEmpty()){

        }else{
            for (String string: authors){
                Log.d("authorstring",string);
                Author authorhere = new Author();
                authorhere.setName(string);
                authorsO.add(authorhere);
            }

        }
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }
    public void newsobjCategories(){
        //add category a outra lista
        if (categories.isEmpty()){

        }else{
            for (String string: categories){
                Log.d("categories",string);
                Category categoryhere = new Category();
                categoryhere.setName(string);
                categoriesO.add(categoryhere);
            }
        }
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
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
                ", authorshere='" + authors + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", status='" + status + '\'' +
                ", authorsO=" + authorsO +
                ", categoriesO=" + categoriesO +
                ", publishedDateO=" + publishedDateO +
                '}';
    }
}
