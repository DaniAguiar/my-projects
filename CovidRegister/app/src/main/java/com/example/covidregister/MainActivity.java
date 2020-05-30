package com.example.covidregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButtonLoginByCPF();
        addButtonCadastrar();
        addButtonResultados();
    }

    private void addButtonLoginByCPF() {

        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaLogin();
            }
        });
    }

    public static final String EXTRA_MESSAGE = "com.example.covidregister.MESSAGE";

    private void telaLogin() {
        Intent intent = new Intent(this, Login.class);

        EditText editTextCPF = (EditText) findViewById(R.id.editTextCPF);
        String cpf = editTextCPF.getText().toString();

        intent.putExtra(EXTRA_MESSAGE,cpf);

        startActivity(intent);
    }

    private void addButtonCadastrar() {

        Button buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    telaCadastro();
            }
        });
    }

    private void telaCadastro() {
        Intent intent = new Intent(this, Cadastro.class);

        startActivity(intent);
    }

    private void addButtonResultados() {
        Button buttonResultados = (Button) findViewById(R.id.buttonResults);
        buttonResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaResultados();
            }
        });
    }

    private void telaResultados() {
        Intent intent = new Intent(this, Mapeamento.class);

        startActivity(intent);
    }

}
