package br.com.dercilima.myfirstapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dercilima on 01/12/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AlunoDB.sqlite";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String createTable = "CREATE TABLE ALUNOS (" +
                "_id INTEGER NOT NULL PRIMARY KEY," +
                "NOME TEXT," +
                "IDADE INTEGER," +
                "CURSO TEXT" +
                ")";

        database.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
