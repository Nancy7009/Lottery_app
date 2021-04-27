package com.example.lottery_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirm_purchase extends AppCompatActivity {
    // variables
    TextView lottery_amount, timespan;
    Button confirm;
    int lottery_type;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_purchase);
        Intent intent = getIntent();
        confirm = (Button) findViewById(R.id.confirm_purchase);
        lottery_amount = (TextView) findViewById(R.id.lottery_amount);
        timespan = (TextView) findViewById(R.id.timespan);
        lottery_amount.setText(intent.getExtras().getString("lottery_amount",""));
        timespan.setText(intent.getExtras().getString("timespan", ""));
        lottery_type = intent.getExtras().getInt("lottery_type");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivity();
            }
        });
    }
    public void callActivity(){
        intent = new Intent(confirm_purchase.this, pickLottery_number.class);
        intent.putExtra("lottery_type",lottery_type);
        startActivity(intent);
        finish();
    }
}