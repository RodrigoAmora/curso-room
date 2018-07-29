package br.com.alura.roomapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import br.com.alura.roomapplication.R;
import br.com.alura.roomapplication.conversor.ConversorDeData;
import br.com.alura.roomapplication.database.GeradorDeBancoDaDados;
import br.com.alura.roomapplication.database.ProvaDao;
import br.com.alura.roomapplication.delegate.ProvasDelegate;
import br.com.alura.roomapplication.modelos.Prova;

public class FormularioProvasFragment extends Fragment implements ProvasDelegate {

    private Button cadastrar;
    private EditText inputMateria, inputData;
    private ProvasDelegate delegate;

    private Prova prova = new Prova();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = (ProvasDelegate) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario_provas, container, false);
        configurarCampos(view);
        popularCampos();
        return view;
    }

    @Override
    public void lidaComClickFAB() {

    }

    @Override
    public void voltarParaTelaAnterior() {
        delegate.voltarParaTelaAnterior();
    }

    @Override
    public void setTitulo(String titulo) {
        delegate.setTitulo("Formulário de Provas");
    }

    @Override
    public void lidaComAProvaSelecionada(Prova prova) {

    }

    private void atualizarProva() {
        prova.setMateria(inputMateria.getText().toString());
        prova.setDataRealizacao(ConversorDeData.converterParaCalendar(inputData.getText().toString()));
    }

    private void configurarCampos(View view) {
        inputData = view.findViewById(R.id.formulario_prova_data);
        inputMateria = view.findViewById(R.id.formulario_prova_materia);
        cadastrar = view.findViewById(R.id.formulario_prova_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarProva();
                GeradorDeBancoDaDados geradorDeBancoDaDados = new GeradorDeBancoDaDados();
                ProvaDao provaDao = geradorDeBancoDaDados.gerar(getContext()).getProvaDao();
                provaDao.insere(prova);
                voltarParaTelaAnterior();
            }
        });
    }

    private void popularCampos() {
        Bundle argumnetos = getArguments();
        if (argumnetos != null) {
            prova = (Prova) argumnetos.getSerializable("prova");
            inputData.setText(ConversorDeData.converterDo(prova.getDataRealizacao()));
            inputMateria.setText(prova.getMateria());
        }
    }

}
