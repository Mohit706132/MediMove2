package com.example.medimove;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUp extends AppCompatActivity {
    EditText signupName,signupEmail,signupUsername,signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    String name,email,username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        signupName =findViewById(R.id.signup_name);
        signupEmail =findViewById(R.id.signup_email);
        signupUsername =findViewById(R.id.signup_username);
        signupPassword =findViewById(R.id.signup_password);
        signupButton =findViewById(R.id.signup_button);
        loginRedirectText =findViewById(R.id.log_in_Redirect_Text);

//        String name,email,username,password;

        signupButton.setOnClickListener(v -> {
            database = FirebaseDatabase.getInstance();
            reference =database.getReference("users");

//            String name =signupName.getText().toString();
//            String email =signupEmail.getText().toString();
//            String username =signupUsername.getText().toString();
//            String password =signupPassword.getText().toString();
             name = signupName.getText().toString();
             email = signupEmail.getText().toString();
             username = signupUsername.getText().toString();
             password = signupPassword.getText().toString();

           HelperClass helperClass = new HelperClass(name,email,username,password);
           reference.child(username).setValue(helperClass);

            Toast.makeText(SingUp.this,"You have signup Successfully!!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SingUp.this, Log.class);
            startActivity(intent);
        });

        loginRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(SingUp.this,LogIn.class);
            startActivity(intent);
        });
    }
}