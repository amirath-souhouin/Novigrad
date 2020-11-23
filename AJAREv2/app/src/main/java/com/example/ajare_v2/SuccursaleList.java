package com.example.ajare_v2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SuccursaleList extends ArrayAdapter<Succursale> {
    private Activity context;
    List<Succursale> succursales;
    public SuccursaleList(Activity context,List<Succursale> succursales ) {
        super(context, R.layout.activity_succursale_list, succursales);
        this.context = context;
        this.succursales=succursales;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_succursale_list, null, true);
        TextView textViewSuccursaleName = (TextView) listViewItem.findViewById(R.id.textViewSuccursaleName);
        TextView textViewHour = (TextView) listViewItem.findViewById(R.id.textViewHour);
        Succursale succursale = succursales.get(position);
        textViewSuccursaleName.setText(succursale.getAddresseName());
        textViewHour.setText(succursale.day_hour());
        return listViewItem;
    }
}
