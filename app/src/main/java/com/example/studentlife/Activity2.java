package com.example.studentlife;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Activity2 extends AppCompatActivity {
    DatabaseMood std_mood;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        std_mood = new DatabaseMood(this);
        radioGroup = (RadioGroup) findViewById(R.id.radio);
        btnDisplay = (Button) findViewById(R.id.mood);
        AddData();
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void AddData() {
        btnDisplay.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        radioButton = (RadioButton) findViewById(selectedId);
                        String time = getDateTime();
                        boolean is_inserted =  std_mood.insertDataMood(time, radioButton.getText().toString().trim());
                        if(is_inserted == true){
                            Toast.makeText(Activity2.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(Activity2.this, "Data not inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}
