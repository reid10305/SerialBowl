package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.ServiceConnectionLeakedViolation;
import android.widget.TextView;

public class showSelectedOrders extends MainActivity {
    private NetSuiteAPIHelper NSAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selected_orders);

        Intent intent = getIntent();
        String Location = intent.getStringExtra("LOCATION");
        String channel = intent.getStringExtra("CHANNEL");
        NSAPI = (NetSuiteAPIHelper) intent.getSerializableExtra("NSAPI");

        TextView description = (TextView) findViewById(R.id.ordersDescriptionTextView);
        description.setText(Location + ":" + channel);



        String[] resultOrders = getOrdersFromNS(Location, channel);


    }

    private String[] getOrdersFromNS(String location, String channel){
        //todo
        return null;
    }
}