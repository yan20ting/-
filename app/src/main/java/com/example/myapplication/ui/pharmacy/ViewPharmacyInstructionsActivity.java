package com.example.myapplication.ui.pharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Appointment;
import com.example.myapplication.data.entities.Prescription;

import java.util.ArrayList;
import java.util.List;

public class ViewPharmacyInstructionsActivity extends AppCompatActivity {

    private ListView listViewInstructions;
    private AppDatabase db;
    private AppointmentDao appointmentDao;
    private PrescriptionDao prescriptionDao;
    private PharmacyInstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_view_instructions);

        listViewInstructions = findViewById(R.id.listViewInstructions);
        db = MyApplication.getInstance().getAppDatabase();
        appointmentDao = db.appointmentDao();
        prescriptionDao = db.prescriptionDao();

        // 获取所有状态为"已完成"的挂号记录
        List<Appointment> appointments = appointmentDao.getAppointmentsByStatus("已完成");

        List<PharmacyInstructionItem> instructionItems = new ArrayList<>();
        for (Appointment appointment : appointments) {
            List<Prescription> prescriptions = prescriptionDao.getPrescriptionsByPatientIdCard(appointment.getIdCard());
            for (Prescription prescription : prescriptions) {
                instructionItems.add(new PharmacyInstructionItem(
                        appointment.getIdCard(),
                        prescription.getPharmacyInstructions()
                ));
            }
        }

        instructionsAdapter = new PharmacyInstructionsAdapter(this, R.layout.item_pharmacy_instruction, instructionItems);
        listViewInstructions.setAdapter(instructionsAdapter);
    }
}