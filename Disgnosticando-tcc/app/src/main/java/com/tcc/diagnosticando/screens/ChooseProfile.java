package com.tcc.diagnosticando.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.domain.Person;

public class ChooseProfile extends AppCompatActivity {
    Person chosenPerson, uniquePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_consultation);

        addButtonRegister();
        addButtonChooseProfile();
        addButtonStartWithoutRegister();

        Intent intent = getIntent();
        chosenPerson = (Person) intent.getSerializableExtra("chosenProfileToConsultation");
        uniquePerson = (Person) intent.getSerializableExtra("uniquePerson");

        TextView textViewPerson = findViewById(R.id.textViewPerson);

        if (chosenPerson != null){
            textViewPerson.setText("Bem vindo " + chosenPerson.getName() + "!");
        }

        if (uniquePerson != null){
            textViewPerson.setText("Bem vindo " + uniquePerson.getName() + "!");
        }
    }

    private void addButtonRegister() {
        Button buttonRegister = findViewById(R.id.buttonChooseProfileRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProfile.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void addButtonChooseProfile() {
        Button buttonChooseProfile = findViewById(R.id.buttonChooseProfile);
        buttonChooseProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProfile.this, Profiles.class);
                startActivity(intent);
            }
        });
    }

    private void addButtonStartWithoutRegister() {
        Button buttonStartWithoutRegister = findViewById(R.id.buttonStartWithoutRegister);
        buttonStartWithoutRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseProfile.this, Appointment.class);
                startActivity(intent);
            }
        });
    }
}