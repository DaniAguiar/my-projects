package com.tcc.diagnosticando.dataBase.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tcc.diagnosticando.domain.Person;

import java.util.ArrayList;

public class DBControllerUser {

    private DBCreationUser personDataBase;

    public DBControllerUser(Context context){
        personDataBase = new DBCreationUser(context);
    }

    public String insertData(final Person person){
        ContentValues values = new ContentValues();
        long flag;

        SQLiteDatabase db = personDataBase.getWritableDatabase();

        values.put(DBCreationUser.NAME, person.getName());
        values.put(DBCreationUser.AGE, person.getAge());
        values.put(DBCreationUser.WEIGHT, person.getWeight());
        values.put(DBCreationUser.HEIGHT, person.getHeight());

        flag = db.insert(DBCreationUser.TABLE, null, values);
        db.close();

        if (flag == -1)
            return "Erro ao inserir registro";
        else
            return "Registro inserido com sucesso";
    }

    public ArrayList<Person> loadData(){

        String[] fields = {DBCreationUser.ID, DBCreationUser.NAME, DBCreationUser.AGE, DBCreationUser.WEIGHT, DBCreationUser.HEIGHT};
        SQLiteDatabase db = personDataBase.getReadableDatabase();

        Cursor cursor = personDataBase.getWritableDatabase().query(DBCreationUser.TABLE, fields,
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

        values.put(DBCreationUser.NAME, person.getName());
        values.put(DBCreationUser.AGE, person.getAge());
        values.put(DBCreationUser.WEIGHT, person.getWeight());
        values.put(DBCreationUser.HEIGHT, person.getHeight());

        String[] args = {person.getId().toString()};

        flag = db.update(DBCreationUser.TABLE, values, DBCreationUser.ID+"=?", args);
        db.close();

        if (flag == -1)
            return "Erro ao inserir registro";
        else
            return "Registro alterado com sucesso";
    }

    public void deleteData(Person person){
        SQLiteDatabase db = personDataBase.getWritableDatabase();

        String[] args = {person.getId().toString()};
        db.delete(DBCreationUser.TABLE, DBCreationUser.ID+"=?", args);

        db.close();
    }
}
