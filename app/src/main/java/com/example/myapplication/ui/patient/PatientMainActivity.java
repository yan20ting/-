package com.example.myapplication.ui.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class PatientMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        findViewById(R.id.btn_register_appointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到挂号页面
                startActivity(new Intent(PatientMainActivity.this, RegisterAppointmentActivity.class));
            }
        });

        findViewById(R.id.btn_view_appointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到查看挂号信息页面
                startActivity(new Intent(PatientMainActivity.this, ViewAppointmentActivity.class));
            }
        });

        findViewById(R.id.btn_payment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到缴费页面
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String idCard = prefs.getString("idCard", "");
                Intent intent = new Intent(PatientMainActivity.this, PatientPaymentActivity.class);
                intent.putExtra("patientIdCard", idCard);
                startActivity(intent);
            }
        });


        findViewById(R.id.btn_prescription).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到查看处方页面
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String idCard = prefs.getString("idCard", "");
                Intent intent = new Intent(PatientMainActivity.this, ViewPrescriptionActivity.class);
                intent.putExtra("patientIdCard", idCard);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String idCard = prefs.getString("idCard", "");
                Intent intent = new Intent(PatientMainActivity.this, ViewPatientHistoryActivity.class);
                intent.putExtra("patientIdCard", idCard);
                startActivity(intent);
            }
        });
    }
}