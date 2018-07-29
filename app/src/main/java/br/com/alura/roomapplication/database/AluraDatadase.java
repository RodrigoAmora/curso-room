package br.com.alura.roomapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import br.com.alura.roomapplication.conversor.ConversorDeData;
import br.com.alura.roomapplication.modelos.Aluno;
import br.com.alura.roomapplication.modelos.Prova;

@Database(entities = {Aluno.class, Prova.class}, version = 3)
@TypeConverters(ConversorDeData.class)
public abstract class AluraDatadase extends RoomDatabase {

    public abstract AlunoDao getAlunoDao();
    public abstract ProvaDao getProvaDao();

}
