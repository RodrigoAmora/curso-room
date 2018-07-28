package br.com.alura.roomapplication.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.delegate.ProvasDelegate;
import br.com.alura.roomapplication.fragments.FormularioProvasFragment;
import br.com.alura.roomapplication.modelos.Prova;

public class ProvasActivity extends AppCompatActivity implements ProvasDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);
        trocarDeFragment(new FormularioProvasFragment(), false);
    }

    @Override
    public void lidaComClickFAB() {

    }

    @Override
    public void voltarParaTelaAnterior() {
        onBackPressed();
    }

    @Override
    public void setTitulo(String titulo) {
        setTitle(titulo);
    }

    @Override
    public void lidaComAProvaSelecionada(Prova prova) {
        Bundle argumentos = new Bundle();
        argumentos.putSerializable("prova", prova);

        FormularioProvasFragment formularioProvasFragment = new FormularioProvasFragment();
        formularioProvasFragment.setArguments(argumentos);
        trocarDeFragment(formularioProvasFragment, true);
    }


    private void trocarDeFragment(Fragment fragment, boolean empilhado) {
        FragmentManager gerencidorDeFragment = getSupportFragmentManager();
        FragmentTransaction transacao = gerencidorDeFragment.beginTransaction();
        transacao.replace(R.id.provas_frame, fragment);
        if (empilhado) {
            transacao.addToBackStack(null);
        }
        transacao.commit();
    }

}
