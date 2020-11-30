package com.example.ajare_v2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;
public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;
    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.activity_service_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_service_list, null, true);
        TextView textViewServiceName = (TextView) listViewItem.findViewById(R.id.textViewServiceName);
        TextView textViewDocuments = (TextView) listViewItem.findViewById(R.id.textViewDocuments);
        Service service = services.get(position);
        textViewServiceName.setText(service.getServiceName());
        textViewDocuments.setText(service.getDocuments());
        return listViewItem;
    }
}