package com.tcc.diagnosticando.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tcc.diagnosticando.dataBase.DBController;
import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.domain.Person;

public class Register extends AppCompatActivity {
    Person updatePerson;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editTextName, editTextAge, editTextWeight, editTextHeight;

        editTextName = findViewById(R.id.editTextPersonName);
        editTextAge = findViewById(R.id.editTextPersonAge);
        editTextWeight = findViewById(R.id.editTextPersonWeight);
        editTextHeight = findViewById(R.id.editTextPersonHeight);


        final Button buttonRegisterNewPerson = findViewById(R.id.buttonRegisterApply);

        Intent intent = getIntent();
        updatePerson = (Person) intent.getSerializableExtra("chosenPersonToEdit");

        if (updatePerson != null){
            buttonRegisterNewPerson.setText("Atualizar");

            editTextName.setText(updatePerson.getName());
            editTextAge.setText(updatePerson.getAge()+"");
            editTextWeight.setText(updatePerson.getWeight()+"");
            editTextHeight.setText(updatePerson.getHeight()+"");
        }
        else{
            buttonRegisterNewPerson.setText("Cadastrar");
        }

        buttonRegisterNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();

                person.setName(editTextName.getText().toString());
                person.setAge(Integer.parseInt(editTextAge.getText().toString()));
                person.setWeight(Double.parseDouble(editTextWeight.getText().toString()));
                person.setHeight(Double.parseDouble(editTextHeight.getText().toString()));

                DBController registry = new DBController(getBaseContext());

                if (buttonRegisterNewPerson.getText().toString().equals("Cadastrar")){
                    String cursor = registry.insertData(person);
                    Toast.makeText(getApplicationContext(), cursor, Toast.LENGTH_LONG).show();
                }
                else {
                    person.setId(updatePerson.getId());
                    String cursor = registry.updateData(person);
                    Toast.makeText(getApplicationContext(), cursor, Toast.LENGTH_LONG).show();
                }
            }
        });

        addButtonRegisterToScreenToHome();
    }

    private void addButtonRegisterToScreenToHome() {
        Button buttonRegisterHome = findViewById(R.id.buttonRegisterBack);
        buttonRegisterHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Principal.class);
                startActivity(intent);
            }
        });
    }
}