package com.example.covidregister;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.BaseColumns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);

        DBCreation dbHelper = new DBCreation(getBaseContext());

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DBCreation.NOME,
                DBCreation.CPF,
                DBCreation.DIAGNOSTICO,
                DBCreation.CIDADE,
        };

        String where = DBCreation.CPF + " = 111111";

        Cursor cursor = db.query(
                DBCreation.TABELA,
                projection,
                where,
                null,
                null,
                null,
                null);

        List itemIds = new ArrayList();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(DBCreation.ID));
            itemIds.add(itemId);
        }

        EditText editTextName = (EditText) findViewById(R.id.editName);
        editTextName.setText(cursor.getString(0));

    }

}
