package com.example.serialbowl;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RetrieveVINS extends AppCompatActivity {
    private int reqInt;
    private int currentInt;
    TextView currentQty;
    String sku;
    ArrayList<String> VINsList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_vins);

        Intent req = getIntent();
        sku = req.getStringExtra("SKU");
        String qty = req.getStringExtra("NUM_NEEDED");
        String current = "0";


        TextView skuTitle = (TextView) findViewById(R.id.skuTitle);
        TextView qtyNeeded = (TextView) findViewById(R.id.qtyNeeded);
        currentQty = (TextView) findViewById(R.id.currentQty);

        String reqTitle = getString(R.string.required) + qty;
        String curTitle = getString(R.string.current) + current;

        qtyNeeded.setText(reqTitle);
        currentQty.setText(curTitle);
        skuTitle.setText(sku);

        reqInt = parseInt(qty);
        currentInt = parseInt(current);

    }

    public void addVIN(View view){
        Intent intent = new Intent(this, ScanVIN.class);
        intent.putExtra("SKU", sku);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int rq, int result, Intent data){
        super.onActivityResult(rq, result, data);

        if (result == Activity.RESULT_OK){
            TextView vinTV = new TextView(RetrieveVINS.this);
            String vin = data.getStringExtra("VIN");
            vinTV.setText(vin);


            LinearLayout vinList = (LinearLayout) findViewById(R.id.vinList);

            vinTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            vinTV.setAllCaps(true);
            vinTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            vinList.addView(vinTV);

            currentInt++;
            String current = getString(R.string.current) + valueOf(currentInt);
            currentQty.setText(current);

            VINsList.add(vin);
        }
    }

    public void trySubmit(View view) {
        if (reqInt == currentInt) {
            // return with result to startfulfillment activity
            Intent complete = new Intent();
            complete.putExtra("VINS", VINsList);
            setResult(Activity.RESULT_OK, complete);
            finish();
        }

        else {
            Toast.makeText(RetrieveVINS.this, "Not enough VINs!", Toast.LENGTH_LONG).show();
        }
    }

}