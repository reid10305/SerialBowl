package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.fulfillOrdersMenuItem:
                showFulfillOrdersActivity();
                return true;

            case R.id.recieveContainersMenuItem:
                showRecieveContainerActivity();
                return true;

            case R.id.helpMenuItem:
                showHelpActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHelpActivity(){
        //todo
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);

    }

    public void showFulfillOrdersActivity(){
        //todo
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showRecieveContainerActivity(){
        //todo
        Intent intent = new Intent(this, RecieveContainers.class);
        startActivity(intent);
    }
}