package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Result3People extends AppCompatActivity {

    String fileName = "MyTabs";

    private static DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result3_people);

        Bundle info = this.getIntent().getExtras();
        float tip = info.getFloat("tipPercent");
        float total = info.getFloat("total");

        float product = ((tip/100) * total) + total;

        TextView person1 = (TextView) findViewById(R.id.Result3Person1);
        TextView person2 = (TextView) findViewById(R.id.Result3Person2);
        TextView person3 = (TextView) findViewById(R.id.Result3Person3);

        TextView amount1 = (TextView) findViewById(R.id.Result3Amount1);
        TextView amount2 = (TextView) findViewById(R.id.Result3Amount2);
        TextView amount3 = (TextView) findViewById(R.id.Result3Amount3);

        person1.setText("You");
        float person1Amount = (info.getFloat("person1Percent")/100) * product;
        amount1.setText("$" + df.format(person1Amount));


        person2.setText(info.getString("person2Name"));
        float person2Amount = (info.getFloat("person2Percent")/100) * product;
        amount2.setText("$" + df.format(person2Amount));

        person3.setText(info.getString("person3Name"));
        float person3Amount = (info.getFloat("person3Percent")/100) * product;
        amount3.setText("$" + df.format(person3Amount));

        Button confirm =(Button) findViewById(R.id.Confirm3);
        String[] newNames = {info.getString("person2Name"),info.getString("person3Name")};
        float[] newAmounts = {person2Amount,person3Amount};

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get current save data
                //add our information
                //resave the data
                //return to home screen
                Context context = getApplicationContext();
                FileSave fs = new FileSave(context);
                String[] oldNames=new String[0];
                float [] oldAmounts= new float[0];
                //get current info
                String rawFile = fs.fileRead(fileName,null,null,null);
                if(!rawFile.equals("")){
                    SaveManager.processData(rawFile);
                    oldNames = SaveManager.getNames();
                    oldAmounts = SaveManager.getAmounts();
                }
                //add our info
                for(int i =0; i< newNames.length;i++){
                    String s = newNames[i];
                    boolean found = false;
                    for(int j =0; j<oldNames.length; j++){

                        if(!found){
                            if(oldNames[j].equals(s)){
                                oldAmounts[j] = oldAmounts[j] + newAmounts[i];
                                found = true;
                            }
                        }
                    }
                    if(!found){

                        oldNames = Arrays.copyOf(oldNames, oldNames.length+1);
                        oldAmounts = Arrays.copyOf(oldAmounts, oldAmounts.length+1);
                        oldNames[oldNames.length-1] = s;
                        oldAmounts[oldAmounts.length-1] = newAmounts[i];
                    }


                }
                //save to file
                String forFile = SaveManager.convertToFileFormat(oldNames,oldAmounts);
                fs.fileSave(fileName,context,forFile,null,null,null,null);
                //return home
                Intent intent = new Intent();
                intent.setClass(Result3People.this, MainActivity.class);
                startActivity(intent);




            }
        });
    }
}