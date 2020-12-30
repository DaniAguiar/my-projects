package com.tcc.diagnosticando.dataBase.diagnosis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCreationDiagnosis extends SQLiteOpenHelper {

    private static final String DIAGNOSIS_DATABASE = "diagnosis.db";
    private static final int VERSION = 1;

    public DBCreationDiagnosis(Context context){
        super(context, DIAGNOSIS_DATABASE, null, VERSION);
    }

    static final String TABLE = "diagnosis";
    static final String ID = "_id";

    static final String DISEASE = "disease";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "("
                + ID + " integer primary key autoincrement NOT NULL,"
                + DISEASE + " varchar(255) NOT NULL"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
