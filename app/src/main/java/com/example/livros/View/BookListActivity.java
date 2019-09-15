package com.example.livros.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.livros.Infra.SessaoApplication;
import com.example.livros.R;
import com.example.livros.View.Fragment.BookFilterSelected;
import com.example.livros.View.Fragment.BooksFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookListActivity extends AppCompatActivity{
    private BottomNavigationView bottomNavigationView;
    private CoordinatorLayout coordinatorLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        acoesReferentesAoBottomNavigation();
        toolbarComMenuNavAbreEFecha();
        criarFragment(savedInstanceState);
    }
    private void toolbarComMenuNavAbreEFecha(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }
    private void criarFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            BooksFragment frag = new BooksFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.containerframe, frag).commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               BooksFragment fragment = (BooksFragment) getSupportFragmentManager().findFragmentById(R.id.container);
                fragment.search(query.toLowerCase());
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
               BooksFragment fragment = (BooksFragment) getSupportFragmentManager().findFragmentById(R.id.containerframe);
                fragment.closeSearch();
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
                    BookFilterSelected.instance.setTipoListaPraMostrarSubCategoriaBottomNavCliente("casa de festa");
                    return true;
                }else if (item.getItemId()==R.id.action_your_configs) {
                    trocarFragmento("userconfigs");
                    BookFilterSelected.instance.setTipoListaPraMostrarSubCategoriaBottomNavCliente("buffet");
                    return true;
                }
                return false;
            }
        });

    }
    public void trocarFragmento(String tipo){
        Fragment frag = null;
        getSupportActionBar().setTitle(getIntent().getStringExtra(tipo));
        frag = BooksFragment.newInstance(0);
        frag.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();
        try {
            toolbar.collapseActionView();
            toolbarComMenuNavAbreEFecha();
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
    public void exibirMsgEAgirSeQuiserSairRealmente(){
        if(SessaoApplication.getInstance().getTipoDeUserLogado().equals("customer")) {
            YesOrNoDialog.show(getSupportFragmentManager(), "Deseja realmente sair da sua conta ?", new YesOrNoDialog.Callback() {
                @Override
                public void metodoSimAoDialog() {
                    SessaoApplication.getInstance().onTerminate();
                    mudarTela(TesteNavActivity.class);
                }
            });
        }else{
            mudarTela(TesteNavActivity.class);
        }
    }
    public void toFavBookList(){
        this.mudarTela(TesteNavActivity.class);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            exibirMsgEAgirSeQuiserSairRealmente();
        }
    }

}
