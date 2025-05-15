package com.example.myapplication.ui.pharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import android.content.SharedPreferences;
public class PharmacyMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_main);

        findViewById(R.id.btn_view_prescriptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到查看药品信息页面
                SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String idCard = prefs.getString("idCard", "");
                Intent intent = new Intent(PharmacyMainActivity.this, ViewPharmacyInstructionsActivity.class);
                intent.putExtra("patientIdCard", idCard);
                startActivity(intent);
            }
        });

    }
}