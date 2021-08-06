package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Tabs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        FileSave fs = new FileSave(getApplicationContext());
        String[] names = new String[0];
        float[] amounts = new float[0];
        String rawFile = fs.fileRead("MyTabs",null,null,null);
        if(!rawFile.equals("")){
            SaveManager.processData(rawFile);
            names = SaveManager.getNames();
            amounts = SaveManager.getAmounts();
        }
        List<TabItem> list = new ArrayList<TabItem>();
        for(int i=0;i<names.length;i++){
            TabItem item = new TabItem();
            item.name = names[i];
            item.amount = amounts[i];
            list.add(item);
        }
        TabItemAdapter adapter;
        adapter = new TabItemAdapter(this,0,list);

        ListView listView = (ListView)findViewById(R.id.displayTabs);
        listView.setAdapter(adapter);

        Button homeBtn =(Button)findViewById(R.id.homebtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Tabs.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button updateBtn = (Button)findViewById(R.id.updateButton);
        String[] finalNames = names;
        float[] finalAmounts = amounts;
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String[] sendNames = finalNames;
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArray("currentNames", finalNames);
                bundle.putFloatArray("currentAmounts", finalAmounts);
                intent.putExtras(bundle);
                intent.setClass(Tabs.this,UpdateTab.class);
                startActivity(intent);


            }
        });


    }
}