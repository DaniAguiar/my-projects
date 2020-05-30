package com.example.covidregister;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCreation extends SQLiteOpenHelper {

    static final String NOME_BANCO = "bdCovid.db";
    static final String TABELA = "usuario";
    static final String ID = "_id";

    static final String NOME = "nome";
    static final String CPF = "cpf";
    static final String CIDADE   = "cidade";
    static final String DIAGNOSTICO = "diagnostico";

    private static final int VERSAO = 1;

    public DBCreation(Context context){
        super (context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + NOME + " varchar(50),"
                + CPF + " varchar(50),"
                + CIDADE + " varchar(50),"
                + DIAGNOSTICO + " varchar(50)"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
