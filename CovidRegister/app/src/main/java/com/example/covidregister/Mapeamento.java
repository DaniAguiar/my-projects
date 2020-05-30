package com.example.covidregister;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Mapeamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapeamento);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregaDados();
    }

    private void carregaDados() {
        DBController crud = new DBController(getBaseContext());
        Cursor cursor = crud.carregaDados();

        String[] nomeCampos = new String[] {DBCreation.ID, DBCreation.NOME};
        int[] idViews = new int[] {R.id.lblValorA, R.id.lblValorB};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.id.listView, cursor, nomeCampos, idViews, 0);

        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);
    }
}