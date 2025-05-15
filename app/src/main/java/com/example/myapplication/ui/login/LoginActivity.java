package com.example.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_patient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGenericLoginActivity("患者");
            }
        });

        findViewById(R.id.btn_doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGenericLoginActivity("医生");
            }
        });

        findViewById(R.id.btn_nurse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGenericLoginActivity("护士");
            }
        });

        findViewById(R.id.btn_pharmacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGenericLoginActivity("药房管理员");
            }
        });
    }

    private void launchGenericLoginActivity(String userRole) {
        Intent intent = new Intent(this, GenericLoginActivity.class);
        intent.putExtra("userRole", userRole);
        startActivity(intent);
    }
}