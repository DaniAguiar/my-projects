package com.tcc.diagnosticando.screens.questions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.dataBase.question.DBControllerQuestion;
import com.tcc.diagnosticando.domain.Question;

import java.util.Objects;

public class CreateQuestions extends AppCompatActivity {
    Question updateQuestion;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_questions);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Intent intent = getIntent();
        updateQuestion = (Question) intent.getSerializableExtra("chosenQuestionToEdit");

        addButtonCreateNewQuestion(updateQuestion);
    }

    private void addButtonCreateNewQuestion(final Question updateQuestion) {
        final Button buttonCreateNewQuestion =  findViewById(R.id.buttonCreateNewQuestion);
        final EditText editTextNewQuestion = findViewById(R.id.editTextNewQuestion);

        if (updateQuestion != null){
            buttonCreateNewQuestion.setText("Atualizar");

            editTextNewQuestion.setText(updateQuestion.getText());
        }
        else{
            buttonCreateNewQuestion.setText("Cadastrar");
        }

        buttonCreateNewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question question = new Question();
                question.setText(editTextNewQuestion.getText().toString());

                DBControllerQuestion registry = new DBControllerQuestion(getBaseContext());

                if (buttonCreateNewQuestion.getText().toString().equals("Cadastrar")){
                    String cursor = registry.insertData(question);
                    Toast.makeText(getApplicationContext(), cursor, Toast.LENGTH_LONG).show();
                }
                else {
                    question.setId(updateQuestion.getId());
                    String cursor = registry.updateData(question);
                    Toast.makeText(getApplicationContext(), cursor, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}