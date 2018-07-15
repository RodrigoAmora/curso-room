package br.com.alura.roomapplication.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class GeradorDeBancoDaDados {

    public AluraDatadase gerar(Context context) {
        AluraDatadase aluraDB = Room
                .databaseBuilder(context, AluraDatadase.class, "AluraDB")
                .allowMainThreadQueries()
                .build();

        return aluraDB;
    }

}
