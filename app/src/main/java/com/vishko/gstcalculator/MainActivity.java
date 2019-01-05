package com.vishko.gstcalculator;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,
                "ca-app-pub-9618673022809782/9913402901");

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final EditText priceT = findViewById(R.id.price);
        final TextView gstT = findViewById(R.id.gstA);
        final TextView pngstT = findViewById(R.id.pngstA);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String priceS = priceT.getText().toString();
                if (TextUtils.isEmpty(priceS)){
                    priceT.setError("Please fill this field!");
                } else {
                    Double price = Double.valueOf(priceT.getText().toString());

                    Double pngstD = (price * 100) / 106;
                    Double gstD = pngstD * 6 / 100;

                    Double pngstDR = round(pngstD, 2);
                    Double gstDR = round(gstD, 2);

                    String pngst = pngstDR.toString();
                    String gst = gstDR.toString();

                    gstT.setText(gst);
                    pngstT.setText(pngst);
                }
            }
        });

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
