package com.tcc.diagnosticando.screens.principal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tcc.diagnosticando.dataBase.user.DBControllerUser;
import com.tcc.diagnosticando.R;
import com.tcc.diagnosticando.domain.Person;

import java.util.ArrayList;
import java.util.Objects;

public class Profiles extends AppCompatActivity {

    ListView listViewProfiles;
    DBControllerUser dbControllerUser;
    ArrayList<Person> peopleList;
    Person person;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        Objects.requireNonNull(getSupportActionBar()).hide();

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
                Intent intent = new Intent(Profiles.this, Appointment.class);
                intent.putExtra("chosenProfileToConsultation", person);
                startActivity(intent);

                return true;
            }
        });

        MenuItem menuDelete = menu.add("Excluir Usuário");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dbControllerUser = new DBControllerUser(getBaseContext());
                dbControllerUser.deleteData(person);
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

        dbControllerUser = new DBControllerUser(getBaseContext());
        peopleList = dbControllerUser.loadData();

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(Profiles.this,
                android.R.layout.simple_list_item_1, peopleList);

        listViewProfiles.setAdapter(arrayAdapter);
    }
}