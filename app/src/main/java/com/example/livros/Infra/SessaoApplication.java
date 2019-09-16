package com.example.livros.Infra;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.livros.Model.Book;
import com.squareup.otto.Bus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessaoApplication extends Application {

    private static final String TAG = " SessaoApplication";
    public static SessaoApplication instance = null;
    private final Map<String, Object> values = new HashMap<>();
    private Bus bus = new Bus();
    private String tipoDeUserLogado = "null";
    private String token = "null";
    private String _id = "null";
    //private User user;
    private Date horaRecebidoToken = null;
    //private User UserIsLogado =null;
    private Class telaAtual =null;
    private Class telaAnterior =null;
    private static Context mContext;
    private static List<Book> adRecomendacao;
    private static List<Book> booklistatmoment;
    private static List<Book> FavBookList;

    public static List<Book> getFavBookList() {
        return FavBookList;
    }

    public static void setFavBookList(List<Book> favBookList) {
        FavBookList = favBookList;
    }


    public static List<Book> getBooklistatmoment() {
        return booklistatmoment;
    }

    public static void setBooklistatmoment(List<Book> booklistatmoment) {
        SessaoApplication.booklistatmoment = booklistatmoment;
    }

    public static SessaoApplication getInstance() {
        return instance; // Singleton
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, " SessaoApplication.onCreate()");
        // Salva a instância para termos acesso como Singleton
        instance = this;
        mContext = this;
    }

    private void setValor(String chave, Object valor) {
        values.put(chave, valor);
    }

    public void setResposta(String resposta) {
        setValor("sessao.resposta", resposta);
    }

    public String getResposta(){
        return (String) values.get("sessao.resposta");
    }

    public String getTipoDeUserLogado(){
        return tipoDeUserLogado;
    }
    public void setTipoDeUserLogado(String tipoDeUserLogado) {
        this.tipoDeUserLogado = tipoDeUserLogado;
    }


    public String getIdUser() {
        return _id;
    }

    public void setIdUser(String _id) {
        this._id = _id;
    }

   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
*/
    public String getTokenUser() {
        return token;
    }

    public void setTokenUser(String token) {
        this.token = token;
    }

    public void setHoraRecebidoToken(Date horaRecebidoToken){
        this.horaRecebidoToken = horaRecebidoToken;
    }

    public void setTelaAtual(Class telaAtual) {
        setTelaAnterior(this.telaAtual);
        this.telaAtual =telaAtual;
    }
    public Class getTelaAtual() {
        return telaAtual;
    }
    public void setTelaAnterior(Class telaAnterior){
        this.telaAnterior = telaAnterior;
    }
    public Class getTelaAnterior(){
        return telaAnterior;
    }
    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onTerminate() {
        reset();
        super.onTerminate();
        SessaoApplication.getInstance().setTipoDeUserLogado("null");
        SessaoApplication.getInstance().setIdUser("null");
        SessaoApplication.getInstance().setHoraRecebidoToken(null);
        SessaoApplication.getInstance().setTelaAnterior(null);

        Log.d(TAG, " SessaoApplication.onTerminate()");
    }

    public Bus getBus() {
        return bus;
    }

    public void reset() {
        this.values.clear();
    }

    public static List<Book> getAdRecomendacao() {
        return adRecomendacao;
    }

    public static void setAdRecomendacao(List<Book> adRecomendacao) {
        SessaoApplication.adRecomendacao = adRecomendacao;
    }
}
