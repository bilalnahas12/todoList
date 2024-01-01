package com.example.todo_liste.view;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.todo_liste.R;
import com.example.todo_liste.model.Proprieter;
import com.example.todo_liste.model.Task;
import com.example.todo_liste.stokPerson.Stokperson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View_task extends AppCompatActivity {
    EditText titale, tit, date_init, date_fin;
    ImageButton ibtn, ibtn2;
    Spinner spinner;
    FloatingActionButton fbtn;
    Task task;
    ArrayList<Task> tasks=new ArrayList<Task>();
    Serializable position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                Proprieter.getStringValues()
        );
        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);
        titale = findViewById(R.id.titale);
        tit = findViewById(R.id.description);
        date_init = findViewById(R.id.date_init);
        date_fin = findViewById(R.id.date_fin);
        ibtn = findViewById(R.id.imageButton);
        ibtn2 = findViewById(R.id.imageButton2);
        spinner = findViewById(R.id.spinner);
        fbtn = findViewById(R.id.fab);
        Intent intent = getIntent();
        position = getIntent().getSerializableExtra("index");
        if (position != null) {
            task = Stokperson.currentPerson.getTasks().get((int) position);
            titale.setText(task.getTitre());
            tit.setText(task.getDescription());
            date_init.setText(task.getDate_debut().toString());
            date_fin.setText(task.getDate_fin().toString());
            spinner.setSelection(priorityAdapter.getPosition(task.getPrp().toString()));
        }

        spinner.setAdapter(priorityAdapter);
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(View_task.this,R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
                                date_init.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + myear);
                            }
                        }, year, month, day);
                dpd.show();

            }
        }); ibtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(View_task.this,R.style.DialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int myear, int monthOfYear, int dayOfMonth) {
                                date_fin.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + myear);
                            }
                        }, year, month, day);
                dpd.show();

            }
        });

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position!=null){
                    Proprieter selectedPriority = Proprieter.valueOf(spinner.getSelectedItem().toString());
                    if (isDateInitBeforeDateFin()) {
                        task.setTitre(titale.getText().toString());
                        task.setDescription(tit.getText().toString());
                        task.setDate_debut(getDateFromString(date_init.getText().toString()));
                        task.setDate_fin(getDateFromString(date_fin.getText().toString()));
                        task.setPrp(selectedPriority);
                        Intent intent = new Intent(View_task.this, Page_index.class);
                        startActivity(intent);
                    }
                    else {
                        showToast("Veuillez vous assurer que la date de début est strictement antérieure à la date de fin.");
                    }
                }else {

                if (isAnyFieldEmpty(titale, tit, date_init, date_fin) || !isSpinnerItemSelected(spinner)) {
                    showToast("Please fill in all fields and select an item from the Spinner.");
                } else {
                    Proprieter selectedPriority = Proprieter.valueOf(spinner.getSelectedItem().toString());



                    task= new Task(
                            titale.getText().toString(),
                            tit.getText().toString(),
                            getDateFromString(date_init.getText().toString()),
                            getDateFromString(date_fin.getText().toString()),
                            selectedPriority
                    );
                    if (isDateInitBeforeDateFin()) {

                        Stokperson.add(task);
                        showToast("All fields are filled. Performing your action.");
                        Intent intent = new Intent(View_task.this, Page_index.class);
                        startActivity(intent);
                    }else {
                        showToast("Veuillez vous assurer que la date de début est strictement antérieure à la date de fin.");
                    }


                }
            }}
        });

    }

    private Date getDateFromString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            showToast("Error parsing date");
            return null;
        }
    }


    private boolean isAnyFieldEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText() == null || editText.getText().toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private boolean isSpinnerItemSelected(Spinner spinner) {
        return spinner.getSelectedItem() != null;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    private boolean isDateInitBeforeDateFin() {
        Date dateInit = getDateFromString(date_init.getText().toString());
        Date dateFin = getDateFromString(date_fin.getText().toString());

        return dateInit != null && dateFin != null && dateInit.before(dateFin);
    }

}
