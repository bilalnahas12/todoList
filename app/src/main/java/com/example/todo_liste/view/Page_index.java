package com.example.todo_liste.view;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.todo_liste.R;
import com.example.todo_liste.adapter.Adapter_task_decortion;
import com.example.todo_liste.adapter.MyAdaper_2;
import com.example.todo_liste.model.Proprieter;
import com.example.todo_liste.model.Task;
import com.example.todo_liste.stokPerson.Stokperson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Page_index extends AppCompatActivity {
    List<Task> taskList = new ArrayList<>();
    RecyclerView lv;
    FloatingActionButton fbtn;
    Intent intent;
    List<HashMap<String, String>> list=new ArrayList<>();
    MyAdaper_2 a;
    private List<HashMap<String, String>> convertTaskListToHashMapList(ArrayList<Task> task) {
        for (Task t:task) {
            HashMap<String, String> taskMap = new HashMap<>();
            taskMap.put("nom", t.getTitre());
            taskMap.put("description", t.getDescription());
            taskMap.put("endDate", String.valueOf(t.getDate_fin()));
            list.add(taskMap);

        }
        return list;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_index);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv = findViewById(R.id.lv);

        convertTaskListToHashMapList(Stokperson.currentPerson.getTasks());
        a = new MyAdaper_2((ArrayList) list, this);
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.addItemDecoration(new Adapter_task_decortion(10));
        lv.setAdapter(a);
        fbtn = findViewById(R.id.fab);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), View_task.class);
                startActivity(intent);
            }
        });
    }



    /////////////////////////menu////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mennu, menu);


        MenuItem.OnActionExpandListener listener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(@NonNull MenuItem menuItem) {

                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(@NonNull MenuItem menuItem) {

                return true;
            }
        };
        Spinner spinner = (Spinner) menu.findItem(R.id.spine).getActionView();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Proprieter.getStringValues());
        lv.setLayoutManager(new LinearLayoutManager(this));
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                list.clear();

                String selectedValue = spinner.getSelectedItem().toString();
                ArrayList<Task> filteredTasks = Stokperson.search(selectedValue);


                list.addAll(convertTaskListToHashMapList(filteredTasks));


                a.notifyDataSetChanged();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        switch  (menu.getItemId()) {
            case R.id.list:
                intent =new Intent(getApplicationContext(), Liste_delete.class);
                startActivity(intent);
                return true;
            case R.id.about:
                Toast.makeText(this, "Option Save sélectionnée",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.spine:
                list.clear();

                Toast.makeText(this, "Option Supprimer sélectionnée",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:

                Toast.makeText(this, "Option Aide sélectionnée",
                        Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected( menu);
        }

    }


}