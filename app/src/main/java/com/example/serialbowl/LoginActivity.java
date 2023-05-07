package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Login(View view){
        //todo
        EditText emailET = (EditText) findViewById(R.id.userNameEditText);
        EditText passwordET = (EditText) findViewById(R.id.passwordEditText);

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        if (email.equals("admin") && password.equals("password")){
            continueToMain();
            Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(LoginActivity.this, "Invalid Login!", Toast.LENGTH_LONG).show();
        }
    }

    private void continueToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}