package com.example.serialbowl;

import static java.lang.String.valueOf;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StartFulfillment extends AppCompatActivity {

    private String orderNum;
    private NetSuiteAPIHelper NSAPI;
    private List<List<String>> VINsBySKU = new ArrayList<List<String>>();
    private String[][] lineItems;

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
        lineItems = NSAPI.getOrderLineItems(orderNum);
        LinearLayout lineItemsView = (LinearLayout) findViewById(R.id.orderLineItemsView);

        for (int i = 0; i < lineItems.length; i++) {
            // create horizontal view
            LinearLayout lineItem = new LinearLayout(StartFulfillment.this);
            lineItem.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lineItem.setOrientation(LinearLayout.HORIZONTAL);

            VINsBySKU.add(new ArrayList<String>());

            for (int j = 0; j < lineItems[i].length; j++) {
                // create text view
                TextView itemDescriptorTV = new TextView(StartFulfillment.this);
                itemDescriptorTV.setLayoutParams(new TableRow.LayoutParams(0, 100, 1f));
                itemDescriptorTV.setText(lineItems[i][j]);

                String targetSKU = lineItems[i][0];
                String numNeeded = lineItems[i][1];
                int lineNum = i;
                itemDescriptorTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inputVIN(targetSKU, numNeeded, lineNum);
                    }
                });

                lineItem.addView(itemDescriptorTV);
            }

            CheckedTextView vinStatus = new CheckedTextView(StartFulfillment.this);
            //vinStatus.setCheckMarkDrawable(R.drawable.checkmark);
            vinStatus.setCheckMarkDrawable(0);

            vinStatus.setChecked(false);
            //vinStatus.setLayoutParams(new TableRow.LayoutParams(24, 24));

            vinStatus.setId(i);

            lineItemsView.addView(lineItem);
            lineItemsView.addView(vinStatus);

        }
    }

    @Override
    protected void onActivityResult(int rq, int result, Intent data){
        super.onActivityResult(rq, result, data);

        if (result == Activity.RESULT_OK){
            CheckedTextView updated = (CheckedTextView) findViewById(rq);
            updated.setChecked(true);
            updated.setCheckMarkDrawable(R.drawable.checkmark);
            VINsBySKU.set(rq, (ArrayList<String>) data.getSerializableExtra("VINS"));
        }
    }

    private void inputVIN(String targetSKU, String num, int lineNum){

        Intent intent = new Intent(this, RetrieveVINS.class);
        intent.putExtra("SKU", targetSKU);
        intent.putExtra("NUM_NEEDED", num);

        startActivityForResult(intent, lineNum);
    }

    public void submitVINStoNS(View view){

        String[] skus = new String[lineItems.length];

        boolean isAllVINsReady = true;

        for (int i = 0; i < lineItems.length; i++) {
            skus[i] = lineItems[i][0];

            CheckedTextView isReadyTV = (CheckedTextView) findViewById(i);
            isAllVINsReady = isReadyTV.isChecked();
        }

        boolean isFulfilledbyNS = NSAPI.fulfillOrder(orderNum, skus, VINsBySKU);

        if (! isFulfilledbyNS) {
            Toast.makeText(StartFulfillment.this, "An Error Occurred!", Toast.LENGTH_LONG).show();
        }

        else if (! isAllVINsReady) {
            Toast.makeText(StartFulfillment.this, "Gather all VINs!", Toast.LENGTH_LONG).show();
        }

        else {
            Toast.makeText(StartFulfillment.this, "Order Fulfilled in NS!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}