package br.com.alura.roomapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.delegate.AlunosDelegate;

public class ListaAlunosFragment extends Fragment implements View.OnClickListener {

    ListView listaAlunos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        FloatingActionButton addAluno = view.findViewById(R.id.fragment_lista_fab);
        addAluno.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        AlunosDelegate delegate = (AlunosDelegate) getActivity();
        delegate.lidaComClickFAB();
    }

}