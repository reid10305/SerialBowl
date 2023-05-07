package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public NetSuiteAPIHelper NSAPI;
    private Spinner locationSpinner;
    private String SelectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NSAPI = new NetSuiteAPIHelper(null, null, null, null, null);

        locationSpinner = (Spinner) findViewById(R.id.LocationsSpinner);

        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                setTextViews();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });
        setTextViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void setTextViews(){
        // set textview values
        TextView denagoOrderCountTV = (TextView) findViewById(R.id.denagoOrdersCountTextView);
        TextView gotraxOrderCountTV = (TextView) findViewById(R.id.gotraxOrdersCountTextView);
        TextView amazonOrderCountTV = (TextView) findViewById(R.id.amazonOrdersCountTextView);
        TextView totalOrdersCountTV = (TextView) findViewById(R.id.TotalOrdersTextView);

        SelectedLocation = locationSpinner.getSelectedItem().toString();

        String denagoOrderCount = getNumOrders(getString(R.string.denagoOrdersTitle), SelectedLocation);
        String gotraxOrderCount = getNumOrders(getString(R.string.gotraxOrdersTitle), SelectedLocation);
        String amazonOrderCount = getNumOrders(getString(R.string.amazonOrdersTitle), SelectedLocation);
        String totalOrdersCount = "0 Orders to fulfill.";


        denagoOrderCountTV.setText(denagoOrderCount);
        gotraxOrderCountTV.setText(gotraxOrderCount);
        amazonOrderCountTV.setText(amazonOrderCount);
        totalOrdersCountTV.setText(totalOrdersCount);
    }

    private String getNumOrders(String channel, String location){
        return NSAPI.getNumOrdersByChannel(channel, location);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.fulfillOrdersMenuItem:
                showFulfillOrdersActivity();
                return true;

            case R.id.recieveContainersMenuItem:
                showRecieveContainerActivity();
                return true;

            case R.id.settingsMenuItem:
                showSettingsActivity();
                return true;

            case R.id.helpMenuItem:
                showHelpActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHelpActivity(){
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    public void showFulfillOrdersActivity(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void showRecieveContainerActivity(){
        Intent intent = new Intent(this, RecieveContainers.class);
        startActivity(intent);
    }

    public void showFulfillSelectionActivity(View view){
        Intent intent = new Intent(this, fulfillSelection.class);
        intent.putExtra("LOCATION", SelectedLocation);
        startActivity(intent);
    }

    public void showSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


}