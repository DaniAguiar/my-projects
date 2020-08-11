package com.tcc.diagnosticando.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.dataBase.DBController;
import com.tcc.diagnosticando.domain.Person;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    DBController dbController;
    ArrayList<Person> peopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButtonStartConsultation();
        addButtonRegister();
        addButtonListProfiles();
    }

    private void addButtonStartConsultation() {
        Button buttonChooseProfile = findViewById(R.id.buttonChooseProfile);
        buttonChooseProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbController = new DBController(getBaseContext());
                peopleList = dbController.loadData();
                System.out.println(peopleList.size());

                if (peopleList.size() == 1){
                    Intent intent = new Intent(Principal.this, ChooseProfile.class);
                    intent.putExtra("uniquePerson", peopleList.get(0));
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Principal.this, ChooseProfile.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void addButtonRegister() {
        Button buttonRegister = findViewById(R.id.buttonRegistry);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Register.class);
                startActivity(intent);
            }
        });
    }

    private void addButtonListProfiles() {
        Button buttonListProfiles = findViewById(R.id.buttonListProfile);
        buttonListProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Principal.this, Profiles.class);
                startActivity(intent);
            }
        });
    }
}