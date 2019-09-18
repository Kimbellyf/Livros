package com.example.livros.View.NewTest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.livros.View.Fragment.JsonPlaceHolderApi;
import com.example.livros.Infra.SessaoApplication;
import com.example.livros.Model.Book;
import com.example.livros.R;
import com.example.livros.View.Fragment.BookDetailsActivity;
import com.example.livros.View.Fragment.BookFilterSelected;
import com.example.livros.View.Fragment.BooksContent;
import com.example.livros.View.Fragment.NewBookItem;
import com.example.livros.View.TesteNavActivity;
import com.example.livros.View.YesOrNoDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewListBooksActivity extends AppCompatActivity implements ItemOlderFragment.OnListFragmentInteractionListener{
        private BottomNavigationView bottomNavigationView;
        private CoordinatorLayout coordinatorLayout;
        private ViewPager viewPager;
        private Toolbar toolbar;
        List<Book> booksh;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_book_list);
            buscandoDadosComRetrofit();
            acoesReferentesAoBottomNavigation();
            configToolbar();
            criarFragment(savedInstanceState);

        }
        private void configToolbar(){
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        private void criarFragment(Bundle savedInstanceState) {
            if (savedInstanceState == null) {
                ItemOlderFragment frag = new ItemOlderFragment();
                frag.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
            }
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_main, menu);
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                   ItemOlderFragment fragment = (ItemOlderFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                    //fragment.search(query.toLowerCase());
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
            searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {

                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    ItemOlderFragment fragment = (ItemOlderFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                    //fragment.closeSearch();
                }
            });

            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_filter_by_category) {
                return true;
            } else if (id == R.id.action_search) {
            } else if (id == R.id.action_filter_by_author) {
            }
            return super.onOptionsItemSelected(item);
        }
        protected Context getContext() {
            return this;
        }

        public void acoesReferentesAoBottomNavigation(){
            bottomNavigationView =  (BottomNavigationView) findViewById(R.id.tab_bar_opcoes_embaixo);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()  {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.action_all_books){
                        //Por algum motivo o oncreate n pega o bundle(fica dando nul), mas o newinstance ele reconhece sim
                        trocarFragmento("allbooks");
                        //tive q fazer:
                        //tbm como n tem pacote no momento, pq é recomendação , mudei p retornar so festa de vez de pacote
                        BookFilterSelected.instance.setTipoListaPraMostrarSubCategoriaBottomNavCliente("festa");
                        return true;

                    }else if (item.getItemId()==R.id.action_fav_books){
                        trocarFragmento("favbooklist");
                        BookFilterSelected.instance.setAnunciosList((ArrayList<Book>) SessaoApplication.getBooklistatmoment());
                        return true;
                    }else if (item.getItemId()==R.id.action_your_configs) {
                        trocarFragmento("userconfigs");
                        //BookFilterSelected.instance.setAnunciosList(SessaoApplication.);
                        return true;
                    }
                    return false;
                }
            });

        }
        public void trocarFragmento(String tipo){
            Fragment frag = null;
            getSupportActionBar().setTitle(getIntent().getStringExtra(tipo));
            frag = ItemOlderFragment.newInstance(0);
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
            try {
                toolbar.collapseActionView();
                configToolbar();
            }catch (Error error){
                error.printStackTrace();
            }
        }

        private void mudarTela(Class tela){
            Intent intent=new Intent(this, tela);
            startActivity(intent);
            finish();
        }
        public void irParaTelaConfiguracoesCliente(){
            this.mudarTela(TesteNavActivity.class);
        }
        public void exibirMsgEAgirSeQuiserSairRealmente() {
            YesOrNoDialog.show(getSupportFragmentManager(), "Deseja realmente sair da sua conta ?", new YesOrNoDialog.Callback() {
                @Override
                public void metodoSimAoDialog() {
                    SessaoApplication.getInstance().onTerminate();
                    mudarTela(TesteNavActivity.class);
                }
            });
        }
        public void buscandoDadosComRetrofit(){
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
                        //testViewResult.setText(("Code:" + response.code()));
                        return;
                    }
                    List<Book> books = response.body();
                    booksh = books;
                    SessaoApplication.setBooklistatmoment(books);
                    Log.d("testesessao",SessaoApplication.getBooklistatmoment().toString());
                    Log.i("exr",SessaoApplication.getBooklistatmoment().toString());

                    for (Book book: books){
                        String content = "";
                        content += "Title:" + book.getTitle() + "\n";
                        content += "Short Description:" + book.getShortDescription() + "\n\n";

                        //testViewResult.append(content);

                    }
                }

                @Override
                public void onFailure(Call<List<Book>> call, Throwable t) {
                    //testViewResult.setText(t.getMessage());
                }

            });


        }

        public void toFavBookList(){
            this.mudarTela(TesteNavActivity.class);
        }

        @Override
        public void onBackPressed() {
            exibirMsgEAgirSeQuiserSairRealmente();

        }

        @Override
        public void onListFragmentInteraction(NewBookItem item) {
            BookFilterSelected.instance.setBookSelected(item);
            mudarTela(BookDetailsActivity.class);

        }
}
