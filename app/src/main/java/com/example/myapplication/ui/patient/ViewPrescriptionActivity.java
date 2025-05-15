package com.example.myapplication.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Prescription;

import java.util.List;

public class ViewPrescriptionActivity extends AppCompatActivity {

    private TextView tvMedicine;
    private TextView tvDosage;
    private TextView tvInstructions;
    private TextView tvPatientInstructions;

    private AppDatabase db;
    private PrescriptionDao prescriptionDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_prescription);

        tvMedicine = findViewById(R.id.tv_medicine);
        tvDosage = findViewById(R.id.tv_dosage);
        tvInstructions = findViewById(R.id.tv_instructions);
        tvPatientInstructions = findViewById(R.id.tv_patient_instructions);

        db = MyApplication.getInstance().getAppDatabase();
        prescriptionDao = db.prescriptionDao();

        String patientIdCard = getIntent().getStringExtra("patientIdCard");

        if (patientIdCard != null) {
            List<Prescription> prescriptions = prescriptionDao.getPrescriptionsByPatientIdCard(patientIdCard);

            if (!prescriptions.isEmpty()) {
                Prescription prescription = prescriptions.get(0);
                tvMedicine.setText("药品: " + prescription.getMedicine());
                tvDosage.setText("剂量: " + prescription.getDosage());
                tvInstructions.setText("用法说明: " + prescription.getInstructions());
                tvPatientInstructions.setText("患者注意事项: " + prescription.getPatientInstructions());
            } else {
                tvMedicine.setText("暂无处方信息");
                tvDosage.setText("暂无处方信息");
                tvInstructions.setText("暂无处方信息");
                tvPatientInstructions.setText("暂无处方信息");
            }
        }
    }
}