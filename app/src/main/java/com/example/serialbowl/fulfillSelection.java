package com.example.serialbowl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class fulfillSelection extends MainActivity {
    private String Location;
    private NetSuiteAPIHelper NSAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulfill_selection);

        Spinner channelSpinner = (Spinner) findViewById(R.id.ChannelSpinner);

        Intent intent = getIntent();
        Location = intent.getStringExtra("LOCATION");
        NSAPI = (NetSuiteAPIHelper) intent.getSerializableExtra("NSAPI");


        ArrayAdapter<CharSequence> channelAdapter = ArrayAdapter.createFromResource(this, R.array.channels, android.R.layout.simple_spinner_item);
        channelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        channelSpinner.setAdapter(channelAdapter);

    }

    public void showSelectedOrdersActivity(View view){
        Intent intent = new Intent(this, showSelectedOrders.class);
        Spinner channelSpinner = (Spinner) findViewById(R.id.ChannelSpinner);

        String channel = channelSpinner.getSelectedItem().toString();

        intent.putExtra("CHANNEL", channel);
        intent.putExtra("LOCATION", Location);
        intent.putExtra("NSAPI", NSAPI);

        startActivity(intent);

    }
}