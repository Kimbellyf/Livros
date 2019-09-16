package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.livros.Teste.JsonPlaceHolderApi;
import com.example.livros.Model.Book;
import com.example.livros.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView testViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testViewResult = findViewById(R.id.testemain);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Book>> call = jsonPlaceHolderApi.getbooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){
                    testViewResult.setText(("Code:" + response.code()));
                    return;
                }
                List<Book> books = response.body();

                for (Book book: books){
                    String content = "";
                    content += "Title:" + book.getTitle() + "\n";
                    content += "Short Description:" + book.getShortDescription() + "\n\n";

                    testViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                testViewResult.setText(t.getMessage());
            }
        });
    }
}
