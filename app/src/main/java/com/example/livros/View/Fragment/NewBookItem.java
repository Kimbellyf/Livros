package com.example.livros.View.Fragment;

public class NewBookItem {
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

    public NewBookItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public NewBookItem() {
        this.title="Book name example";
        this.longDescription="Long description";
        this.shortDescription="Short description";
        this.status="PUBLISH";
        this.isbn="00";
        this.thumbnailUrl="";
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
