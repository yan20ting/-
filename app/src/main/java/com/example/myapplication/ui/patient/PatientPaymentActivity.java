package com.example.myapplication.ui.patient;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.dao.HistoryDao;
import com.example.myapplication.data.dao.UserDao;
import com.example.myapplication.data.entities.User;
import com.example.myapplication.data.entities.Appointment;
import com.example.myapplication.data.entities.History;
import com.example.myapplication.data.entities.Prescription;

import java.util.List;

public class PatientPaymentActivity extends AppCompatActivity {

    private TextView tvPaymentAmount;
    private Button btnPay;
    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private PrescriptionDao prescriptionDao;
    private UserDao userDao;
    private HistoryDao historyDao;
    private String patientIdCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_payment);

        tvPaymentAmount = findViewById(R.id.tv_total_amount);
        btnPay = findViewById(R.id.btn_pay);

        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();
        prescriptionDao = db.prescriptionDao();
        historyDao = db.historyDao();
        userDao = db.userDao();
        patientIdCard = getIntent().getStringExtra("patientIdCard");

        if (patientIdCard != null) {
            List<Appointment> appointments = appointmentDao.getAppointmentsByUserIdentity(patientIdCard, "患者");
            if (!appointments.isEmpty()) {
                Appointment appointment = appointments.get(0);
                if(appointment == null){
                    tvPaymentAmount.setText("暂无相关挂号即缴费信息");
                    btnPay.setEnabled(false);
                }
                else if (appointment.getMoney() != 0.0) {
                    if (appointment.getMoney() == 0) {
                        tvPaymentAmount.setText("缴费金额：" + "暂无相关缴费信息");
                        btnPay.setEnabled(false);
                    }
                    btnPay.setEnabled(true);
                    tvPaymentAmount.setText("缴费金额: " + appointment.getMoney());
                    btnPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 获取患者姓名
                            Prescription prescription = prescriptionDao.getPrescriptionsByPatientIdCard(patientIdCard).get(0);
                            String patientName = userDao.getUserFullName2(patientIdCard, "患者");
                            String DoctorIdCard = prescriptionDao.getDoctorIdCard(patientIdCard);
                            String DoctorName = userDao.getUserFullName2(DoctorIdCard, "医生");
                            assert appointment != null;
                            double payMoney = appointment.getMoney();
                            String Money = String.valueOf(payMoney);

                            // 创建历史记录
                            History history = new History(
                                    patientIdCard,
                                    patientName,
                                    DoctorName,
                                    appointment.getDescription(),
                                    prescription.getMedicine(),
                                    prescription.getDosage(),
                                    prescription.getInstructions(),
                                    prescription.getNurseInstructions(),
                                    prescription.getPharmacyInstructions(),
                                    prescription.getPatientInstructions(),
                                    Money

                            );
                            historyDao.insertHistory(history);

                            // 删除处方记录
                            List<Prescription> prescriptions = prescriptionDao.getPrescriptionsByPatientIdCard(patientIdCard);
                            for (Prescription prescription1 : prescriptions) {
                                prescriptionDao.deletePrescription(prescription1.getPatientIdCard());
                            }

                            // 删除挂号记录
                            appointmentDao.deleteAppointment(appointment.getIdCard());

                            Toast.makeText(PatientPaymentActivity.this, "缴费成功！相关信息已归档。", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(PatientPaymentActivity.this, PatientMainActivity.class));
                            finish();
                        }
                    });
                } else {
                    tvPaymentAmount.setText("缴费金额：" + "暂无相关缴费信息");
                    btnPay.setEnabled(false);
                }


            }
            else{
                tvPaymentAmount.setText("缴费金额：" + "暂无相关缴费信息");
                btnPay.setEnabled(false);
            }
        }

    }
}

