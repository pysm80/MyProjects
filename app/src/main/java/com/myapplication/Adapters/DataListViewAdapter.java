package com.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplication.R;

public class DataListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public DataListViewAdapter(Context context, String[] values) {
        super(context, R.layout.user_row_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.user_row_layout, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.name);
        textView.setText(values[position]);
        return rowView;
    }
}
