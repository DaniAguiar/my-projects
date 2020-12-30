package com.tcc.diagnosticando.dataBase.diagnosis;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tcc.diagnosticando.domain.Diagnosis;

public class DBControllerDiagnosis {

    private DBCreationDiagnosis diagnosisDataBase;

    public DBControllerDiagnosis(Context context){
        diagnosisDataBase = new DBCreationDiagnosis(context);
    }

    public String insertData(final Diagnosis diagnosis){
        ContentValues values = new ContentValues();
        long flag;

        SQLiteDatabase db = diagnosisDataBase.getWritableDatabase();

        values.put(DBCreationDiagnosis.DISEASE, diagnosis.getDisease());

        flag = db.insert(DBCreationDiagnosis.TABLE, null, values);
        db.close();

        if (flag == -1)
            return "Erro ao inserir regitro";
        else
            return "Registro inserido com sucesso";
    }
}
