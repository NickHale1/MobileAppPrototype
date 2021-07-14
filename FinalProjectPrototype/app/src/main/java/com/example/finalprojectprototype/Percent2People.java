package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Percent2People extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent2_people);

        Bundle info = this.getIntent().getExtras();


        //Get all the EditTextFields

        EditText person1Percent = (EditText) findViewById(R.id.Percent2Percent1);

        EditText person2Name = (EditText) findViewById(R.id.Percent2Person2);
        EditText person2Percent = (EditText) findViewById(R.id.Percent2Percent2);

        Button submitButton = (Button) findViewById(R.id.Percent2Submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.putFloat("person1Percent",Float.valueOf(person1Percent.getText().toString()));

                info.putString("person2Name", person2Name.getText().toString());
                info.putFloat("person2Percent", Float.valueOf(person2Percent.getText().toString()));
                Intent intent = new Intent();
                intent.putExtras(info);
                intent.setClass(Percent2People.this, Result2People.class);
                startActivity(intent);

            }
        });


    }
}