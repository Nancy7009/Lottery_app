package com.example.lottery_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class homepage_java extends AppCompatActivity {
    TextInputEditText first_name, last_name, email;
    int lottery_type;
    Button confirm;
    SharedPreferences user_preferences;
    SharedPreferences.Editor user_File_editor;
    Intent intent, get_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        get_intent = getIntent();
        String lottery_number = get_intent.getExtras().getString("lottery_number", "");
        String generated_lottery = get_intent.getExtras().getString("generated_lottery");
        lottery_type = get_intent.getExtras().getInt("lottery_type");
        confirm =  (Button)findViewById(R.id.submit);
        first_name = (TextInputEditText) findViewById(R.id.first_name);
        last_name = (TextInputEditText) findViewById(R.id.last_name);
        email = (TextInputEditText) findViewById(R.id.email);
        // Initializing file
        user_preferences = PreferenceManager.getDefaultSharedPreferences(homepage_java.this);
        user_File_editor = user_preferences.edit();
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String first_nameTXT = first_name.getText().toString();
                String last_nameTXT = last_name.getText().toString();
                String emailTXT = email.getText().toString();
                if(first_nameTXT.isEmpty() || last_nameTXT.isEmpty() || emailTXT.isEmpty()){
                        alert("You need to fill all the fields!!");
                }
                else {
                    InsertData(first_nameTXT, last_nameTXT, emailTXT, lottery_number, generated_lottery);
                    String [] user_details = readData();
                    callActivity(user_details);
                }


            }
        });
    }

    public void InsertData(String first_name, String last_name, String email, String lottery_number, String generated_lottery){

        user_File_editor.putString("first_name", first_name);
        user_File_editor.putString("last_name", last_name);
        user_File_editor.putString("email", email);
        user_File_editor.putString("lottery_number", lottery_number);
        user_File_editor.putString("generated_lottery", generated_lottery);
        user_File_editor.commit();
        Log.d("Insert :", "Data Inserted successfully");
    }

    public String[] readData() {
        String first_name = user_preferences.getString("first_name", "unknown");
        String last_name = user_preferences.getString("last_name", "unknown");
        String email = user_preferences.getString("email", "unknown");
        String lottery_number = user_preferences.getString("lottery_number", "unknown");
        String generated_lottery = user_preferences.getString("generated_lottery","");
        String[] details = {first_name, last_name, email, lottery_number, generated_lottery};
        return details;
    }

    public void callActivity(String [] user_details){
        intent = new Intent(homepage_java.this, final_output.class);
        intent.putExtra("results",user_details);
        intent.putExtra("lottery_type", lottery_type);
        startActivity(intent);
        finish();
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