package com.example.myapplication.ui.nurse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.data.entities.User;

import java.util.List;

public class DoctorSpinnerAdapter extends ArrayAdapter<User> {

    public DoctorSpinnerAdapter(Context context, int resource, List<User> doctors) {
        super(context, resource, doctors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    private View createCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_doctor, parent, false);
        }

        TextView tvDoctorName = convertView.findViewById(R.id.tv_doctor_name);
        User doctor = getItem(position);
        if (doctor != null) {
            tvDoctorName.setText(doctor.getFullName());
        }

        return convertView;
    }
}