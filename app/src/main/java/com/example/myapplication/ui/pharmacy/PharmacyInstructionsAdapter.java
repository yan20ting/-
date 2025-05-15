package com.example.myapplication.ui.pharmacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import com.example.myapplication.R;
import android.content.Intent;
import java.util.List;

public class PharmacyInstructionsAdapter extends ArrayAdapter<PharmacyInstructionItem> {

    public PharmacyInstructionsAdapter(Context context, int resource, List<PharmacyInstructionItem> instructionItems) {
        super(context, resource, instructionItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pharmacy_instruction, parent, false);
        }

        TextView tvPatientIdCard = convertView.findViewById(R.id.tv_patient_id_card);
        TextView tvPharmacyInstructions = convertView.findViewById(R.id.tv_pharmacy_instructions);
        Button btnSubmit = convertView.findViewById(R.id.btn_submit);

        final PharmacyInstructionItem item = getItem(position);
        if (item != null) {
            tvPatientIdCard.setText("患者身份证号: " + item.getPatientIdCard());
            tvPharmacyInstructions.setText("药品信息: " + item.getPharmacyInstructions());

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到输入金额页面
                    Intent intent = new Intent(getContext(), PharmacyInstructionDetailActivity.class);
                    intent.putExtra("patientIdCard", item.getPatientIdCard());
                    intent.putExtra("pharmacyInstructions", item.getPharmacyInstructions());
                    getContext().startActivity(intent);
                }
            });
        }

        return convertView;
    }
}