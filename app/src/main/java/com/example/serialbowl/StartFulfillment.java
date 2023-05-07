package com.example.serialbowl;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
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
                String numNeeded = lineItems[i][1];

                itemDescriptorTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        inputVIN(targetSKU, numNeeded);
                    }
                });

                lineItem.addView(itemDescriptorTV);
            }

            CheckedTextView vinStatus = new CheckedTextView(StartFulfillment.this);
            //vinStatus.setCheckMarkDrawable(R.drawable.checkmark);
            vinStatus.setCheckMarkDrawable(0);

            vinStatus.setChecked(false);
            //vinStatus.setLayoutParams(new TableRow.LayoutParams(24, 24));

            lineItemsView.addView(lineItem);
            lineItemsView.addView(vinStatus);

        }
    }

    private void inputVIN(String targetSKU, String num){
        Intent intent = new Intent(this, RetrieveVINS.class);
        intent.putExtra("SKU", targetSKU);
        intent.putExtra("NUM_NEEDED", num);
        intent.putExtra("NSAPI", NSAPI);

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityRestulCallback<ActivityResult>()){
                    @Override
                    public void onActivityResult(ActivityResult result){

            }
        });
    }
}