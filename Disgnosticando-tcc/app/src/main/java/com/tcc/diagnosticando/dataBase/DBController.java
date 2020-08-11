package com.tcc.diagnosticando.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tcc.diagnosticando.domain.Person;

import java.util.ArrayList;

public class DBController {

    private DBCreation personDataBase;

    public DBController(Context context){
        personDataBase = new DBCreation(context);
    }

    public String insertData(final Person person){
        ContentValues values = new ContentValues();
        long flag;

        SQLiteDatabase db = personDataBase.getWritableDatabase();

        values.put(DBCreation.NAME, person.getName());
        values.put(DBCreation.AGE, person.getAge());
        values.put(DBCreation.WEIGHT, person.getWeight());
        values.put(DBCreation.HEIGHT, person.getHeight());

        flag = db.insert(DBCreation.TABLE, null, values);
        db.close();

        if (flag == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public ArrayList<Person> loadData(){

        String[] fields = {DBCreation.ID, DBCreation.NAME, DBCreation.AGE, DBCreation.WEIGHT, DBCreation.HEIGHT};
        SQLiteDatabase db = personDataBase.getReadableDatabase();

        Cursor cursor = personDataBase.getWritableDatabase().query(DBCreation.TABLE, fields,
                null, null, null, null, null, null);

        ArrayList<Person> people = new ArrayList<>();

        while (cursor.moveToNext()){
            Person person = new Person();
            person.setId(cursor.getLong(0));
            person.setName(cursor.getString(1));
            person.setAge(cursor.getInt(2));
            person.setWeight(cursor.getDouble(3));
            person.setHeight(cursor.getDouble(4));

            people.add(person);
        }

        db.close();
        return people;
    }

    public String updateData(Person person){
        ContentValues values = new ContentValues();
        long flag;

        SQLiteDatabase db = personDataBase.getWritableDatabase();

        values.put(DBCreation.NAME, person.getName());
        values.put(DBCreation.AGE, person.getAge());
        values.put(DBCreation.WEIGHT, person.getWeight());
        values.put(DBCreation.HEIGHT, person.getHeight());

        String[] args = {person.getId().toString()};

        flag = db.update(DBCreation.TABLE, values, DBCreation.ID+"=?", args);
        db.close();

        if (flag == -1)
            return "Erro ao inserir registro";
        else
            return "Registro alterado com sucesso";
    }

    public void deleteData(Person person){
        SQLiteDatabase db = personDataBase.getWritableDatabase();

        String[] args = {person.getId().toString()};
        db.delete(DBCreation.TABLE, DBCreation.ID+"=?", args);

        db.close();
    }
}
