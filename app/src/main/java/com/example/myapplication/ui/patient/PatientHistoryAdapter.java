package com.example.myapplication.ui.patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.data.entities.History;

import java.util.List;

public class PatientHistoryAdapter extends ArrayAdapter<History> {

    public PatientHistoryAdapter(Context context, int resource, List<History> histories) {
        super(context, resource, histories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_patient_history, parent, false);
        }

        TextView tvDescription = convertView.findViewById(R.id.tv_description);

        History history = getItem(position);
        if (history != null) {
            tvDescription.setText("问题描述: " + history.getDescription());
        }

        return convertView;
    }
}