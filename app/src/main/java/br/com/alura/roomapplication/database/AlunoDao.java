package br.com.alura.roomapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.alura.roomapplication.modelos.Aluno;

@Dao
public interface AlunoDao {

    @Insert
    void insere(Aluno aluno);

    @Query("select * from Aluno order by nome")
    List<Aluno> busca();

    @Update()
    void altera(Aluno aluno);

}
