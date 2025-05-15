package com.example.myapplication.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.HistoryDao;
import com.example.myapplication.data.entities.History;

public class PatientHistoryDetailActivity extends AppCompatActivity {

    private TextView tvPatientName;
    private TextView tvDoctorName;
    private TextView tvMedicine;
    private TextView tvDosage;
    private TextView tvInstructions;
    private TextView tvNurseInstructions;
    private TextView tvPharmacyInstructions;
    private TextView tvPatientInstructions;
    private TextView tvPaymentAmount;

    private AppDatabase db;
    private HistoryDao historyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_history_detail);

        tvPatientName = findViewById(R.id.tv_patient_name);
        tvDoctorName = findViewById(R.id.tv_doctor_name);
        tvMedicine = findViewById(R.id.tv_medicine);
        tvDosage = findViewById(R.id.tv_dosage);
        tvInstructions = findViewById(R.id.tv_instructions);
        tvNurseInstructions = findViewById(R.id.tv_nurse_instructions);
        tvPharmacyInstructions = findViewById(R.id.tv_pharmacy_instructions);
        tvPatientInstructions = findViewById(R.id.tv_patient_instructions);
        tvPaymentAmount = findViewById(R.id.tv_payment_amount);

        db = MyApplication.getInstance().getAppDatabase();
        historyDao = db.historyDao();

        int historyId = getIntent().getIntExtra("historyId", 0);

        if (historyId != 0) {
            History history = historyDao.getHistoryById(historyId);
            if (history != null) {
                tvPatientName.setText("患者姓名: " + history.getPatientName());
                tvDoctorName.setText("医生姓名: " + history.getDoctorIdCard());
                tvMedicine.setText("药品: " + history.getMedicine());
                tvDosage.setText("剂量: " + history.getDosage());
                tvInstructions.setText("用法说明: " + history.getInstructions());
                tvNurseInstructions.setText("护理信息: " + history.getNurseInstructions());
                tvPharmacyInstructions.setText("药品管理员信息: " + history.getPharmacyInstructions());
                tvPatientInstructions.setText("患者注意事项: " + history.getPatientInstructions());
                tvPaymentAmount.setText("缴费金额: " + history.getPaymentAmount());
            }
        }
    }
}

