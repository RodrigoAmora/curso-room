package br.com.alura.roomapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.database.AlunoDao;
import br.com.alura.roomapplication.database.AluraDatadase;
import br.com.alura.roomapplication.database.GeradorDeBancoDaDados;
import br.com.alura.roomapplication.delegate.AlunosDelegate;
import br.com.alura.roomapplication.modelos.Aluno;

public class ListaAlunosFragment extends Fragment implements View.OnClickListener {

    private AlunosDelegate delegate;
    private FloatingActionButton addAluno;
    private ListView listaAlunos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = (AlunosDelegate) getActivity();
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
        addAluno = view.findViewById(R.id.fragment_lista_fab);
        addAluno.setOnClickListener(this);
    }

    private void configurarLista(View view) {
        Context context = getContext();

        GeradorDeBancoDaDados gerador = new GeradorDeBancoDaDados();
        AluraDatadase aluraDatadase = gerador.gerar(context);
        final AlunoDao alunoDao = aluraDatadase.getAlunoDao();
        List<Aluno> alunos = alunoDao.busca();

        final ListView lista = view.findViewById(R.id.fragment_lista);
        final ArrayAdapter<Aluno> adapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, alunos);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) lista.getItemAtPosition(position);
                delegate.lidaComAlunoSelecionado(aluno);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Aluno aluno = (Aluno) lista.getItemAtPosition(position);
                String mensagemDelete = "Excluir aluno "+aluno.getNome()+"?";
                Snackbar.make(addAluno, mensagemDelete, Snackbar.LENGTH_SHORT)
                        .setAction("Sim", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alunoDao.apagar(aluno);
                                adapter.remove(aluno);
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
