package br.com.alura.roomapplication.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.database.AluraDatadase;
import br.com.alura.roomapplication.database.GeradorDeBancoDaDados;
import br.com.alura.roomapplication.database.ProvaDao;
import br.com.alura.roomapplication.delegate.ProvasDelegate;
import br.com.alura.roomapplication.modelos.Prova;

public class ListaProvasFragments extends Fragment implements View.OnClickListener {

    private ProvasDelegate delegate;
    private FloatingActionButton addProva;
    private ListView listaProvas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = (ProvasDelegate) getActivity();
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.lista_provas_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_lista_porvas_calendario) {
            Context context = getContext();
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            EditText campoInicio = new EditText(context);
            campoInicio.setHint("Inicio");
            campoInicio.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

            EditText campoFim = new EditText(context);
            campoFim.setHint("Fim");
            campoFim.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE);

            linearLayout.addView(campoInicio);
            linearLayout.addView(campoFim);

            new AlertDialog.Builder(context)
                    .setMessage("Datas para busca")
                    .setPositiveButton("Buscar", null)
                    .setNegativeButton("Cancelar", null)
                    .show();
        }
        
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.setTitulo(getString(R.string.lista_de_alunos));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        configurarComponentsDa(view);
        return view;
    }

    private void configurarComponentsDa(View view) {
        configurarLista(view);
        configurarFAB(view);
    }

    private void configurarFAB(View view) {
        addProva = view.findViewById(R.id.fragment_lista_fab);
        addProva.setOnClickListener(this);
    }

    private void configurarLista(View view) {
        Context context = getContext();

        GeradorDeBancoDaDados gerador = new GeradorDeBancoDaDados();
        AluraDatadase aluraDatadase = gerador.gerar(context);
        final ProvaDao alunoDao = aluraDatadase.getProvaDao();
        List<Prova> provas = alunoDao.busca();

        final ListView lista = view.findViewById(R.id.fragment_lista);
        final ArrayAdapter<Prova> adapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, provas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova prova = (Prova) lista.getItemAtPosition(position);
                delegate.lidaComAProvaSelecionada(prova);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Prova prova = (Prova) lista.getItemAtPosition(position);
                String mensagemDelete = "Excluir prova "+prova.getMateria()+"?";
                Snackbar.make(addProva, mensagemDelete, Snackbar.LENGTH_SHORT)
                        .setAction("Sim", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alunoDao.apagar(prova);
                                adapter.remove(prova);
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        delegate.lidaComClickFAB();
    }

}
