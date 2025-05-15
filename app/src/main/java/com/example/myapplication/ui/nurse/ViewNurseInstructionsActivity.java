package com.example.myapplication.ui.nurse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Appointment;
import com.example.myapplication.data.entities.Prescription;
import com.example.myapplication.ui.doctor.ViewPatientDetailsActivity;
import com.example.myapplication.ui.doctor.ViewRecommendationsActivity;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;

public class ViewNurseInstructionsActivity extends AppCompatActivity {

    private ListView listViewInstructions;
    private AppDatabase db;
    private PrescriptionDao prescriptionDao;
    private NurseInstructionsAdapter instructionsAdapter;
    private AppointmentDao appointmentDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_view_instructions);

        listViewInstructions = findViewById(R.id.listViewInstructions);
        db = MyApplication.getInstance().getAppDatabase();
        prescriptionDao = db.prescriptionDao();
        List<Appointment>appointments = db.appointmentDao().getAppointmentsByStatus("已处理");
        List<NurseInstructionItem> instructionItems = new ArrayList<>();
        for(Appointment appointment :appointments) {
            List<Prescription> prescriptions = prescriptionDao.getPrescriptionsByPatientIdCard(appointment.getIdCard());
//            for (Prescription prescription : prescriptions) {
//                instructionItems.add(new NurseInstructionItem(
//                        appointment.getIdCard(),
//                        prescription.getNurseInstructions()
//                ));
//            }


            instructionsAdapter = new NurseInstructionsAdapter(this, R.layout.item_nurse_instruction, prescriptions);
            listViewInstructions.setAdapter(instructionsAdapter);
            listViewInstructions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Prescription prescription = (Prescription) parent.getItemAtPosition(position);
                    Intent intent = new Intent(ViewNurseInstructionsActivity.this, NurseInstructionDetailActivity.class);
                    intent.putExtra("patientIdCard", prescription.getPatientIdCard());
                    startActivity(intent);
                }
            });
        }
}
}