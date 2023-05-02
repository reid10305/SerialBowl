package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class showSelectedOrders extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selected_orders);

        Intent intent = getIntent();

        String location = intent.getStringExtra("LOCATION");
        String channel = intent.getStringExtra("CHANNEL");

        TextView description = (TextView) findViewById(R.id.ordersDescriptionTextView);
        description.setText(location + ":" + channel);

        String[] resultOrders = getOrdersFromNS(location, channel);


    }

    private String[] getOrdersFromNS(String location, String channel){
        //todo
        return null;
    }
}