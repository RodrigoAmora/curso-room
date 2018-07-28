package br.com.alura.roomapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.alura.roomapplication.modelos.Aluno;
import br.com.alura.roomapplication.modelos.Prova;

@Database(entities = {Aluno.class, Prova.class}, version = 2)
public abstract class AluraDatadase extends RoomDatabase {

    public abstract AlunoDao getAuoDao();
    public abstract ProvaDao getProvaDao();

}
