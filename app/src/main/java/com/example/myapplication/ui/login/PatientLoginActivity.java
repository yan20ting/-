package com.example.myapplication.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.entities.User;
import com.example.myapplication.ui.patient.PatientMainActivity;

public class PatientLoginActivity extends AppCompatActivity {

    private EditText etLoginIdOrPhone;
    private EditText etPassword;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        etLoginIdOrPhone = findViewById(R.id.et_login_id_or_phone);
        etPassword = findViewById(R.id.et_password);
        db = MyApplication.getInstance().getAppDatabase();

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginIdOrPhone = etLoginIdOrPhone.getText().toString().trim();
                String password = etPassword.getText().toString();

                if (loginIdOrPhone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(PatientLoginActivity.this, "请输入身份证号/手机号和密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 从数据库验证用户
                User user = db.userDao().getUserByIdCardOrPhoneAndPassword(loginIdOrPhone, password, "患者");
                if (user != null) {
                    // 保存用户信息到SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("user_prefs", MODE_PRIVATE).edit();
                    editor.putString("idCard", user.getIdCard());
                    editor.putString("userRole", "患者");
                    editor.apply();

                    Toast.makeText(PatientLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PatientLoginActivity.this, PatientMainActivity.class));
                    finish();
                } else {
                    Toast.makeText(PatientLoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.tv_forget_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到忘记密码页面
                Toast.makeText(PatientLoginActivity.this, "忘了也没用，本系统暂无交互功能，建议查看数据库", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(PatientLoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }
}