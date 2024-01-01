package com.example.todo_liste.view;
import static com.example.todo_liste.view.Login_activity.isValidEmail;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.todo_liste.R;
import com.example.todo_liste.model.Person;
import com.example.todo_liste.stokPerson.Stokperson;
public class Signup_activity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button signUpButton;
    private TextView forgetPasswordTextView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = findViewById(R.id.editTextTextName);
        editTextEmail = findViewById(R.id.editTextText);
        editTextPassword = findViewById(R.id.editTextText2);
        signUpButton = findViewById(R.id.button3);
        forgetPasswordTextView = findViewById(R.id.textView5);

        signUpButton.setOnClickListener(v -> {
            if (!isAnyFieldEmpty(editTextName, editTextEmail, editTextPassword)) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                if (isValidEmail(email)) {
                    if (!Stokperson.isUserExists(email)) {
                        Stokperson.isadd(new Person(name, email, password));
                        intent = new Intent(getApplicationContext(), Login_activity.class);
                        startActivity(intent);
                        Toast.makeText(Signup_activity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Signup_activity.this, "User with this email already exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Signup_activity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Signup_activity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }

        });

        forgetPasswordTextView.setOnClickListener(v -> {
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
}
