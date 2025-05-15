package com.example.myapplication.ui.nurse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.data.entities.Appointment;
import java.util.List;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    public AppointmentAdapter(Context context, int resource, List<Appointment> appointments) {
        super(context, resource, appointments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_appointment, parent, false);
        }

        TextView tvDescription = convertView.findViewById(R.id.tv_description);
        TextView tvStatus = convertView.findViewById(R.id.tv_status);
        TextView tvRecommendedDoctor = convertView.findViewById(R.id.tv_recommended_doctor);

        Appointment appointment = getItem(position);
        if (appointment != null) {
            tvDescription.setText("问题描述: " + appointment.getDescription());
            tvStatus.setText("状态: " + appointment.getStatus());
            tvRecommendedDoctor.setText("推荐医生: " + (appointment.getRecommendedDoctor() != null ? appointment.getRecommendedDoctor() : "暂无"));
        }

        return convertView;
    }
}