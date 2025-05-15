package com.example.myapplication.ui.nurse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.entities.Appointment;

import java.util.List;

public class ManageAppointmentsActivity extends AppCompatActivity {

    private ListView listViewAppointments;
    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private AppointmentAdapter appointmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_manage_appointments);

        listViewAppointments = findViewById(R.id.listViewAppointments);
        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();

        List<Appointment> appointments = appointmentDao.getAppointList();
        appointmentAdapter = new AppointmentAdapter(this, R.layout.item_appointment, appointments);
        listViewAppointments.setAdapter(appointmentAdapter);

        listViewAppointments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Appointment appointment = (Appointment) parent.getItemAtPosition(position);
                Intent intent = new Intent(ManageAppointmentsActivity.this, ViewAppointmentDetailsActivity.class);
                intent.putExtra("idCard", appointment.getIdCard());
                intent.putExtra("userRole", appointment.getUserRole());
                startActivity(intent);
            }
        });
    }
}