package com.tcc.diagnosticando.dataBase.question;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tcc.diagnosticando.domain.Question;

import java.util.ArrayList;

public class DBControllerQuestion {

    private DBCreationQuestion questionDataBase;

    public DBControllerQuestion(Context context){
        questionDataBase = new DBCreationQuestion(context);
    }

    public String insertData(final Question question){
        ContentValues values = new ContentValues();
        long flag;

        SQLiteDatabase db = questionDataBase.getWritableDatabase();

        values.put(DBCreationQuestion.TEXT, question.getText());

        flag = db.insert(DBCreationQuestion.TABLE, null, values);
        db.close();

        if (flag == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public ArrayList<Question> loadData(){
        String[] fields = {DBCreationQuestion.ID, DBCreationQuestion.TEXT};
        SQLiteDatabase db = questionDataBase.getReadableDatabase();

        Cursor cursor = questionDataBase.getWritableDatabase().query(DBCreationQuestion.TABLE, fields,
                null, null, null, null, null, null);

        ArrayList<Question> questions = new ArrayList<>();

        while (cursor.moveToNext()){
            Question question = new Question();
            question.setId(cursor.getLong(0));
            question.setText(cursor.getString(1));

            questions.add(question);
        }

        db.close();
        return questions;
    }

    public String updateData(Question question){
        ContentValues values = new ContentValues();
        long flag;

        SQLiteDatabase db = questionDataBase.getWritableDatabase();

        values.put(DBCreationQuestion.TEXT, question.getText());

        String[] args = {question.getId().toString()};

        flag = db.update(DBCreationQuestion.TABLE, values, DBCreationQuestion.ID+"=?", args);
        db.close();

        if (flag == -1)
            return "Erro ao inserir registro";
        else
            return "Registro alterado com sucesso";
    }

    public void deleteData(Question question){
        SQLiteDatabase db = questionDataBase.getWritableDatabase();

        String[] args = {question.getId().toString()};
        db.delete(DBCreationQuestion.TABLE, DBCreationQuestion.ID+"=?", args);

        db.close();
    }
}
