package br.com.alura.roomapplication.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.delegate.AlunosDelegate;
import br.com.alura.roomapplication.fragments.FormularioAlunosFragment;
import br.com.alura.roomapplication.fragments.ListaAlunosFragment;

public class AlunosActivity extends AppCompatActivity implements AlunosDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);
        trocarDeFragment(new ListaAlunosFragment(), false);
    }

    public void trocarDeFragment(Fragment fragment, boolean empilhado) {
        FragmentManager gerencidorDeFragment = getSupportFragmentManager();
        FragmentTransaction transacao = gerencidorDeFragment.beginTransaction();
        transacao.replace(R.id.alunos_frame, fragment);
        if (empilhado) {
            transacao.addToBackStack(null);
        }
        transacao.commit();
    }

    @Override
    public void lidaComClickFAB() {
        trocarDeFragment(new FormularioAlunosFragment(), true);
    }

}