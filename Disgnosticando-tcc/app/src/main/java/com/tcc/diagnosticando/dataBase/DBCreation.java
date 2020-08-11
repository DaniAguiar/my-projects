package com.tcc.diagnosticando.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCreation extends SQLiteOpenHelper {

    private static final String USER_DATABASE = "user.db";
    private static final int VERSION = 1;

    public DBCreation(Context context){
        super (context, USER_DATABASE, null, VERSION);
    }

    static final String TABLE = "people";
    static final String ID = "_id";

    static final String NAME = "name";
    static final String AGE = "age";
    static final String WEIGHT   = "weight";
    static final String HEIGHT = "height";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABLE+"("
                + ID + " integer primary key autoincrement NOT NULL,"
                + NAME + " varchar(50) NOT NULL,"
                + AGE + " int(50) NOT NULL,"
                + WEIGHT + " real(50) NOT NULL,"
                + HEIGHT + " real(50) NOT NULL"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
