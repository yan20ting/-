package com.example.myapplication.ui.nurse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.content.SharedPreferences;

public class NurseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nurse_main);

        findViewById(R.id.btn_manage_appointments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到整理挂号页面
                startActivity(new Intent(NurseMainActivity.this, ManageAppointmentsActivity.class));
            }
        });



        findViewById(R.id.btn_patient_nurse_tasks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到查看护理信息页面
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String idCard = prefs.getString("idCard", "");
                Intent intent = new Intent(NurseMainActivity.this, ViewNurseInstructionsActivity.class);
                intent.putExtra("patientIdCard", idCard);
                startActivity(intent);
            }
        });


    }
}