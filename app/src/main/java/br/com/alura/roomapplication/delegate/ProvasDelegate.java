package br.com.alura.roomapplication.delegate;

import br.com.alura.roomapplication.modelos.Prova;

public interface ProvasDelegate {

    void lidaComClickFAB();
    void voltarParaTelaAnterior();
    void setTitulo(String titulo);
    void lidaComAProvaSelecionada(Prova prova);

}
