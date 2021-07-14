package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BillInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);

        EditText editTip = (EditText)findViewById(R.id.editTip);
        EditText editTotal = (EditText) findViewById(R.id.editTotal);
        EditText editNumPeople = (EditText) findViewById(R.id.editNumPeople);

        Button submit = (Button) findViewById(R.id.submitBillInfo);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float tip = Float.valueOf(editTip.getText().toString());
                float total = Float.valueOf(editTotal.getText().toString());
                int numPeople = Integer.valueOf(editNumPeople.getText().toString());

                Intent intent = new Intent();

                Bundle info = new Bundle();
                //add all the info from above
                info.putFloat("tipPercent", tip);
                info.putFloat("total", total);

                //Just doing 2 people for testing purposes
                intent.setClass(BillInfo.this, Percent2People.class);


                //switch statement to choose the next activity based off of number of people
                /*
                switch (numPeople){
                    case 2: intent.setClass(BillInfo.this, Percent2People.class);
                    case 3: intent.setClass(BillInfo.this, Percent3People.class);
                }

                 */

                //None of this info is used in the next activity but it needs to be carried forward for final calculations
                //launch the next activity
                intent.putExtras(info);
                startActivity(intent);
            }
        });
    }
}