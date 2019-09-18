package com.example.livros.Infra;

import android.content.Context;
import android.util.Log;

import com.example.livros.Infra.ConnectAPI;
import com.example.livros.Infra.IOUtils;
import com.example.livros.Infra.SessaoApplication;
import com.example.livros.Infra.utils.bibliotecalivroandroid.utils.FileUtils;
import com.example.livros.Model.Book;
import com.example.livros.R;
import com.example.livros.View.Fragment.BookDetailsActivity;
import com.example.livros.View.Fragment.BookListActivity;
import com.example.livros.View.Fragment.BooksContent;
import com.example.livros.View.itemsBottomNav.favorites.FavListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static final String TAG = "AnuncioEmComumService";
    private static final boolean LOG_ON = false;
    //private static final String URL_BASE = "https://api.myjson.com/bins/h8xi7";
    private static final String URL_BASE = "https://makepartyserver.herokuapp.com/ads";
    //GET
    private static final String URL_LISTAR_ANUNCIOS = URL_BASE + "ads";
    private static final String URL_LISTAR_ANUNCIOS_PELO_TIPO = URL_BASE;

    private Gson gson = new Gson();
    private String respostaServidor;

    public BookService() {
    } //CONSTRUTOR

    public static String conectarServidorGet(String url) throws IOException {
        // Request HTTP GET
        ConnectAPI http = new ConnectAPI();
        http.LOG_ON = true;
        String json = http.doGet(url);
        return json;
    }


    private static List<BooksContent.BookItem> parserJSON(Context context, String json) throws IOException {
        //Informa ao GSON que vamos converter uma lista de livros
        Type listType = new TypeToken<ArrayList<Book>>() {
        }.getType();
        //Faz o parser em apenas uma linha e cria a list
        List<BooksContent.BookItem> ads = new Gson().fromJson(json, listType);

        return ads;
    }


    private static String getTipo(int tipo) {
        if (tipo == R.string.text_all_books) {
            return "pacotes";
        } else if (tipo == R.string.text_fav_list_book) {
            return "casas";
        } else if (tipo == R.string.text_your_config) {
            return "buffet";
        }
        return "";
    }

    public static List getTodosAnuncios(Context context) throws IOException {
        String url = URL_LISTAR_ANUNCIOS;
        String json = conectarServidorGet(url);
        List listaAds = parserJSON(context, json);
        return listaAds;
    }
