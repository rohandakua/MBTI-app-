package com.example.mbpt;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        // Customize the view here if needed
        TextView tv=view.findViewById(R.id.l1);
        if(quesReport[position].saw && quesReport[position].attempt){
            tv.setBackgroundResource(R.color.green);
        } else if (quesReport[position].saw==true) {
            tv.setBackgroundResource(R.color.red);
        } else{
            tv.setBackgroundResource(R.color.grey);
        }


        return view;
    }
    public qrd[] quesReport;
    public void help(qrd[] a){
        this.quesReport=a;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        // Customize the dropdown view here based on some condition
        TextView l1=view.findViewById(R.id.l1);
        l1.setBackgroundColor(R.color.green);


        return view;
    }


}