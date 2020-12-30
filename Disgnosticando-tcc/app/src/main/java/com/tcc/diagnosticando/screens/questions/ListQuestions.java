package com.tcc.diagnosticando.screens.questions;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.ContextMenu;

import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.dataBase.question.DBControllerQuestion;
import com.tcc.diagnosticando.dataBase.user.DBControllerUser;
import com.tcc.diagnosticando.domain.Question;
import com.tcc.diagnosticando.screens.principal.Appointment;
import com.tcc.diagnosticando.screens.principal.Profiles;

import java.util.ArrayList;
import java.util.Objects;

public class ListQuestions extends AppCompatActivity {

    ListView listViewQuestions;
    DBControllerQuestion dbControllerQuestion;
    ArrayList<Question> questionsList;
    Question question;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        Objects.requireNonNull(getSupportActionBar()).hide();

        listViewQuestions = findViewById(R.id.listViewQuestions);
        registerForContextMenu(listViewQuestions);

        listViewQuestions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Question chosenQuestion = (Question) adapter.getItemAtPosition(position);

                Intent intent = new Intent(ListQuestions.this, CreateQuestions.class);
                intent.putExtra("chosenQuestionToEdit", chosenQuestion);
                startActivity(intent);
            }
        });

        listViewQuestions.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                question = (Question) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Excluir Questão");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dbControllerQuestion = new DBControllerQuestion(getBaseContext());
                dbControllerQuestion.deleteData(question);
                listAllQuestions();

                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        listAllQuestions();
    }

    private void listAllQuestions() {

        dbControllerQuestion = new DBControllerQuestion(getBaseContext());
        questionsList = dbControllerQuestion.loadData();

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(ListQuestions.this,
                android.R.layout.simple_list_item_1, questionsList);

        listViewQuestions.setAdapter(arrayAdapter);
    }
}