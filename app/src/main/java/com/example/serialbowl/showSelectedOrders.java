package com.example.serialbowl;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.ServiceConnectionLeakedViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class showSelectedOrders extends MainActivity {
    private NetSuiteAPIHelper NSAPI;
    private String Location;
    private String channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selected_orders);

        Intent intent = getIntent();
        Location = intent.getStringExtra("LOCATION");
        channel = intent.getStringExtra("CHANNEL");
        NSAPI = (NetSuiteAPIHelper) intent.getSerializableExtra("NSAPI");

        TextView description = (TextView) findViewById(R.id.ordersDescriptionTextView);
        description.setText(Location + ":" + channel);

        showOrders();

    }

    private String[][] getOrdersFromNS(String location, String channel){
        //todo
        return NSAPI.queryNS("SalesOrd", channel, location);
    }

    private void showOrders(){
        String[][] resultOrders = getOrdersFromNS(Location, channel);

        LinearLayout ordersView = (LinearLayout) findViewById(R.id.ordersView);

        for (int i = 0; i < resultOrders.length; i++) {
            // create horizontal view
            LinearLayout orderLine = new LinearLayout(showSelectedOrders.this);
            orderLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            orderLine.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < resultOrders[i].length; j++) {
                // create text view
                TextView orderDescriptorTV = new TextView(showSelectedOrders.this);
                orderDescriptorTV.setLayoutParams(new TableRow.LayoutParams(0, 100, 1f));
                orderDescriptorTV.setText(resultOrders[i][j]);

                String orderNum = resultOrders[i][0];

                orderDescriptorTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startFulfillment(orderNum);
                    }
                });

                orderLine.addView(orderDescriptorTV);
            }

            ordersView.addView(orderLine);

        }
    }

    public void startFulfillment(String orderNum){
        Intent intent = new Intent(this, StartFulfillment.class);
        intent.putExtra("ORDER_NUM", orderNum);
        intent.putExtra("NSAPI", NSAPI);

        startActivity(intent);
    }
}