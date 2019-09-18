package com.example.livros.View.Fragment;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.livros.Infra.ImageUtils;
import com.example.livros.Infra.SessaoApplication;
import com.example.livros.R;
import com.example.livros.View.Fragment.BooksFragment.OnListFragmentInteractionListener;
import com.example.livros.View.Fragment.BooksContent.BookItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BookItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class BooksRecyclerViewAdapter extends RecyclerView.Adapter<BooksRecyclerViewAdapter.BooksViewHolder> {
    private final List<BookItem> mValues;
   // private final List<BookItem> mValues;
    private final Context context;
    private final OnListFragmentInteractionListener mListener;

    public BooksRecyclerViewAdapter(Context context, List<BookItem> items, OnListFragmentInteractionListener listener) {
        //this.books = books;
        this.context = context;
        mValues =items;
        //mValues = SessaoApplication.getBooklistatmoment();
        mListener = listener;
    }
    //abaixo metodos que devem ser implementados para ter diferentes respostas dependendo do clique

    /* public interface OnListFragmentInteractionListener {
        void onClickBooks(BooksViewHolder holder, int indexAnuncio);
        void onLongClickBooks(BooksViewHolder holder, int indexAnuncio);
    }
    */


    @Override
    public BooksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_adapter_books, parent, false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BooksViewHolder holder, final int position) {
        // Livro da linha
        // Ad da linha
        BooksContent.BookItem b = mValues.get(position);
        // Livro da linha
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
        // Atualiza os valores nas views
        holder.mTitle.setText(mValues.get(position).getTitle());
        //holder.mAuthorsNames.setText(mValues.get(position).AuthorsName);
        holder.mShortDescription.setText(mValues.get(position).getShortDescription());

        String urlImg =  mValues.get(position).getThumbnailUrl();

        // Foto do Ad
        ImageUtils.setImage(context,urlImg, holder.mImg);


        // Click
        if (null != mListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // A variável position é final
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);

                }
            });
            // Click longo
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //mListener.onLongClickCarro(holder.mItem);
                    return true;
                }
            });
        }
        //Pinta o fundo de azul se a linha estiver selecionada
        int corFundo = context.getResources().getColor(b.selected ? R.color.colorPrimary : R.color.colorWhite);
        holder.mCardView.setCardBackgroundColor(corFundo);
        // A cor do texto é branca ou azul, depende da cor do fundo.
        int corFonte = context.getResources().getColor(b.selected ? R.color.colorWhite : R.color.colorPrimary);
        //holder.tNome.setTextColor(corFonte);
    }

    @Override
    public int getItemCount() {
        return mValues != null ? this.mValues.size() : 0;
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public BookItem mItem;
        CardView mCardView;
        public ImageView mImg;
        public TextView mTitle;
        public TextView mAuthorsNames;
        TextView mShortDescription;
        private ProgressBar progress;

        public BooksViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            //// Cria as views para salvar no ViewHolder
            mCardView = view.findViewById(R.id.card_view);
            mImg = (ImageView) view.findViewById(R.id.img);
            mTitle = (TextView) view.findViewById(R.id.textViewTitleBook);
            mAuthorsNames = (TextView) view.findViewById(R.id.textViewAuthorName);
            mShortDescription = (TextView) view.findViewById(R.id.textViewShortDescription);
            progress = (ProgressBar) view.findViewById(R.id.progress);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
