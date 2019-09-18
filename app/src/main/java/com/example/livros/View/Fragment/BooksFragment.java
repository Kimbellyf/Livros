package com.example.livros.View.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.livros.Infra.BookService;
import com.example.livros.Infra.IOUtils;
import com.example.livros.Infra.SDCardUtils;
import com.example.livros.Infra.SessaoApplication;
import com.example.livros.Infra.utils.bibliotecalivroandroid.task.TaskListener;
import com.example.livros.R;
import com.example.livros.View.YesOrNoDialog;
import com.example.livros.View.itemsBottomNav.favorites.FavListActivity;

import java.io.File;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BooksFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    protected RecyclerView recyclerView;
    private String tipo;
    //private List<Book> books;
    private List<BooksContent.BookItem> books;
    private SwipeRefreshLayout swipeLayout;
    private ActionMode actionMode;
    private Intent shareIntent;
    private List<BooksContent.BookItem> tempBooks;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BooksFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BooksFragment newInstance(int columnCount) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        this.tipo = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books_list, container, false);
/*
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new BooksRecyclerViewAdapter(getContext(), BooksContent.ITEMS, mListener));
        } */
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
// Swipe to Refresh
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        //swipeLayout.setOnRefreshListener(OnRefreshListener());
        swipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);


        //swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        recyclerView.setAdapter(new BooksRecyclerViewAdapter(getContext(), BooksContent.ITEMS, mListener));
        return view;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskAnuncios(false);
    }
    private void taskAnuncios(boolean pullToRefresh) {
        // Busca os carros: Dispara a Task
        //startTask("books", new GetAnunciosTask(pullToRefresh), pullToRefresh ? R.id.swipeToRefresh : R.id.progress);
    }


    // Task para buscar os ads
    private class GetAnunciosTask implements TaskListener<List> {
        private boolean refresh;

        public GetAnunciosTask(boolean refresh) {
            this.refresh = refresh;
        }

        @Override
        public List execute() throws Exception {
            Log.d("Olhaa quem logou", SessaoApplication.getInstance().getTipoDeUserLogado());
            //Log.d("tiporetornado",tipo);
            return BookService.getAnunciosByTipo(BookFilterSelected.instance.getTipoListaPraMostrarSubCategoriaBottomNavCliente());
            // Busca os anuncios em background (Thread)
        }

        @Override
        public void updateView(List books) {
            if (books != null) {
                // Salva a lista de anuncios no atributo da classe
                BooksFragment.this.books = BooksFragment.this.books;
                // Atualiza a view na UI Thread
                recyclerView.setAdapter(new BooksRecyclerViewAdapter( getContext(), BooksContent.ITEMS, mListener));
                //recyclerView.setAdapter(new BooksRecyclerViewAdapter(getContext(), books, onClickBook()));
            }
        }

        @Override
        public void onError(Exception e) {
            // Qualquer exceção lançada no método execute vai cair aqui.
            if (e instanceof SocketTimeoutException) {
                alert(getString(R.string.msg_erro_io_timeout));
            } else {
                alert(getString(R.string.msg_error_io));
            }
        }

        @Override
        public void onCancelled(String s) {
        }
    }
    // Atualiza o título da action bar (CAB)
    private void updateActionModeTitle() {
        if (actionMode != null) {
            actionMode.setTitle("Selecione os anúncios.");
            actionMode.setSubtitle(null);
            List<BooksContent.BookItem> selectedAds = getSelectedAnuncios();
            if (selectedAds.size() == 0) {
                actionMode.finish();
            } else if (selectedAds.size() == 1) {
                actionMode.setSubtitle("1 anuncio selecionado");
            } else if (selectedAds.size() > 1) {
                actionMode.setSubtitle(selectedAds.size() + " anúncios selecionados");
            }
            updateShareIntent(selectedAds);
        }
    }

    // Atualiza a share intent com os ads selecionados
    private void updateShareIntent(List<BooksContent.BookItem> selectedAds) {
        if (shareIntent != null) {
            // Texto com os anúncios
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Anúncios: " + selectedAds);
        }
    }

    // Retorna a lista de ads selecionados
    private List<BooksContent.BookItem> getSelectedAnuncios() {
        List<BooksContent.BookItem> list = new ArrayList<BooksContent.BookItem>();
        for (BooksContent.BookItem c : books) {
            if (c.selected) {
                list.add(c);
            }
        }
        return list;
    }

    private ActionMode.Callback getActionModeCallback() {
        return new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Infla o menu específico da action bar de contexto (CAB)
                MenuInflater inflater = getActivity().getMenuInflater();
                inflater.inflate(R.menu.menu_selecao, menu);
                telaAtualIconesVisivelOuNaoMenuSelecao(menu);
                MenuItem shareItem = menu.findItem(R.id.action_share);
               /* ShareActionProvider share = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                shareIntent.setType("text/plain");
                share.setShareIntent(shareIntent);*/

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                final List<BooksContent.BookItem> selectedAds = getSelectedAnuncios();
                if (item.getItemId() == R.id.action_add_lista_desejo_finalm) {
                    YesOrNoDialog.show(getFragmentManager(), "Deseja adicionar esses anúncios que foram selecionados a sua lista de desejos?", new YesOrNoDialog.Callback() {
                        @Override
                        public void metodoSimAoDialog() {
                            if (SessaoApplication.getInstance().getTipoDeUserLogado().equals("customer")) {
                                //add lista fav
                            }else{
                                Intent intent = new Intent(getContext(), FavListActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                } else if (item.getItemId() == R.id.action_delete_item_lista_finalm) {
                    YesOrNoDialog.show(getFragmentManager(), "Deseja mesmo excluir esses anúncios da sua lista?", new YesOrNoDialog.Callback() {
                        @Override
                        public void metodoSimAoDialog() {
                            Log.i("opaaa a lista aq",selectedAds.toString());
                            //startTask("ads", new PostOuDeleteTask(selectedAds,"delete"));
                        }
                    });
                } else if ((item.getItemId() == R.id.action_share)) {
                    // Dispara a tarefa para fazer download das fotos
                    //startTask("compartilhar", new CompartilharTask(selectedAds));

                } // Encerra o action mode
                mode.finish();
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Limpa o estado
                actionMode = null;
                // Configura todos os anuncios para não selecionados
                for (BooksContent.BookItem c : books) {
                    c.selected = false;
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        };
    }
    private void telaAtualIconesVisivelOuNaoMenuSelecao(Menu menuSelecao) {
        if (SessaoApplication.getInstance().getTelaAtual().equals(BookListActivity.class)) {
            menuSelecao.findItem(R.id.action_delete_item_lista_finalm).setVisible(false);
            menuSelecao.findItem(R.id.action_add_lista_desejo_finalm).setVisible(true);
        } else {
            menuSelecao.findItem(R.id.action_delete_item_lista_finalm).setVisible(true);
            menuSelecao.findItem(R.id.action_add_lista_desejo_finalm).setVisible(false);
        }
    }

    // Task para fazer o download
    // Faça import da classe android.net.Uri;
    private class CompartilharTask implements TaskListener {
        private final List<BooksContent.BookItem> selectedAds;
        // Lista de arquivos para compartilhar
        ArrayList<Uri> imageUris = new ArrayList<Uri>();

        public CompartilharTask(List<BooksContent.BookItem> selectedAds) {
            this.selectedAds = selectedAds;
        }

        @Override
        public Object execute() throws Exception {
            if (selectedAds != null) {
                for (BooksContent.BookItem c : selectedAds) {
                    // Faz o download da foto do anuncio para arquivo
                    // String url = c.urlFoto;
                    String url = "http://i.imgur.com/DvpvklR.png";
                    String fileName = url.substring(url.lastIndexOf("/"));
                    // Cria o arquivo no SD card
                    File file = SDCardUtils.getPrivateFile(getContext(), "anuncios", fileName);
                    IOUtils.downloadToFile(url, file);
                    // Salva a Uri para compartilhar a foto
                    imageUris.add(Uri.fromFile(file));
                }
            }
            return null;
        }

        @Override
        public void updateView(Object o) {
            // Cria a intent com a foto dos anuncios
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
            shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
            shareIntent.setType("image/*");
            // Cria o Intent Chooser com as opções
            startActivity(Intent.createChooser(shareIntent, "Enviar anúncios"));
        }

        @Override
        public void onError(Exception e) {
            alert("Ocorreu algum erro ao compartilhar.");
        }

        @Override
        public void onCancelled(String s) {
        }
    }
    protected void alert(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onListFragmentInteraction(Book item);

        void onListFragmentInteraction(BooksContent.BookItem mItem);

    }
    public void search(String texto){
        tempBooks = new ArrayList<BooksContent.BookItem>();
        for (BooksContent.BookItem book: books){
            if (book.getTitle().toLowerCase().contains(texto)){
                tempBooks.add(book);
            }
        }
        if (tempBooks.size() == 0){
            Toast.makeText(getActivity(), "Nenhum livro encontrado", Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(new BooksRecyclerViewAdapter( getContext(), BooksContent.ITEMS, mListener));
       // recyclerView.setAdapter(new BooksRecyclerViewAdapter(getContext(), tempBooks//, onClickAnuncio()));
    }

    public void closeSearch(){
        tempBooks = null;
        recyclerView.setAdapter(new BooksRecyclerViewAdapter( getContext(), BooksContent.ITEMS, mListener));
        //recyclerView.setAdapter(new BooksRecyclerViewAdapter(getContext(), books, on));
    }
}
