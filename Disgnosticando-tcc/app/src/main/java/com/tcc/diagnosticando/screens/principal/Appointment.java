package com.tcc.diagnosticando.screens.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.dataBase.question.DBControllerQuestion;
import com.tcc.diagnosticando.domain.Question;
import com.tcc.diagnosticando.screens.DiagnosisResult;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Appointment extends AppCompatActivity {

    TextView questionTextView;
    RadioButton yesRadio, noRadio;
    DBControllerQuestion dbControllerQuestion;

    ArrayList<Question> questions;
    ArrayList<String> answers = new ArrayList<>();

    Button buttonNext;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Objects.requireNonNull(getSupportActionBar()).hide();

        dbControllerQuestion = new DBControllerQuestion(getBaseContext());
        questions = dbControllerQuestion.loadData();

        questionTextView = (TextView)findViewById(R.id.textViewQuestion);
        yesRadio = (RadioButton)findViewById(R.id.radioYes);
        noRadio = (RadioButton)findViewById(R.id.radioNo);

        loaderQuestions(answers);
        appointmentQuestions();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        loaderQuestions(answers);
    }

    private void appointmentQuestions() {
        buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesRadio.isChecked()) {
                    answers.add("yes");
                    onRestart();
                } else
                    if (noRadio.isChecked()) {
                        answers.add("no");
                        onRestart();
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "É preciso selecionar uma das opções para prosseguir",
                                Toast.LENGTH_LONG
                        ).show();
                        appointmentQuestions();
                    }
                System.out.println("Appoitment: " + answers.size());
            }
        });
    }

    public void loaderQuestions(final ArrayList<String> answers){
        if (questions.size() > 0) {
            Question showQuestion = questions.remove(0);
            questionTextView.setText(showQuestion.getText());
            yesRadio.setText("SIM");
            noRadio.setText("NÃO");

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Seu diagnóstico está ficando pronto...",
                    Toast.LENGTH_LONG
            ).show();

            buttonNext.setEnabled(false);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    showDiagnosis(answers);
                    finish();
                }
            });
            thread.start();
        }
    }

    private void showDiagnosis(ArrayList<String> answers) {
        Intent intent = new Intent(this, DiagnosisResult.class);
        intent.putExtra("answers", answers);
        startActivity(intent);
    }
}