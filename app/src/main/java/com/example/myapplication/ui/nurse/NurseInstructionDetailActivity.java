package com.example.myapplication.ui.nurse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Prescription;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.entities.Appointment;

import java.util.List;

public class NurseInstructionDetailActivity extends AppCompatActivity {

    private TextView tvPatientIdCard;
    private TextView tvNurseInstructions;
    private Button btnComplete;
    private TextView tvPatientName;
    private AppDatabase db;
    private PrescriptionDao prescriptionDao;
    private AppointmentDao appointmentDao;
    private String patientIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_instruction_detail);

        tvPatientIdCard = findViewById(R.id.tv_patient_id_card);
        tvNurseInstructions = findViewById(R.id.tv_nurse_instructions);
        tvPatientName = findViewById(R.id.tv_patient_name);
        btnComplete = findViewById(R.id.btn_complete);

        db = MyApplication.getInstance().getAppDatabase();
        prescriptionDao = db.prescriptionDao();
        appointmentDao = db.appointmentDao();

        patientIdCard = getIntent().getStringExtra("patientIdCard");

        if (patientIdCard != null) {
            Prescription prescription = prescriptionDao.getPrescriptionsByPatientIdCard(patientIdCard).get(0);
            Appointment appointment = appointmentDao.getAppointsByIdCard(patientIdCard).get(0);
            if (prescription != null) {
                String name = db.userDao().getUserFullName(patientIdCard);
                tvPatientName.setText("患者姓名: " + name);
                tvPatientIdCard.setText("患者身份证号: " + prescription.getPatientIdCard());
                tvNurseInstructions.setText("护理信息: " + prescription.getNurseInstructions());

                btnComplete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 标记护理任务为已完成
                        db.appointmentDao().updateAppointmentStatus(patientIdCard);
                        Toast.makeText(NurseInstructionDetailActivity.this, "护理任务完成！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NurseInstructionDetailActivity.this, NurseMainActivity.class));
                        finish();
                    }
                });
            }
        }
    }
}