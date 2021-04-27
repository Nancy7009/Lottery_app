package com.example.lottery_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ImageButton lottery1, lottery2, lottery3;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lottery1 = (ImageButton) findViewById(R.id.lottery1);
        lottery2 = (ImageButton) findViewById(R.id.lottery2);
        lottery3 = (ImageButton) findViewById(R.id.lottery3);
        lottery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_ConfirmLotteryPage("100000", "3 MONTHS", 1);

            }
        });

        lottery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_ConfirmLotteryPage("50000", "1 MONTH", 2);
            }
        });

        lottery3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call_ConfirmLotteryPage("10000", "1 WEEK", 3);
            }
        });
    }

    public void call_ConfirmLotteryPage(String lottery_amount, String timespan, int value){
        intent = new Intent(MainActivity.this, confirm_purchase.class);
        intent.putExtra("lottery_amount", lottery_amount);
        intent.putExtra("timespan", timespan);
        intent.putExtra("lottery_type", value);
        startActivity(intent);
        finish();
    }

}
