package com.example.myapplication.ui.doctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.data.entities.Appointment;

import java.util.List;

public class RecommendationAdapter extends ArrayAdapter<Appointment> {

    public RecommendationAdapter(Context context, int resource, List<Appointment> appointments) {
        super(context, resource, appointments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recommendation, parent, false);
        }

        TextView tvPatientIdCard = convertView.findViewById(R.id.tv_patient_id_card);
        TextView tvDescription = convertView.findViewById(R.id.tv_description);

        Appointment appointment = getItem(position);
        if (appointment != null) {
            tvPatientIdCard.setText("患者身份证号: " + appointment.getIdCard());
            tvDescription.setText("问题描述: " + appointment.getDescription());
        }

        return convertView;
    }
}