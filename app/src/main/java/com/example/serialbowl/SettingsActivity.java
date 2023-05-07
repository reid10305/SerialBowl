package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SettingsActivity extends MainActivity {
    private NetSuiteAPIHelper NSAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        NSAPI = (NetSuiteAPIHelper) intent.getSerializableExtra("NSAPI");
    }

    public void saveAPIChanges(View view){
        //todo
        returnHome();
    }

    public void Logout(View view){
        Toast.makeText(SettingsActivity.this, "Logged out!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void returnHome(){
        Toast.makeText(SettingsActivity.this, "Settings saved!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}