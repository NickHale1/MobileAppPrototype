package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Percent2People extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent2_people);

        Bundle info = this.getIntent().getExtras();


        //Get all the EditTextFields
        TextView person1 = (TextView) findViewById(R.id.Person1);
        person1.setText("Me");
        EditText person1Percent = (EditText) findViewById(R.id.Percent1);

        EditText person2Name = (EditText) findViewById(R.id.Person2);
        EditText person2Percent = (EditText) findViewById(R.id.Percent2);
        TextView errorText = (TextView)findViewById(R.id.errorText);
        errorText.setText("");

        Button submitButton = (Button) findViewById(R.id.Percent2Submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person1Percent.getText().toString().equals("") || person2Percent.getText().toString().equals("") || person2Name.getText().toString().equals("") ) {
                    errorText.setText("Please ensure there is a value\nin all fields");
                } else {

                    float percent1 = Float.valueOf(person1Percent.getText().toString());
                    float percent2 = Float.valueOf(person2Percent.getText().toString());
                    if (percent1 + percent2 != 100) {
                        errorText.setText("Percents should add up to 100\nCurrent total = " + (percent1 + percent2));
                    } else {

                        info.putFloat("person1Percent", percent1);

                        info.putString("person2Name", person2Name.getText().toString());
                        info.putFloat("person2Percent", percent2);
                        Intent intent = new Intent();
                        intent.putExtras(info);
                        intent.setClass(Percent2People.this, Result2People.class);
                        startActivity(intent);
                    }
                }

            }
        });


    }
}