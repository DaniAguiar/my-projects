package com.example.covidregister;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_covid);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputSpinner();
        addButtonSalvar();
    }

    private void inputSpinner() {
        Spinner spOperation = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cidade, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spOperation.setAdapter(adapter);
    }

    private void addButtonSalvar() {
        Button buttonSalvar = (Button) findViewById(R.id.buttonSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editName = (EditText) findViewById(R.id.editName);
                EditText editCPF = (EditText) findViewById(R.id.editCPF);
                RadioButton positivoCovid = (RadioButton) findViewById(R.id.radioButton);
                Spinner spinnerCidade = (Spinner) findViewById(R.id.spinner);

                String nome = editName.getText().toString();
                String cpf = editCPF.getText().toString();
                String cidade = spinnerCidade.getSelectedItem().toString();
                String diagnostico;

                if(positivoCovid.isChecked())
                    diagnostico = "positivo";
                else
                    diagnostico = "negativo";

                DBController cadastrar = new DBController(getBaseContext());
                String cursor = cadastrar.insereDados(nome, cpf, cidade, diagnostico);

                Toast.makeText(getApplicationContext(), cursor, Toast.LENGTH_LONG).show();
            }
        });
    }

}