/*
    public static List<Book> getAnunciosByTipoTwo(Context context, String tipo) throws IOException {
        String url = URL_LISTAR_ANUNCIOS_PELO_TIPO.replace(":type", tipo);
        String json = conectarServidorGet(url);
        List listaAnunciosPorTipo = parserJSON(context, json);
        return listaAnunciosPorTipo;
    }


    public static List<Book> getAnunciosByTipoThree(String tipo) throws IOException {
        String url = URL_LISTAR_ANUNCIOS_PELO_TIPO.replace(":type", tipo);
        String json = conectarServidorGet(url);
        Log.d("um json ai", json);
       // Data data = new Gson().fromJson(json, Data.class);
        //return data;
    }
    */

    public void verifTipoReqLista(String tipo) {
        //if verifTipoReqLista(SessaoApplication.getInstance().getTipoDeUserLogado().equals("Fornecedor"));
    }

    public static List<Book> getAnunciosByPriceFilter(String intervaloInicio, String intervaloFim) throws IOException {
        List ads = new ArrayList<Book>();
        //FAZER AQ EINNNNNNNNNNNNNNNNNNNNNNNNN
        //FILTRO PREÇOOOOOOOOOO

        return ads;
    }
    public static List<BooksContent.BookItem> searchAllAdsByNome(String nome) throws IOException {
        String url = URL_BASE + "/nome/" + nome; // << essa url ta errada, eu n sei qual url da p pesquisar pelo nome la na API
        ConnectAPI http = new ConnectAPI();
        String json = http.doGet(url);
        List litaAnunciosPorNome = parserJSONListaAnunciosComFor(json);
        return litaAnunciosPorNome;
    }
    public static void addItensLista(List<BooksContent.BookItem> selectedAds) throws IOException, JSONException {
        if ((SessaoApplication.getInstance().getTipoDeUserLogado().equals("customer") &&
                ((SessaoApplication.getInstance().getTelaAtual().equals(BookListActivity.class)
                        | SessaoApplication.getInstance().getTelaAtual().equals(BookDetailsActivity.class))))) {

            //Boolean a = ClienteService.addAWishList(selectedAds);
            Boolean a = true;
            //aqui embaixo esse metodo acho q n é a melhor forma de implementar, talvez o wishlist? chamando ele?
            //setar ainda qual tela ta
        }
    }
    public static List<BooksContent.BookItem> getAnunciosByTipo(String tipo) throws IOException {
        List ads = new ArrayList<BooksContent.BookItem>();
        String url = URL_BASE;
        String json = conectarServidorGet(url);
        Log.d("um json ai", json);
        ads = parserJSONListaAnunciosComFor(json);


        return ads;
    }

    public static List<BooksContent.BookItem> parserJSONListaAnunciosComFor(String json) throws IOException {
        List<BooksContent.BookItem> books = new ArrayList<BooksContent.BookItem>();
        try {
            JSONObject objetoJson = new JSONObject(json);
            JSONArray jsonBooks = null;
            if(SessaoApplication.getInstance().getTelaAtual().equals(FavListActivity.class)){
                jsonBooks=objetoJson.getJSONArray("book");
            }else{
                jsonBooks = objetoJson.getJSONArray("ads");
            }

            //Lê o array de books do Json
            //JSONArray jsonBooks = new JSONArray(json);
            for (int i = 0; i < jsonBooks.length(); i++) {
                JSONObject jsonBook = jsonBooks.getJSONObject(i);
                BooksContent.BookItem c = new BooksContent.BookItem();
                //Lê as info de cada anuncio
                c.setTitle(jsonBook.optString("title"));
               // c.setThumbnailUrl(jsonBook.optString("thumbnailUrl"));
                /*c.setDescription(jsonBook.optString("description"));
                c.setPrice(jsonBook.optDouble("price"));
                //TAGS N TA FUNCIONANDO DIREITO
                /*JSONArray tagsArray = jsonBook.getJSONArray("tags");
                List<String> listTags = new ArrayList<String>();
                for (int e=0;i<tagsArray.length();i++){
                    listTags.add(tagsArray.getString(e));
                }
                Log.d("tagsss",listTags.toString());
                c.setTags((ArrayList) listTags);

                /*
                JSONArray fotosArrayJson = jsonBook.getJSONArray("photos");
                List<String> listFotos = new ArrayList<String>();
                for (int e=0;i<fotosArrayJson.length();i++){
                    listFotos.add(fotosArrayJson.getString(e));
                }
                Log.d("fotoooosArray",listFotos.toString());
                c.setPhotos((ArrayList) listFotos);
                */
                /*
                c.set_id(jsonBook.optString("_id"));
                c.setTitle(jsonBook.optString("title"));
                c.setType(jsonBook.optString("type"));
                c.setPhone(jsonBook.optString("phone"));

                Owner ownerAqui=new Owner();
                JSONObject objetoOwner = jsonBook.getJSONObject("owner");
                ownerAqui.setSocialname(objetoOwner.getString("socialname"));
                ownerAqui.setCnpj(objetoOwner.getString("cnpj"));
                ownerAqui.set_id(objetoOwner.getString("_id"));

                User userOwner = new User();
                JSONObject objetoUserOwner =objetoOwner.getJSONObject("user");
                userOwner.setEmail(objetoUserOwner.getString("email"));
                userOwner.set_id(objetoUserOwner.getString("_id"));
                ownerAqui.setUser(userOwner);
                c.setOwner(ownerAqui);
                Log.d("oi",c.toString());

                //ta errado aq embaixo
                try {
                    String dateStr = jsonBook.getString("createdAt");
                    Date sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dateStr);
//                    long createdAtDate = sdf.parse(dateStr);
//                    Log.d("dataveae", String.valueOf(createdAtDate));
//                    Date date = new Date();
//                    date.setTime(createdAtDate);
                    c.setCreatedAt(sdf);
                    Log.d("dataveae", c.getCreatedAt().toString());
                }catch (ParseException e){
                    Log.d("erro da data:", e.getMessage());
                }


                //c.setUpdatedAt();
//                Long createdAt = jsonBook.optLong("createdAt");
//                Date createdAtConv = new Date(createdAt);
//                c.setCreatedAt(createdAtConv);

                Address addressAnuncio = new Address();
                JSONObject objetoEndAnuncio = jsonBook.getJSONObject("address");
                //LEMBRANDO Q ESSES OBJ N PODEM FICAR NULL EXPLIC , SE N, DA ERROO NA CONV
                addressAnuncio.setCity(objetoEndAnuncio.getString("city"));
                addressAnuncio.setNeighborhood(objetoEndAnuncio.getString("neighborhood"));
                addressAnuncio.setNumber(objetoEndAnuncio.getString("number"));
                addressAnuncio.setStreet(objetoEndAnuncio.getString("street"));
                addressAnuncio.setZipcode(objetoEndAnuncio.getString("zipcode"));
                c.setAddress(addressAnuncio);

                 */

               /* if (LOG_ON) {
                    Log.d(TAG, "Book" + c.getDescription() + ">");

                }

                */
                books.add(c);
            }
            if (LOG_ON) {
                Log.d(TAG, books.size() + "encontrados");
            }
        } catch (JSONException e) {
            throw new IOException(e.getMessage(), e);
            //} catch (ParseException e) {
            //  e.printStackTrace();
        }
        return books;

    }


    // Faz a requisição HTTP, cria a lista de anuncios e salva o JSON em arquivo
    public static List<BooksContent.BookItem> getAnunciosFromWebService(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String url = URL_BASE;
        Log.d(TAG, "URL: " + url);
        ConnectAPI http = new ConnectAPI();
        String json = http.doGet(url);
        List<BooksContent.BookItem> ads = parserJSON(context, json);
        salvaArquivoNaMemoriaInterna(context, url, json);
        // Depois de buscar salva os ads
        //salvarAnuncios(context, tipo, ads);
        return ads;
    }

    private static void salvaArquivoNaMemoriaInterna(Context context, String url, String json) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        File f = FileUtils.getFile(context, fileName);
        IOUtils.writeString(f, json);
        Log.d(TAG, "Arquivo salvo: " + f);

    }

    public static List<Book> getAnunciosFromRaw(Context context, int tipo) throws IOException {
        //String json = readFile(context, tipo);
        //List<Ad> anuncios = parserJSON(context, json);

        //return anuncios;
        return null;
    }

    // Abre o arquivo salvo, se existir, e cria a lista de anuncios
    public static List<BooksContent.BookItem> getAnunciosFromArquivo(Context context, int tipo) throws IOException {
        String tipoString = getTipo(tipo);
        String fileName = String.format("anuncios_%s.json", tipoString);
        Log.d(TAG, "Abrindo arquivo: " + fileName);
        // Lê o arquivo da memória interna
        String json = FileUtils.readFile(context, fileName, "UTF-8");
        if (json == null) {
            Log.d(TAG, "Arquivo " + fileName + " não encontrado.");
            return null;
        }
        List<BooksContent.BookItem> ads = parserJSON(context, json);
        Log.d(TAG, "Retornando ads do arquivo " + fileName + ".");
        return ads;
    }

    //converte um objeto para json
    public String criarJson(Object objeto) {
        return gson.toJson(objeto);
    }

    //converte um json para objeto
    public Book criarObjeto(String json) {
        return gson.fromJson(json, Book.class);

    }

}
