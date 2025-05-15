package com.example.myapplication.ui.pharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Appointment;
import android.widget.Toast;
public class PharmacyInstructionDetailActivity extends AppCompatActivity {

    private TextView tvPatientIdCard;
    private TextView tvPharmacyInstructions;
    private EditText etPaymentAmount;
    private Button btnSubmit;

    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private String patientIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_instruction_detail);

        tvPatientIdCard = findViewById(R.id.tv_patient_id_card);
        tvPharmacyInstructions = findViewById(R.id.tv_pharmacy_instructions);
        etPaymentAmount = findViewById(R.id.et_payment_amount);
        btnSubmit = findViewById(R.id.btn_submit);

        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();

        patientIdCard = getIntent().getStringExtra("patientIdCard");
        String pharmacyInstructions = getIntent().getStringExtra("pharmacyInstructions");

        if (patientIdCard != null && pharmacyInstructions != null) {
            tvPatientIdCard.setText("患者身份证号: " + patientIdCard);
            tvPharmacyInstructions.setText("药品信息: " + pharmacyInstructions);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String paymentAmount = etPaymentAmount.getText().toString().trim();

                    if (paymentAmount.isEmpty()) {
                        // 显示错误信息
                        return;
                    }

                    // 更新挂号状态为"代缴费"
                    updateAppointmentStatus(patientIdCard, paymentAmount);
                    Toast.makeText(PharmacyInstructionDetailActivity.this, "金额提交成功！", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    private void updateAppointmentStatus(String patientIdCard, String paymentAmount) {
        appointmentDao.updateAppointmentStatus2(patientIdCard, "待缴费", paymentAmount);
    }
}