package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class UpdateTab extends AppCompatActivity {
    int currentSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tab);

        Bundle bundle = this.getIntent().getExtras();

        String[] names = bundle.getStringArray("currentNames");
        float[] amounts = bundle.getFloatArray("currentAmounts");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);
        for(int i=0;i<names.length;i++) {
            adapter.add(names[i]);
        }
        Spinner selector = (Spinner)findViewById(R.id.NameSelector);
        selector.setAdapter(adapter);
        TextView selectedAmount = (TextView)findViewById(R.id.currentAmountOwed);
        Button confirm = (Button) findViewById(R.id.confirmUpdate);
        Button back = (Button) findViewById(R.id.backBtn);
        TextView amountpaid = (TextView)findViewById(R.id.amountPaid);

        selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSelection = position;
                selectedAmount.setText("Currently owes: " + amounts[currentSelection]);
                selectedAmount.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getApplicationContext();
                FileSave fs = new FileSave(context);
                amounts[currentSelection] = amounts[currentSelection] - Float.valueOf(amountpaid.getText().toString());
                //if all paid remove from list
                if(amounts[currentSelection]<=0){
                    String[] copyNames = new String[names.length - 1];
                    float[] copyAmounts = new float[amounts.length - 1];

                    for (int i = 0, j = 0; i < names.length; i++) {
                        if (i != currentSelection) {
                            copyNames[j] = names[i];
                            copyAmounts[j] = amounts[i];
                            j++;
                        }
                    }
                    String forFile = SaveManager.convertToFileFormat(copyNames, copyAmounts);
                    fs.fileSave("MyTabs",context,forFile,null,null,null,null);


                } else {
                    String forFile = SaveManager.convertToFileFormat(names,amounts);
                    fs.fileSave("MyTabs",context,forFile,null,null,null,null);
                }


                Intent intent = new Intent();
                intent.setClass(UpdateTab.this,Tabs.class);
                startActivity(intent);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UpdateTab.this,Tabs.class);
                startActivity(intent);

            }
        });



    }


}