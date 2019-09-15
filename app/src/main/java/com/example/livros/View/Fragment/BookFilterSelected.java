package com.example.livros.View.Fragment;

import com.example.livros.Infra.SessaoApplication;
import com.example.livros.Model.Book;
import com.example.livros.View.BookListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookFilterSelected {
    public static final BookFilterSelected instance = new BookFilterSelected();
    private final Map<String, Object> values = new HashMap<>();
    private String tipoAnuncioBottomNavSoPCliente ="null";

    public String getTipoListaPraMostrarSubCategoriaBottomNavCliente(){
        if ((tipoAnuncioBottomNavSoPCliente.equals("null")|(!SessaoApplication.getInstance().getTelaAtual().equals(BookListActivity.class)))){
            //tem q definir aq como o primeiro tipo q aparece no bottom navig
            this.tipoAnuncioBottomNavSoPCliente="festa";
        }
        return tipoAnuncioBottomNavSoPCliente;
    }
    public void setTipoListaPraMostrarSubCategoriaBottomNavCliente(String tipoSubCategoriaBottomNav){
        this.tipoAnuncioBottomNavSoPCliente= tipoSubCategoriaBottomNav;
    }
    public void setAnunciosList(ArrayList<Book> anunciosList) {
        setValor("BookFilterSelected.anunciosList", anunciosList);
    }

    public ArrayList<Book> getAnunciosList() {
        return (ArrayList<Book>) values.get("BookFilterSelected.anunciosList");
    }

    public Book getBookSelected() {
        return (Book) values.get("sessao.AnuncioSelecionado");
    }

    public void setAnuncioSelecionado(Book anuncioSelecionado) {
        setValor("sessao.AnuncioSelecionado", anuncioSelecionado);
    }

    public Book getTypeBookSelected() {
        return (Book) values.get("sessao.AnuncioSelecionado");
    }

    public void setTypeBookSelected(Book anuncioSelecionado) {
        setValor("sessao.AnuncioSelecionado", anuncioSelecionado);
    }
    private void setValor(String chave, Object valor){
        values.put(chave, valor);
    }

    public void reset() {
        this.values.clear();
    }
}


