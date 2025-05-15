package com.example.myapplication.ui.pharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Prescription;

import java.util.List;

public class PharmacyReceivePrescriptionActivity extends AppCompatActivity {

    private EditText etTotalAmount;
    private Button btnSubmit;
    private AppDatabase db;
    private PrescriptionDao prescriptionDao;
    private String patientIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_receive_prescription);

        etTotalAmount = findViewById(R.id.et_total_amount);
        btnSubmit = findViewById(R.id.btn_submit);

        db = MyApplication.getInstance().getAppDatabase();
        prescriptionDao = db.prescriptionDao();

        patientIdCard = getIntent().getStringExtra("patientIdCard");

        if (patientIdCard != null) {
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String totalAmount = etTotalAmount.getText().toString().trim();

                    if (totalAmount.isEmpty()) {
                        Toast.makeText(PharmacyReceivePrescriptionActivity.this, "请输入总金额", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    List<Prescription> prescriptions = prescriptionDao.getPrescriptionsByPatientIdCard(patientIdCard);
                    Prescription prescription = prescriptions.get(0);                    if (prescription != null) {
                        prescription.setPharmacyInstructions(totalAmount);
                        prescriptionDao.updatePrescription(prescription);

                        Toast.makeText(PharmacyReceivePrescriptionActivity.this, "总金额提交成功！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PharmacyReceivePrescriptionActivity.this, PharmacyMainActivity.class));
                        finish();
                    }
                }
            });
        }
    }
}