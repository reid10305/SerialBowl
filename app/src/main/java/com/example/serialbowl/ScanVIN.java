package com.example.serialbowl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ScanVIN extends AppCompatActivity {
    String reqSKU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_vin);

        Intent req = getIntent();
        reqSKU = req.getStringExtra("SKU");

    }

    public void trySubmit(View view){
        EditText skuET = (EditText) findViewById(R.id.skuET);
        EditText vinET = (EditText) findViewById(R.id.vinET);

        String skuInput = skuET.getText().toString();

        if (skuInput.equals(reqSKU)) {
            // return to retrievevins activity
            Intent complete = new Intent();
            complete.putExtra("VIN", vinET.getText().toString());
            setResult(Activity.RESULT_OK, complete);
            finish();
        }

        else {
            Toast.makeText(ScanVIN.this, "SKU Mismatch!", Toast.LENGTH_LONG).show();
        }
    }
}