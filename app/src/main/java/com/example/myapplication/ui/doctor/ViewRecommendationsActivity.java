package com.example.myapplication.ui.doctor;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.entities.Appointment;
import android.view.View;
import java.util.List;
import com.example.myapplication.data.dao.UserDao;
import com.example.myapplication.data.entities.User;

public class ViewRecommendationsActivity extends AppCompatActivity {

    private ListView listViewRecommendations;
    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private RecommendationAdapter recommendationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view_recommendations);

        listViewRecommendations = findViewById(R.id.listViewRecommendations);
        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();

        // 获取当前登录医生的身份证号
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String doctorIdCard = prefs.getString("idCard", "");
        String doctorRole = prefs.getString("userRole", "");

        if (!doctorIdCard.isEmpty() && doctorRole.equals("医生")) {
            // 获取医生的用户名
            User doctor = db.userDao().getUserByIdCard(doctorIdCard);
            if (doctor != null) {
                String doctorName = doctor.getFullName();
                // 获取推荐给该医生的患者列表
                List<Appointment> appointments = appointmentDao.getAppointmentsByRecommendedDoctor(doctorName);

                recommendationAdapter = new RecommendationAdapter(this, R.layout.item_recommendation, appointments);
                listViewRecommendations.setAdapter(recommendationAdapter);

            listViewRecommendations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Appointment appointment = (Appointment) parent.getItemAtPosition(position);
                    Intent intent = new Intent(ViewRecommendationsActivity.this, ViewPatientDetailsActivity.class);
                    intent.putExtra("patientIdCard", appointment.getIdCard());
                    startActivity(intent);
                }
            });
        }
    }
}
}