package com.example.myapplication.ui.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.entities.Appointment;

public class ViewAppointmentActivity extends AppCompatActivity {

    private TextView tvAppointmentDescription;
    private TextView tvAppointmentStatus;
    private TextView tvRecommendedDoctor;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment);

        tvAppointmentDescription = findViewById(R.id.tv_appointment_description);
        tvAppointmentStatus = findViewById(R.id.tv_appointment_status);
        tvRecommendedDoctor = findViewById(R.id.tv_recommended_doctor);
        db = MyApplication.getInstance().getAppDatabase();

        // 获取保存的用户信息
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String idCard = prefs.getString("idCard", "");
        String userRole = prefs.getString("userRole", "");

        if (idCard.isEmpty() || !userRole.equals("患者")) {
            // 如果没有获取到用户信息，显示错误信息
            tvAppointmentDescription.setText("问题描述: 用户信息获取失败");
            tvAppointmentStatus.setText("状态: 用户信息获取失败");
            tvRecommendedDoctor.setText("推荐医生: 用户信息获取失败");
            return;
        }

        // 获取患者的挂号信息
        Appointment appointment = db.appointmentDao().getAppointmentsByUserIdentity(idCard, userRole).get(0);

        if (appointment != null) {
            tvAppointmentDescription.setText("问题描述: " + appointment.getDescription());
            tvAppointmentStatus.setText("状态: " + appointment.getStatus());

            if (appointment.getRecommendedDoctor() != null) {
                tvRecommendedDoctor.setText("推荐医生: " + appointment.getRecommendedDoctor());
            } else {
                tvRecommendedDoctor.setText("推荐医生: 暂无");
            }
        } else {
            tvAppointmentDescription.setText("问题描述: 暂无挂号信息");
            tvAppointmentStatus.setText("状态: 暂无挂号信息");
            tvRecommendedDoctor.setText("推荐医生: 暂无");
        }
    }
}