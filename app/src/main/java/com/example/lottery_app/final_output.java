package com.example.lottery_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class final_output extends AppCompatActivity {
    TextView winning_number, display_name, email, user_result;
    Button playAgain;
    Intent intent;
    int lottery_type;
    int amount = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_output);
        intent = getIntent();
        lottery_type = intent.getExtras().getInt("lottery_type");
        winning_number = (TextView) findViewById(R.id.winnig_number);
        playAgain= (Button) findViewById(R.id.play_again);
        display_name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email_id);
        user_result = (TextView)findViewById(R.id.user_ticket);
        Intent  get_intent = getIntent();
        String [] user_details = get_intent.getExtras().getStringArray("results");
        String name, email_id, lottery_number, generated_lottery;
        name = user_details[0] + " " +  user_details[1];
        email_id = user_details[2];
        lottery_number = user_details[3];
        generated_lottery = user_details[4];

        if(lottery_number.equals(generated_lottery)){

            if(lottery_type == 1){
                amount = 100000;
            }
            else if(lottery_type == 2){
                amount = 50000;
            }
            else if(lottery_type == 3){
                amount = 10000;
            }
            alert("Congratulations On Winning $$$"+ amount +"$$$!!");
        }
        else{
            alert("Better Luck Next time.");
        }
        winning_number.setText(generated_lottery);
        user_result.setText(lottery_number);
        email.setText(email_id);
        display_name.setText(name);

    playAgain.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            intent = new Intent(final_output.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    });
    }

    public  void alert(String message){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
