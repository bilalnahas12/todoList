package com.example.todo_liste.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.todo_liste.R;

public class MainActivity extends AppCompatActivity {

    private Button signUpButton;
    private Intent intent;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpButton = findViewById(R.id.button6);
        loginButton = findViewById(R.id.button7);

        signUpButton.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), Signup_activity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), Login_activity.class);
            startActivity(intent);
        });
    }
}
