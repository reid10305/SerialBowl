package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class StartFulfillment extends AppCompatActivity {

    private String orderNum;
    private NetSuiteAPIHelper NSAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_fulfillment);

        Intent intent = getIntent();
        orderNum = intent.getStringExtra("ORDER_NUM");
        NSAPI = (NetSuiteAPIHelper) intent.getSerializableExtra("NSAPI");

        TextView orderNumTitleTV = (TextView) findViewById(R.id.orderNumTitle);
        orderNumTitleTV.setText(orderNum);

        showLineItems();
    }

    private void showLineItems(){
        String[][] lineItems = NSAPI.getOrderLineItems(orderNum);
        LinearLayout lineItemsView = (LinearLayout) findViewById(R.id.orderLineItemsView);

        for (int i = 0; i < lineItems.length; i++) {
            // create horizontal view
            LinearLayout lineItem = new LinearLayout(StartFulfillment.this);
            lineItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lineItem.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < lineItems[i].length; j++) {
                // create text view
                TextView itemDescriptorTV = new TextView(StartFulfillment.this);
                itemDescriptorTV.setLayoutParams(new TableRow.LayoutParams(0, 100, 1f));
                itemDescriptorTV.setText(lineItems[i][j]);

                String targetSKU = lineItems[i][0];

                itemDescriptorTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inputVIN(targetSKU);
                    }
                });

                lineItem.addView(itemDescriptorTV);
            }

            lineItemsView.addView(lineItem);

        }
    }

    private void inputVIN(String targetSKU){

    }
}