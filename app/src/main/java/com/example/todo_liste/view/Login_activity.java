package com.example.todo_liste.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo_liste.R;
import com.example.todo_liste.stokPerson.Stokperson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_activity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;
    private Button facebookButton;
    private Button googleButton;
    private TextView registerTextView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextTextEmail);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.button);
        facebookButton = findViewById(R.id.button2);
        googleButton = findViewById(R.id.buttonGoogle);
        registerTextView = findViewById(R.id.textView3);

        loginButton.setOnClickListener(v -> {
            if (!isAnyFieldEmpty(editTextEmail, editTextPassword)) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (Stokperson.isverify(email, password)) {
                    intent=new Intent(getApplicationContext(), Page_index.class);
                    startActivity(intent);
                }

                Toast.makeText(Login_activity.this, "Login successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Login_activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }

        });

        facebookButton.setOnClickListener(v -> {

        });

        googleButton.setOnClickListener(v -> {

        });

        registerTextView.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), Signup_activity.class);
            startActivity(intent);
        });
    }
    private boolean isAnyFieldEmpty(EditText... editTexts) {
        for (EditText editText : editTexts) {
            if (editText.getText() == null || editText.getText().toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
