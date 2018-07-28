package br.com.alura.roomapplication.delegate;

import br.com.alura.roomapplication.modelos.Aluno;

public interface AlunosDelegate {

    void lidaComClickFAB();
    void voltarParaTelaAnterior();
    void setTitulo(String titulo);
    void lidaComAlunoSelecionado(Aluno aluno);

}
