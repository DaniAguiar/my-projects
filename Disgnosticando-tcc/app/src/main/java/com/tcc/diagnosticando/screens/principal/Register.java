package com.tcc.diagnosticando.screens.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tcc.diagnosticando.dataBase.user.DBControllerUser;
import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.domain.Person;

import java.util.Objects;

public class Register extends AppCompatActivity {
    EditText editTextName, editTextAge, editTextWeight, editTextHeight;
    Person updatePerson;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        updatePerson = (Person) intent.getSerializableExtra("chosenPersonToEdit");

        final Button buttonRegisterNewPerson = findViewById(R.id.buttonRegisterApply);

        editTextName = findViewById(R.id.editTextPersonName);
        editTextAge = findViewById(R.id.editTextPersonAge);
        editTextWeight = findViewById(R.id.editTextPersonWeight);
        editTextHeight = findViewById(R.id.editTextPersonHeight);

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

        addButtonRegisterNewPerson(buttonRegisterNewPerson);
        addButtonRegisterToScreenToHome();
    }

    private void addButtonRegisterNewPerson(final Button buttonRegisterNewPerson) {
        buttonRegisterNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person person = new Person();

                person.setName(editTextName.getText().toString());

                if(editTextAge.getText().toString().isEmpty()) person.setAge(null);
                else person.setAge(Integer.parseInt(editTextAge.getText().toString()));

                if(editTextWeight.getText().toString().isEmpty()) person.setWeight(null);
                else person.setWeight(Double.parseDouble(editTextWeight.getText().toString()));

                if(editTextHeight.getText().toString().isEmpty()) person.setHeight(null);
                else person.setHeight(Double.parseDouble(editTextHeight.getText().toString()));

                if (person.getName().isEmpty() || person.getAge() == null || person.getWeight() == null || person.getHeight() == null){
                    Toast.makeText(
                            getApplicationContext(),
                            "Preencha todos os campos para cadastrar",
                            Toast.LENGTH_LONG
                    ).show();
                    addButtonRegisterNewPerson(buttonRegisterNewPerson);
                } else {
                    DBControllerUser registry = new DBControllerUser(getBaseContext());

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
            }
        });
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