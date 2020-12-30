package com.tcc.diagnosticando.screens.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.dataBase.question.DBControllerQuestion;
import com.tcc.diagnosticando.dataBase.user.DBControllerUser;
import com.tcc.diagnosticando.domain.Person;
import com.tcc.diagnosticando.domain.Question;
import com.tcc.diagnosticando.screens.questions.Questions;

import java.util.ArrayList;
import java.util.Objects;

public class Principal extends AppCompatActivity {
    DBControllerUser dbControllerUser;
    ArrayList<Person> peopleList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        registerStandardQuestions();
        addButtonStartConsultation();
        addButtonRegister();
        addButtonListProfiles();
    }

    private void registerStandardQuestions() {
        DBControllerQuestion registry = new DBControllerQuestion(getBaseContext());
        ArrayList<Question> questionsList = registry.loadData();

        if (questionsList.size() > 0){
            return;
        } else {
            Question question1 = new Question();
            question1.setText("Sente dores locais?");
            registry.insertData(question1);

            Question question2 = new Question();
            question2.setText("Sente dores nas juntas?");
            registry.insertData(question2);

            Question question3 = new Question();
            question3.setText("Sente dores no abdômen?");
            registry.insertData(question3);

            Question question4 = new Question();
            question4.setText("Sente dores no reto?");
            registry.insertData(question4);

            Question question5 = new Question();
            question5.setText("Sente dores nos ouvidos?");
            registry.insertData(question5);

            Question question6 = new Question();
            question6.setText("Houve tosse com catarro?");
            registry.insertData(question6);

            Question question7 = new Question();
            question7.setText("Houve tosse seca?");
            registry.insertData(question7);

            Question question8 = new Question();
            question8.setText("Sente algum mal estar?");
            registry.insertData(question8);

            Question question9 = new Question();
            question9.setText("Está com febre?");
            registry.insertData(question9);

            Question question10 = new Question();
            question10.setText("Está com desidratação?");
            registry.insertData(question10);

            Question question11 = new Question();
            question11.setText("Está com congestão nasal?");
            registry.insertData(question11);

            Question question12 = new Question();
            question12.setText("Sente dor de garganta?");
            registry.insertData(question12);

            Question question13 = new Question();
            question13.setText("Sente alguma dificuldade para engolir?");
            registry.insertData(question13);

            Question question14 = new Question();
            question14.setText("Sente irritação na garganta?");
            registry.insertData(question14);

            Question question15 = new Question();
            question15.setText("Está com problemas no aparelho gastrointestinal?");
            registry.insertData(question15);

            Question question16 = new Question();
            question16.setText("Houve perda de apetite?");
            registry.insertData(question16);

            Question question17 = new Question();
            question17.setText("Sente sede excessiva?");
            registry.insertData(question17);

            Question question18 = new Question();
            question18.setText("Laringe está inflamada?");
            registry.insertData(question18);

            Question question19 = new Question();
            question19.setText("Está com indigestão?");
            registry.insertData(question19);

            Question question20 = new Question();
            question20.setText("Sente alguma dificuldade para falar?");
            registry.insertData(question20);

            Question question21 = new Question();
            question21.setText("Sente dores de cabeça?");
            registry.insertData(question21);

            Question question22 = new Question();
            question22.setText("Sente falta de ar?");
            registry.insertData(question22);

            Question question23 = new Question();
            question23.setText("Sente náuseas?");
            registry.insertData(question23);

            Question question24 = new Question();
            question24.setText("Sente a boca mais seca que o normal?");
            registry.insertData(question24);

            Question question25 = new Question();
            question25.setText("Houve perda repentina de peso?");
            registry.insertData(question25);

            Question question26 = new Question();
            question26.setText("Está com diarreia?");
            registry.insertData(question26);

            Question question27 = new Question();
            question27.setText("Está tendo vômitos?");
            registry.insertData(question27);

            Question question28 = new Question();
            question28.setText("Sente febre baixa?");
            registry.insertData(question28);

            Question question29 = new Question();
            question29.setText("Amígdalas estão inchadas?");
            registry.insertData(question29);

            Question question30 = new Question();
            question30.setText("Está com mau hálito?");
            registry.insertData(question30);

            Question question31 = new Question();
            question31.setText("Há salivação excessiva?");
            registry.insertData(question31);

            Question question32 = new Question();
            question32.setText("Nódulos cervicais estão aumentados?");
            registry.insertData(question32);

            Question question33 = new Question();
            question33.setText("Há pús na garganta?");
            registry.insertData(question33);

            Toast.makeText(
                    getApplicationContext(),
                    "Questões padrões registradas!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void addButtonStartConsultation() {
        Button buttonChooseProfile = findViewById(R.id.buttonChooseProfile);
        buttonChooseProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbControllerUser = new DBControllerUser(getBaseContext());
                peopleList = dbControllerUser.loadData();

                if (peopleList.size() == 1){
                    Intent intent = new Intent(Principal.this, Appointment.class);
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