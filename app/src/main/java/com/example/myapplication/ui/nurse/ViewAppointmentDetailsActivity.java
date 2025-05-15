package com.example.myapplication.ui.nurse;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.UserDao;
import com.example.myapplication.data.entities.Appointment;
import com.example.myapplication.data.entities.User;
import android.view.View;
import java.util.List;

public class ViewAppointmentDetailsActivity extends AppCompatActivity {

    private TextView tvDescription;
    private Spinner spRecommendDoctor;
    private Button btnUpdate;

    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private UserDao userDao;

    private String idCard;
    private String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_view_appointment);

        tvDescription = findViewById(R.id.tv_description);
        spRecommendDoctor = findViewById(R.id.sp_recommend_doctor);
        btnUpdate = findViewById(R.id.btn_update);

        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();
        userDao = db.userDao();

        idCard = getIntent().getStringExtra("idCard");
        userRole = getIntent().getStringExtra("userRole");

        if (idCard != null && userRole != null) {
            Appointment appointment = appointmentDao.getAppointmentsByUserIdentity(idCard, userRole).get(0);
            tvDescription.setText("问题描述: " + appointment.getDescription());

            // 获取所有医生
            List<User> doctors = userDao.getUsersByRole("医生");

            // 创建自定义适配器
            DoctorSpinnerAdapter doctorAdapter = new DoctorSpinnerAdapter(this, R.layout.item_doctor, doctors);
            spRecommendDoctor.setAdapter(doctorAdapter);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User selectedDoctor = (User) spRecommendDoctor.getSelectedItem();
                    if (selectedDoctor != null) {
                        appointment.setRecommendedDoctor(selectedDoctor.getFullName());
                        appointment.setStatus("已推荐");
                        appointmentDao.updateAppointment(appointment);
                        Toast.makeText(ViewAppointmentDetailsActivity.this, "推荐成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
    }
}