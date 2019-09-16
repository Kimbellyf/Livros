package com.example.livros.Teste;

import com.example.livros.Model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("bins/h8xi7")
    Call<List<Book>> getbooks();
}
