package com.example.myapplication.ui.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Appointment;
import com.example.myapplication.data.entities.Prescription;
import com.example.myapplication.data.entities.User;
import android.view.View;
import android.widget.Toast;
public class ViewPatientDetailsActivity extends AppCompatActivity {

    private TextView tvPatientIdCard;
    private TextView tvDescription;
    private EditText etMedicine;
    private EditText etDosage;
    private EditText etInstructions;
    private EditText etNurseInstructions;
    private EditText etPharmacyInstructions;
    private EditText etPatientInstructions;
    private Button btnSubmitPrescription;

    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private PrescriptionDao prescriptionDao;

    private String patientIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_patient_details);

        tvPatientIdCard = findViewById(R.id.tv_patient_id_card);
        tvDescription = findViewById(R.id.tv_description);
        etMedicine = findViewById(R.id.et_medicine);
        etDosage = findViewById(R.id.et_dosage);
        etInstructions = findViewById(R.id.et_instructions);
        etNurseInstructions = findViewById(R.id.et_nurse_instructions);
        etPharmacyInstructions = findViewById(R.id.et_pharmacy_instructions);
        etPatientInstructions = findViewById(R.id.et_patient_instructions);
        btnSubmitPrescription = findViewById(R.id.btn_submit_prescription);

        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();
        prescriptionDao = db.prescriptionDao();

        patientIdCard = getIntent().getStringExtra("patientIdCard");

        if (patientIdCard != null) {
            Appointment appointment = appointmentDao.getAppointmentsByUserIdentity(patientIdCard, "患者").get(0);
            tvPatientIdCard.setText("患者身份证号: " + patientIdCard);
            tvDescription.setText("问题描述: " + appointment.getDescription());

            btnSubmitPrescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String medicine = etMedicine.getText().toString().trim();
                    String dosage = etDosage.getText().toString().trim();
                    String instructions = etInstructions.getText().toString().trim();
                    String nurseInstructions = etNurseInstructions.getText().toString().trim();
                    String pharmacyInstructions = etPharmacyInstructions.getText().toString().trim();
                    String patientInstructions = etPatientInstructions.getText().toString().trim();

                    if (medicine.isEmpty() || dosage.isEmpty() || instructions.isEmpty()) {
                        // 显示输入错误信息
                        return;
                    }

                    // 获取当前登录医生的信息
                    String doctorIdCard = getSharedPreferences("user_prefs", MODE_PRIVATE).getString("idCard", "");

                    if (!doctorIdCard.isEmpty()) {
                        // 创建处方记录
                        Prescription prescription = new Prescription(
                                patientIdCard,
                                doctorIdCard,
                                medicine,
                                dosage,
                                instructions
                        );
                        prescription.setNurseInstructions(nurseInstructions);
                        prescription.setPharmacyInstructions(pharmacyInstructions);
                        prescription.setPatientInstructions(patientInstructions);

                        prescriptionDao.insertPrescription(prescription);

                        // 更新挂号记录状态
                        appointment.setStatus("已处理");
                        appointmentDao.updateAppointment(appointment);

                        // 显示成功消息并返回医生主页
                        Toast.makeText(ViewPatientDetailsActivity.this, "处方提交成功！", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ViewPatientDetailsActivity.this, DoctorMainActivity.class));
                        finish();
                    }
                }
            });
        }
    }
}