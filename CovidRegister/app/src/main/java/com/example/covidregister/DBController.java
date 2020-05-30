package com.example.covidregister;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBController {

    private DBCreation banco;

    public DBController(Context context){
        banco = new DBCreation(context);
    }

    public String insereDados(String nome, String cpf, String cidade, String diagnostico){
        ContentValues valores;
        long resultado;

        SQLiteDatabase db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(DBCreation.NOME, nome);
        valores.put(DBCreation.CPF, cpf);
        valores.put(DBCreation.CIDADE, cidade);
        valores.put(DBCreation.DIAGNOSTICO, diagnostico);

        resultado = db.insert(DBCreation.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {DBCreation.ID, DBCreation.NOME};
        SQLiteDatabase db = banco.getReadableDatabase();
        cursor = db.query(DBCreation.TABELA, campos, null, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        db.close();
        return cursor;
    }
}
