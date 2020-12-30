package com.tcc.diagnosticando.screens.questions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tcc.diagnosticando.R;

import java.util.Objects;

public class Questions extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Objects.requireNonNull(getSupportActionBar()).hide();

        addButtonQuestionsCreate();
        addButtonQuestionsList();
        addButtonQuestionStandard();
    }

    private void addButtonQuestionStandard() {
        Button buttonQuestionStandard = findViewById(R.id.buttonQuestionStandard);
        buttonQuestionStandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void addButtonQuestionsCreate() {
        Button buttonQuestionsCreate = findViewById(R.id.buttonQuestionsCreate);
        buttonQuestionsCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questions.this, CreateQuestions.class);
                startActivity(intent);
            }
        });
    }

    private void addButtonQuestionsList() {
        Button buttonQuestionsList = findViewById(R.id.buttonQuestionsList);
        buttonQuestionsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questions.this, ListQuestions.class);
                startActivity(intent);
            }
        });
    }
}