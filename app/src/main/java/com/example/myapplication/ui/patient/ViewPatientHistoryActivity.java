package com.example.myapplication.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.dao.HistoryDao;
import com.example.myapplication.data.entities.History;
import android.widget.AdapterView;
import java.util.List;

public class ViewPatientHistoryActivity extends AppCompatActivity {

    private ListView listViewHistory;
    private AppDatabase db;
    private HistoryDao historyDao;
    private PatientHistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view_history);

        listViewHistory = findViewById(R.id.listViewHistory);
        db = MyApplication.getInstance().getAppDatabase();
        historyDao = db.historyDao();

        String patientIdCard = getIntent().getStringExtra("patientIdCard");

        if (patientIdCard != null) {
            List<History> histories = historyDao.getHistoriesByPatientIdCard(patientIdCard);

            historyAdapter = new PatientHistoryAdapter(this, R.layout.item_patient_history, histories);
            listViewHistory.setAdapter(historyAdapter);

            listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                History history = (History) parent.getItemAtPosition(position);
                Intent intent = new Intent(ViewPatientHistoryActivity.this, PatientHistoryDetailActivity.class);
                intent.putExtra("historyId", history.getId());
                startActivity(intent);
                }
            });
        }
    }
}