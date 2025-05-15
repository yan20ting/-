package com.example.myapplication.ui.nurse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.myapplication.R;
import com.example.myapplication.data.entities.Prescription;
import android.view.View;
import com.example.myapplication.MyApplication;
import com.example.myapplication.data.dao.AppointmentDao;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;

public class NurseInstructionsAdapter extends ArrayAdapter<Prescription> {

    private AppointmentDao appointmentDao;
    public NurseInstructionsAdapter(Context context, int resource, List<Prescription> prescriptions) {
        super(context, resource, prescriptions);
        appointmentDao = ((MyApplication) context.getApplicationContext()).getAppDatabase().appointmentDao();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_nurse_instruction, parent, false);
        }

        TextView tvPatientIdCard = convertView.findViewById(R.id.tv_patient_id_card);
        TextView tvNurseInstructions = convertView.findViewById(R.id.tv_nurse_instructions);

        final Prescription prescription = getItem(position);
        if (prescription != null) {
            tvPatientIdCard.setText("患者身份证号: " + prescription.getPatientIdCard());
            tvNurseInstructions.setText("护理信息: " + prescription.getNurseInstructions());

//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getContext(), NurseInstructionDetailActivity.class);
//                    intent.putExtra("patientIdCard", prescription.getPatientIdCard());
//                    getContext().startActivity(intent);
//                }
//            });
        }

        return convertView;
    }
}