package com.example.lottery_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class pickLottery_number extends AppCompatActivity {
    TextView number1, number2, number3, number4, number5;
    Button confirm;
    int lottery_type;
    Intent intent;
    String generated_lotterynumber;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        generated_lotterynumber = generateLotteryNumber();
        lottery_type = intent.getExtras().getInt("lottery_type");
        setContentView(R.layout.pick_lottery_number);
        number1 = (TextView) findViewById(R.id.number1);
        number1.setFilters(new InputFilter[]{ new limit_input("0", "9")});
        number2 = (TextView) findViewById(R.id.number2);
        number3 = (TextView) findViewById(R.id.number3);
        number4 = (TextView) findViewById(R.id.number4);
        number5 = (TextView) findViewById(R.id.number5);
        confirm = (Button) findViewById(R.id.confirm);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = number1.getText().toString();
                String num2 = number2.getText().toString();
                String num3 = number3.getText().toString();
                String num4 = number4.getText().toString();
                String num5 = number5.getText().toString();
                if( num1.isEmpty() || num2.isEmpty() || num3.isEmpty() || num4.isEmpty() || num5.isEmpty()){
                    alert("Some values are not entered!!");
                }
                else {


                    String lottery_number = num1 + num2 + num3 + num4 + num5;
                    call_UserDetailsPage(lottery_number);
                }
            }
        });
    }

    public void call_UserDetailsPage(String  lottery_number){

        intent = new Intent(pickLottery_number.this, homepage_java.class);
        intent.putExtra("lottery_number", lottery_number);
        intent.putExtra("generated_lottery", generated_lotterynumber);
        intent.putExtra("lottery_type", lottery_type);
        startActivity(intent);
        finish();
    }

    public String generateLotteryNumber(){
        Random random = new Random();
        String lottery_number = "";
        for (int i = 0; i < 5; i++){
            lottery_number = lottery_number + random.nextInt(10);
        }
        lottery_number = lottery_number.trim();
        Log.d("Lottery number", lottery_number);
        return lottery_number;
    }

    public  void alert(String message){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        builder1.setMessage(message);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alert11.show();
    }
}