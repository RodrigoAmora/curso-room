package br.com.alura.roomapplication.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.fragments.ListaAlunosFragment;

public class AlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        FragmentManager gerencidorDeFragment = getSupportFragmentManager();
        FragmentTransaction transacao = gerencidorDeFragment.beginTransaction();
        transacao.replace(R.id.alunos_frame, new ListaAlunosFragment());
        transacao.commit();
    }

}
