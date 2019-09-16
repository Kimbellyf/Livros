package com.example.livros.View.NewTest;

import android.net.Uri;

import com.example.livros.Infra.SessaoApplication;
import com.example.livros.Model.Book;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewBooksContent {

    public static List<Book> ITEMS = new ArrayList<>();


    private static void addItem(Book item) {
        ITEMS.add(0, item);
    }
    public static List<Book> getITEMS() {
        return ITEMS;
    }
    public static void setITEMS(List<Book> ITEMS) {
        NewBooksContent.ITEMS = ITEMS;
    }

}