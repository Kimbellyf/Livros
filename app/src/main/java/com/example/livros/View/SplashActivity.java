package com.example.livros.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.livros.R;
import com.example.livros.View.booksgeneral.BookListActivity;

public class SplashActivity extends AppCompatActivity implements Runnable {

    /**
     * @param savedInstanceState Objeto da classe Bundle que contem o estado anterior da activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 2000);
    }

    /**
     * @see BookListActivity
     */

    public void run() {
        startActivity(new Intent(this, BookListActivity.class));
        finish();
    }
}
