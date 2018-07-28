package br.com.alura.roomapplication.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.alura.roomapplication.modelos.Prova;

@Dao
public interface ProvaDao {

    @Insert
    void insere(Prova prova);

    @Query("select * from Prova order by materia")
    List<Prova> busca();

}
