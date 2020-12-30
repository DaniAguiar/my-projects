package com.tcc.diagnosticando.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCreationDiagnosisQuestionAssociation extends SQLiteOpenHelper {

    private static final String DIAGNOSIS_QUESTION_ASSOCIATION = "association.db";
    private static final int VERSION = 1;

    public DBCreationDiagnosisQuestionAssociation(Context context){
        super(context, DIAGNOSIS_QUESTION_ASSOCIATION, null,VERSION);
    }

    static final String TABLE = "diagnosis_question";
    static final String DIAGNOSIS_ID = "diagnosis_id";
    static final String QUESTION_ID = "question_id";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + "("
                + DIAGNOSIS_ID + " integer NOT NULL,"
                + " foreign key (" + DIAGNOSIS_ID + ") references diagnosis(_id),"
                + QUESTION_ID + " integer NOT NULL,"
                + " foreign key (" + QUESTION_ID + ") references question(_id)"
                +")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
