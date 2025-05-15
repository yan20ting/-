package com.example.myapplication.ui.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.entities.Appointment;

public class RegisterAppointmentActivity extends AppCompatActivity {

    private EditText etAppointmentDescription;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_appointment);

        etAppointmentDescription = findViewById(R.id.et_appointment_description);
        db = MyApplication.getInstance().getAppDatabase();

        findViewById(R.id.btn_submit_appointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etAppointmentDescription.getText().toString().trim();

                if (description.isEmpty()) {
                    Toast.makeText(RegisterAppointmentActivity.this, "请输入问题描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 获取保存的用户信息
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String idCard = prefs.getString("idCard", "");
                String userRole = prefs.getString("userRole", "");

                if (!idCard.isEmpty() && userRole.equals("患者")) {
                    // 创建挂号记录
                    Appointment appointment = new Appointment(idCard, userRole, description);
                    db.appointmentDao().insertAppointment(appointment);

                    Toast.makeText(RegisterAppointmentActivity.this, "挂号成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterAppointmentActivity.this, PatientMainActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterAppointmentActivity.this, "用户信息获取失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}