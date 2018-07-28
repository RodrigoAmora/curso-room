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
import br.com.alura.roomapplication.database.AlunoDao;
import br.com.alura.roomapplication.database.GeradorDeBancoDaDados;
import br.com.alura.roomapplication.delegate.AlunosDelegate;
import br.com.alura.roomapplication.modelos.Aluno;

public class FormularioAlunosFragment extends Fragment {

    private Aluno aluno = new Aluno();
    private AlunosDelegate delegate;

    private EditText campoNome;
    private EditText campoEmail;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        delegate = (AlunosDelegate) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        delegate.setTitulo("Formulário de Alunos");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_formulario_alunos, container, false);

        campoEmail = view.findViewById(R.id.formulario_alunos_email);
        campoNome = view.findViewById(R.id.formulario_alunos_nome);

        Button cadastrar = view.findViewById(R.id.formulario_alunos_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarAluno();
                persisteAluno();
                delegate.voltarParaTelaAnterior();
            }
        });

        colocarAlunoNaTela();

        return view;
    }

    private void persisteAluno() {
        GeradorDeBancoDaDados gerador = new GeradorDeBancoDaDados();
        AlunoDao alunoDao = gerador.gerar(getActivity()).getAlunoDao();
        if (aluno.getId() == null) {
            alunoDao.insere(aluno);
        } else {
            alunoDao.altera(aluno);
        }
    }

    private void colocarAlunoNaTela() {
        Bundle argumentos = getArguments();
        if (argumentos != null) {
            this.aluno = (Aluno) argumentos.getSerializable("aluno");
            campoEmail.setText(aluno.getEmail());
            campoNome.setText(aluno.getNome());
        }
    }

    private void atualizarAluno() {
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setNome(campoNome.getText().toString());
    }

}
