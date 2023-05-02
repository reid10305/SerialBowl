package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class fulfillSelection extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulfill_selection);

        Spinner locationsSpinner = (Spinner) findViewById(R.id.locationSpinner);

        ArrayAdapter<CharSequence> locationsAdapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);
        locationsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationsSpinner.setAdapter(locationsAdapter);

        Spinner channelSpinner = (Spinner) findViewById(R.id.channelSpinner);

        ArrayAdapter<CharSequence> channelAdapter = ArrayAdapter.createFromResource(this, R.array.channels, android.R.layout.simple_spinner_item);
        channelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        channelSpinner.setAdapter(channelAdapter);

    }

    public void showSelectedOrdersActivity(View view){
        Intent intent = new Intent(this, showSelectedOrders.class);
        Spinner locationsSpinner = (Spinner) findViewById(R.id.locationSpinner);
        Spinner channelSpinner = (Spinner) findViewById(R.id.channelSpinner);

        String location = locationsSpinner.getSelectedItem().toString();
        String channel = channelSpinner.getSelectedItem().toString();

        intent.putExtra("LOCATION", location);
        intent.putExtra("CHANNEL", channel);

        startActivity(intent);

    }
}