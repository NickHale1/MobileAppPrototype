package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BillInfo extends AppCompatActivity {
    int numPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);

        EditText editTip = (EditText)findViewById(R.id.editTip);
        EditText editTotal = (EditText) findViewById(R.id.editTotal);
        TextView errorText = (TextView) findViewById(R.id.errorText);
        errorText.setText("");
        //EditText editNumPeople = (EditText) findViewById(R.id.editNumPeople);


        Button submit = (Button) findViewById(R.id.submitBillInfo);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        adapter.add("2");
        adapter.add("3");
        adapter.add("4");
        Spinner selector = (Spinner)findViewById(R.id.numPeopleSelector);
        selector.setAdapter(adapter);
        selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    numPeople=position+2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTip.getText().toString().equals("") ||editTotal.getText().toString().equals("")) {
                    errorText.setText("Please ensure there is a value\nin all fields");
                } else {



                    float tip = Float.valueOf(editTip.getText().toString());
                    float total = Float.valueOf(editTotal.getText().toString());

                    if(total==0) {
                        errorText.setText("Please enter a total");
                        //in    t numPeople = Integer.valueOf(editNumPeople.getText().toString());

                    } else {

                        Intent intent = new Intent();

                        Bundle info = new Bundle();
                    //add all the info from above
                        info.putFloat("tipPercent", tip);
                        info.putFloat("total", total);

                        //Just doing 2 people for testing purposes
                        if (numPeople == 2) {
                            intent.setClass(BillInfo.this, Percent2People.class);
                        } else if (numPeople == 3) {
                            intent.setClass(BillInfo.this, Percent3People.class);
                        } else if (numPeople == 4) {
                            intent.setClass(BillInfo.this, Percent4People.class);

                        }
                    intent.putExtras(info);
                    startActivity(intent);
                    }
                }
            }
        });
    }
}