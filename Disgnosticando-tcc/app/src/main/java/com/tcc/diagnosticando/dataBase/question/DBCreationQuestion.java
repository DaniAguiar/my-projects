package com.tcc.diagnosticando.dataBase.question;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCreationQuestion extends SQLiteOpenHelper {

    private static final String QUESTION_DATABASE = "question.db";
    private static final int VERSION = 1;

    public DBCreationQuestion(Context context){
        super(context, QUESTION_DATABASE, null, VERSION);
    }

    static final String TABLE = "question";
    static final String ID = "_id";

    static final String TEXT = "text";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE +"("
                + ID + " integer primary key autoincrement NOT NULL,"
                + TEXT + " varchar(255) NOT NULL"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
