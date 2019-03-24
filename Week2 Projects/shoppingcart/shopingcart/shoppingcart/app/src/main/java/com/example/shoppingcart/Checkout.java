package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Checkout extends AppCompatActivity {
    String totalPrice;
    TextView tp;
    Button exit, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        tp = findViewById(R.id.totalPrice);
        exit = findViewById(R.id.exit);
        home = findViewById(R.id.Home);

        Intent i = getIntent();
        if(i.hasExtra("tp")){
            totalPrice = "Total Price is  ";
            totalPrice += i.getStringExtra("tp");
            totalPrice+="\n Thanks for shopping with us";
            tp.setText(totalPrice);
        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Checkout.this, productsInformation.class);
                startActivity(i);
            }
        });


    }
}
