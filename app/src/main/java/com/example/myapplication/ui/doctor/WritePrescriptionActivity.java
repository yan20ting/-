//package com.example.myapplication.ui.doctor;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.myapplication.MyApplication;
//import com.example.myapplication.R;
//import com.example.myapplication.data.AppDatabase;
//import com.example.myapplication.data.dao.PrescriptionDao;
//import com.example.myapplication.data.entities.Appointment;
//import com.example.myapplication.data.entities.Prescription;
//import java.util.List;
//import android.view.View;
//import android.widget.Toast;
//
//public class WritePrescriptionActivity extends AppCompatActivity {
//
//    private EditText etMedicine;
//    private EditText etDosage;
//    private EditText etInstructions;
//    private EditText etNurseInstructions;
//    private EditText etPharmacyInstructions;
//    private EditText etPatientInstructions;
//    private Spinner spPatient;
//    private Button btnSubmit;
//
//    private AppDatabase db;
//    private PrescriptionDao prescriptionDao;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_doctor_write_prescription);
//
//        etMedicine = findViewById(R.id.et_medicine);
//        etDosage = findViewById(R.id.et_dosage);
//        etInstructions = findViewById(R.id.et_instructions);
//        etNurseInstructions = findViewById(R.id.et_nurse_instructions);
//        etPharmacyInstructions = findViewById(R.id.et_pharmacy_instructions);
//        etPatientInstructions = findViewById(R.id.et_patient_instructions);
//        spPatient = findViewById(R.id.sp_patient);
//        btnSubmit = findViewById(R.id.btn_submit);
//
//        db = MyApplication.getInstance().getAppDatabase();
//        prescriptionDao = db.prescriptionDao();
//
//        // 填充患者选择框
//        populatePatientSpinner();
//
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String medicine = etMedicine.getText().toString().trim();
//                String dosage = etDosage.getText().toString().trim();
//                String instructions = etInstructions.getText().toString().trim();
//                String nurseInstructions = etNurseInstructions.getText().toString().trim();
//                String pharmacyInstructions = etPharmacyInstructions.getText().toString().trim();
//                String patientInstructions = etPatientInstructions.getText().toString().trim();
//                String patientIdCard = ((PatientSpinnerAdapter) spPatient.getAdapter()).getSelectedPatientIdCard();
//
//                if (medicine.isEmpty() || dosage.isEmpty() || instructions.isEmpty() || patientIdCard.isEmpty()) {
//                    // 显示错误信息
//                    return;
//                }
//
//                // 创建处方记录
//                Prescription prescription = new Prescription(
//                        patientIdCard,
//                        getDoctorIdCard(),
//                        medicine,
//                        dosage,
//                        instructions
//                );
//                prescription.setNurseInstructions(nurseInstructions);
//                prescription.setPharmacyInstructions(pharmacyInstructions);
//                prescription.setPatientInstructions(patientInstructions);
//
//                prescriptionDao.insertPrescription(prescription);
//
//                // 更新挂号记录状态
//                updateAppointmentStatus(patientIdCard);
//
//                // 显示成功消息并返回医生主页
//                Toast.makeText(WritePrescriptionActivity.this, "处方提交成功！", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(WritePrescriptionActivity.this, DoctorMainActivity.class));
//                finish();
//            }
//        });
//    }
//
//    private void populatePatientSpinner() {
//        // 获取所有患者
//        List<Appointment> patients = db.appointmentDao().getAllPatientAppointments();
//        PatientSpinnerAdapter adapter = new PatientSpinnerAdapter(this, patients);
//        spPatient.setAdapter(adapter);
//    }
//
//    private String getDoctorIdCard() {
//        return getSharedPreferences("user_prefs", MODE_PRIVATE).getString("idCard", "");
//    }
//
//    private void updateAppointmentStatus(String patientIdCard) {
//        // 更新挂号记录状态为已处理
//        Appointment appointment = new Appointment();
//        appointment.setIdCard(patientIdCard);
//        appointment.setStatus("已处理");
//        db.appointmentDao().updateAppointment(appointment);
//    }
//}