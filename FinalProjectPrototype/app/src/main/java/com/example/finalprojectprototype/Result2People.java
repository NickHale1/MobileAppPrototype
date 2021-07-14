package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Result2People extends AppCompatActivity {

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2_people);

        Bundle info = this.getIntent().getExtras();
        float tip = info.getFloat("tipPercent");
        float total = info.getFloat("total");

        float product = ((tip/100) * total) + total;

        TextView person1 = (TextView) findViewById(R.id.Result2Person1);
        TextView person2 = (TextView) findViewById(R.id.Result2Person2);

        TextView amount1 = (TextView) findViewById(R.id.Result2Amount1);
        TextView amount2 = (TextView) findViewById(R.id.Result2Amount2);

        person1.setText("You");
        float person1Amount = (info.getFloat("person1Percent")/100) * product;
        amount1.setText("$" + df.format(person1Amount));


        person2.setText(info.getString("person2Name"));
        float person2Amount = (info.getFloat("person2Percent")/100) * product;
        amount2.setText("$" + df.format(person2Amount));




    }
}