package com.example.myapplication.ui.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class DoctorMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        findViewById(R.id.btn_view_recommendations).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到查看推荐患者页面
                startActivity(new Intent(DoctorMainActivity.this, ViewRecommendationsActivity.class));
            }
        });

        findViewById(R.id.btn_write_prescription).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到开处方页面
               startActivity(new Intent(DoctorMainActivity.this,ViewPatientDetailsActivity.class));
            }
        });


    }
}