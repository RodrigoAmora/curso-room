package br.com.alura.roomapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import br.com.alura.roomapplication.modelos.Aluno;

@Dao
public interface AlunoDao {

    @Insert
    void insere(Aluno aluno);

}
