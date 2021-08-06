package com.example.finalprojectprototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

public class TabItemAdapter extends ArrayAdapter<TabItem> {
    private LayoutInflater mInflater;
    public TabItemAdapter(Context context, int rid, List<TabItem> list)
    {
        super(context, rid, list);
        mInflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent){

        TabItem item =(TabItem)getItem(position);

        View view = mInflater.inflate(R.layout.tab_item,null);

        TextView name;
        name = (TextView)view.findViewById(R.id.tabName);
        name.setText(item.name);

        TextView amount;
        DecimalFormat df = new DecimalFormat("0.00");
        amount = (TextView)view.findViewById(R.id.tabAmount);
        amount.setText(df.format(item.amount));

        return view;
    }
}


