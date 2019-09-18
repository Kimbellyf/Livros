package com.example.livros.View.Fragment;

import android.util.Log;

import com.example.livros.Model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookServiceNew {
    private static final String URL_BASE = "https://api.myjson.com/";

    public BookServiceNew() {
    } //CONSTRUTOR

    public void bookListAllAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Book>> call = jsonPlaceHolderApi.getbooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){
                    //testViewResult.setText(("Code:" + response.code()));
                    return;
                }
                List<Book> books = response.body();
                printBookDataLogAPI(books);
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                //testViewResult.setText(t.getMessage());
            }
        });

    }
    public void printBookDataLogAPI(List<Book> booksrec){
        for (Book book: booksrec){
            String content = "";
            content += "Title:" + book.getTitle() + "\n";
            content += "Short Description:" + book.getShortDescription() + "\n\n";
            Log.i("tostring",book.toString());
            //testViewResult.append(content);

        }

    }
}
