package br.com.alura.roomapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.database.GeradorDeBancoDaDados;
import br.com.alura.roomapplication.delegate.AlunosDelegate;
import br.com.alura.roomapplication.modelos.Aluno;

public class ListaAlunosFragment extends Fragment implements View.OnClickListener {

    private AlunosDelegate delegate;
    private ListView listaAlunos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = (AlunosDelegate) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.setTitulo("Lista de Alunos");
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
        FloatingActionButton addAluno = view.findViewById(R.id.fragment_lista_fab);
        addAluno.setOnClickListener(this);
    }

    private void configurarLista(View view) {
        GeradorDeBancoDaDados gerador = new GeradorDeBancoDaDados();
        /*
        AluraDatadase aluraDatadase = gerador.gerar(getContext());
        AlunoDao alunoDao = aluraDatadase.getAuoDao();
        List<Aluno> alunos = alunoDao.busca();
        */
        Context context = getContext();
        List<Aluno> alunos = gerador.gerar(context).getAuoDao().busca();
        ListView lista = view.findViewById(R.id.fragment_lista);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(context, android.R.layout.simple_expandable_list_item_1, alunos);
        lista.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        delegate.lidaComClickFAB();
    }

}
