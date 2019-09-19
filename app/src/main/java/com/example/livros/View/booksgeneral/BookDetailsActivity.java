package com.example.livros.View.booksgeneral;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.livros.Infra.ImageUtils;
import com.example.livros.Infra.SessaoApplication;
import com.example.livros.R;
import com.example.livros.View.YesOrNoDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookDetailsActivity extends AppCompatActivity {
    private ImageView photoBook;
    private TextView titleBook, authorsNames, bookShortDescription, bookLongDescription, isbnBook, pubDate;
    private TextView booksCategoriesTags, bookeditors;
    private FloatingActionButton floatingAddBookListFav;
    private String validar = "";
    boolean isValido = false;
    private ProgressDialog mprogressDialog;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        setUpToolbar();
        encontrandoItensViews();
    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTelaDetalhes);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitleTextColor( getResources().getColor(R.color.colorWhite));

    }

    public void encontrandoItensViews() {
        this.photoBook = findViewById(R.id.bookImageViewDetails);
        this.titleBook = findViewById(R.id.textViewTitleBook);
        this.authorsNames = findViewById(R.id.textViewAuthorsName);
        this.floatingAddBookListFav = findViewById(R.id.floatingButtonAddListFav);
        this.bookShortDescription = findViewById(R.id.textViewShortDescription);
        this.bookLongDescription = findViewById(R.id.textViewLongDescription);
        this.bookeditors = findViewById(R.id.textViewEditors);
        this.pubDate = findViewById(R.id.textViewPublishDates);
        this.isbnBook = findViewById(R.id.textViewISBNBook);
        this.booksCategoriesTags = findViewById(R.id.textViewTagsBookCategories);
        acoesBotoes();
        //tornarInvisivelAlgunsPFornecedor();
        setarInfoView();
    }

    public void acoesBotoes() {
        floatingAddBookListFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemBookListFav();
            }
        });
    }

    public void setarInfoView() {
        NewBookItem bookSelected = BookFilterSelected.instance.getBookSelected();
        titleBook.setText(bookSelected.getTitle());
        String urlImg =  bookSelected.getThumbnailUrl();

        // Foto do Ad
        ImageUtils.setImage(this,urlImg, photoBook);
        bookShortDescription.setText(bookSelected.getShortDescription());
        bookLongDescription.setText(bookSelected.getLongDescription());
        isbnBook.setText(bookSelected.getIsbn());

       /* authorsNames.setText(("Autor(es) :" +bookSelected.getOwner().getSocialname()));
        SimpleDateFormat sdfDiaMesAno = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHoraMin = new SimpleDateFormat("HH:mm");
        String diaMesAnoCreated = sdfDiaMesAno.format(bookSelected.getCreatedAt());
        String horaMinCreated = sdfHoraMin.format(bookSelected.getCreatedAt());
        pubDate.setText(("Data de publicação: "+diaMesAnoCreated+" às "+horaMinCreated));
        bookLongDescription.setText(("Descrição: " + bookSelected.getLongDescription()));
        */

        //isbnBook.setText(("Telefone :" + bookSelected.getPhone()));
        //booksCategoriesTags.setText(("Tags : " +bookSelected.getTags().toString())); ---ERRO NA CONVERSAO METODO TA ERRADO

    }

    public void addItemBookListFav() {
        YesOrNoDialog.show(getSupportFragmentManager(), "Deseja adicionar esse livro a sua lista de favoritos ?", new YesOrNoDialog.Callback() {
            @Override
            public void metodoSimAoDialog() {
                    mprogressDialog = new ProgressDialog(BookDetailsActivity.this);
                    mprogressDialog.setMessage("Adicionando esse livro a sua lista de favoritos");
                    mprogressDialog.show();
                    try{
                        addBookToOfficialFavList();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    exibirMsgSeValidouadd();
                    mprogressDialog.dismiss();
            }
        });

    }
    public void msgToast(String msgToast){
        Toast.makeText(this, msgToast, Toast.LENGTH_SHORT).show();
        finish();
    }


    public void mudarTela(Class tela) {
        Intent intent = new Intent(this, tela);
        startActivity(intent);
        finish();
    }
    private void addBookToOfficialFavList() throws InterruptedException{
        String token=  "," + "\"token\"" + ":" + "\""+SessaoApplication.getInstance().getTokenUser() +"\"" +"}";
        //String jsonAMao ="{" + "\"ad\":"+"\""+BookFilterSelected.instance.getBookSelected().get_id()+"\""+ token;
        //callServer("POST",jsonAMao);
    }
    
    public void exibirMsgSeValidouadd(){
        Toast.makeText(getApplicationContext(), validar, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
