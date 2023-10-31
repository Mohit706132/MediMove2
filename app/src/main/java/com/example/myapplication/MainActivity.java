package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.AlarmClock;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText UserName, Pass;
    Button SignIn, LogIn;
    SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName = findViewById(R.id.editTextTextEmailAddress);
        Pass = findViewById(R.id.editTextTextPassword);
        SignIn = findViewById(R.id.button);
        LogIn = findViewById(R.id.button2);

        sql = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        sql.execSQL("CREATE TABLE IF NOT EXISTS Login_Details(Username VARCHAR,Password VARCHAR);");

        SignIn.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        if (view == SignIn) {
            if (UserName.getText().toString().length() == 0 || Pass.getText().toString().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            sql.execSQL("INSERT INTO Login_Details VALUES('" + UserName.getText() + "','" + Pass.getText() + "');");
            showMessage("Success", "Record added");
            clearText();
        }

    }

//        SignIn.setOnClickListener(view ->{
//                        Intent I = new Intent(MainActivity.this,MainActivity2.class);
//                    String user_name=UserName.getText().toString();
//
//                }
//                );

    public void showMessage(String title, String message) {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText() {
        UserName.setText("");
        Pass.setText("");
        UserName.requestFocus();
    }
}