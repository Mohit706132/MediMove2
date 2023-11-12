package com.example.medimove;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LogIn extends AppCompatActivity {
     EditText loginusername,loginPassword;
     Button loginButton;
     TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginusername =findViewById(R.id.login_username);
        loginPassword =findViewById(R.id.login_password);
        signupRedirectText =findViewById(R.id.Sign_up_Redirect_Text);
        loginButton = findViewById(R.id.login_button);

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LogIn.this,SingUp.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateusername() | !validatePassword())
                {

                }
                else
                {
                    checkUser();
                }
            }
        });

    }
    public Boolean validateusername()
    {
        String val =loginusername.getText().toString();
        if(val.isEmpty())
        {
            loginusername.setError("Username cannot be empty");
            return  false;
        }
        else
        {
            loginusername.setError(null);
            return  true;
        }
    }

    public Boolean validatePassword()
    {
        String val =loginPassword.getText().toString();
        if(val.isEmpty())
        {
            loginPassword.setError("Password cannot be empty");
            return  false;
        }
        else
        {
            loginPassword.setError(null);
            return  true;
        }
    }

    public void checkUser()
    {
        String userUsername =loginusername.getText().toString().trim();
        String userPassword =loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    loginusername.setError(null);
                    String passwordFromDB =snapshot.child(userUsername).child("password").getValue(String.class);
                     if(passwordFromDB.equals(userPassword))
                     {
                         loginusername.setError(null);
                         Intent intent =new Intent(LogIn.this,MainActivity.class);
                         startActivity(intent);
                     }
                     else
                     {
                         loginPassword.setError("Invalid Credentials");
                         loginPassword.requestFocus();
                     }
                }
                else {
                    loginusername.setError("User does not exist");
                    loginusername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}