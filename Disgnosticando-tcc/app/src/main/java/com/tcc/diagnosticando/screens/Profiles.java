package com.tcc.diagnosticando.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tcc.diagnosticando.dataBase.DBController;
import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.domain.Person;

import java.util.ArrayList;

public class Profiles extends AppCompatActivity {

    ListView listViewProfiles;
    DBController dbController;
    ArrayList<Person> peopleList;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        listViewProfiles = findViewById(R.id.listViewProfile);
        registerForContextMenu(listViewProfiles);

        listViewProfiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                Person chosenPerson = (Person) adapter.getItemAtPosition(position);

                Intent intent = new Intent(Profiles.this, Register.class);
                intent.putExtra("chosenPersonToEdit", chosenPerson);
                startActivity(intent);
            }
        });

        listViewProfiles.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                person = (Person) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuChooseProfileToConsultation = menu.add("Ir para a Consulta");
        menuChooseProfileToConsultation.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(Profiles.this, ChooseProfile.class);
                intent.putExtra("chosenProfileToConsultation", person);
                startActivity(intent);

                return true;
            }
        });

        MenuItem menuDelete = menu.add("Excluir Usuário");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dbController = new DBController(getBaseContext());
                dbController.deleteData(person);
                listAllProfiles();

                return true;
            }
        });
    }

    protected void onResume(){
        super.onResume();
        listAllProfiles();
    }

    private void listAllProfiles() {

        dbController = new DBController(getBaseContext());
        peopleList = dbController.loadData();

        ArrayAdapter arrayAdapter = new ArrayAdapter<Person>(Profiles.this,
                android.R.layout.simple_list_item_1, peopleList);

        listViewProfiles.setAdapter(arrayAdapter);
    }
}