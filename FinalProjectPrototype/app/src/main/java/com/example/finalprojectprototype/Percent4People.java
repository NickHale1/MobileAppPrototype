package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Percent4People extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent4_people);


        Bundle info = this.getIntent().getExtras();


        EditText person1Percent = (EditText) findViewById(R.id.Percent1);
        TextView person1 = (TextView) findViewById(R.id.Person1);
        person1.setText("Me");

        EditText person2Name = (EditText) findViewById(R.id.Person2);
        EditText person2Percent = (EditText) findViewById(R.id.Percent2);

        EditText person3Name = (EditText) findViewById(R.id.Person3);
        EditText person3Percent = (EditText) findViewById(R.id.Percent3);

        EditText person4Name = (EditText) findViewById(R.id.Person4);
        EditText person4Percent = (EditText) findViewById(R.id.Percent4);
        TextView errorText = (TextView)findViewById(R.id.errorText);
        errorText.setText("");

        Button submitButton = (Button) findViewById(R.id.Submit4);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person1Percent.getText().toString().equals("") || person2Percent.getText().toString().equals("") || person3Percent.getText().toString().equals("")
                 ||person4Percent.getText().toString().equals("")|| person2Name.getText().toString().equals("") || person3Name.getText().toString().equals("") || person4Name.getText().toString().equals("")) {
                    errorText.setText("Please ensure there is a value\nin all fields");
                } else {

                    float percent1, percent2, percent3, percent4;
                    percent1 = Float.valueOf(person1Percent.getText().toString());
                    percent2 = Float.valueOf(person2Percent.getText().toString());
                    percent3 = Float.valueOf(person3Percent.getText().toString());
                    percent4 = Float.valueOf(person4Percent.getText().toString());
                    if(percent1+percent2+percent3+percent4 !=100) {
                        errorText.setText("Percents should add up to 100\nCurrent total = " + (percent1+percent2+percent3+percent4));
                    } else {

                        info.putFloat("person1Percent", percent1);

                        info.putString("person2Name", person2Name.getText().toString());
                        info.putFloat("person2Percent", percent2);

                        info.putString("person3Name", person3Name.getText().toString());
                        info.putFloat("person3Percent", percent3);

                        info.putString("person4Name", person4Name.getText().toString());
                        info.putFloat("person4Percent", percent4);


                        Intent intent = new Intent();
                        intent.putExtras(info);
                        intent.setClass(Percent4People.this, Result4People.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}