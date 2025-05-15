package com.example.myapplication.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.entities.User;
import com.example.myapplication.ui.nurse.NurseMainActivity;
import com.example.myapplication.ui.patient.PatientMainActivity;
import com.example.myapplication.ui.doctor.DoctorMainActivity;
import com.example.myapplication.ui.pharmacy.PharmacyMainActivity;

public class GenericLoginActivity extends AppCompatActivity {

    private EditText etLoginIdOrPhone;
    private EditText etPassword;
    private AppDatabase db;
    private String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_login);

        etLoginIdOrPhone = findViewById(R.id.et_login_id_or_phone);
        etPassword = findViewById(R.id.et_password);
        db = MyApplication.getInstance().getAppDatabase();

        // 获取传递的角色类型
        userRole = getIntent().getStringExtra("userRole");
        findViewById(R.id.tv_title).findViewById(R.id.tv_title);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(userRole + "登录");
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginIdOrPhone = etLoginIdOrPhone.getText().toString().trim();
                String password = etPassword.getText().toString();

                if (loginIdOrPhone.isEmpty() || password.isEmpty()) {
                    Toast.makeText(GenericLoginActivity.this, "请输入身份证号/手机号和密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 从数据库验证用户
                User user = db.userDao().getUserByIdCardOrPhoneAndPassword(loginIdOrPhone, password, userRole);
                if (user != null) {
                    // 保存用户信息到SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences("user_prefs", MODE_PRIVATE).edit();
                    editor.putString("idCard", user.getIdCard());
                    editor.putString("userRole", user.getUserRole());
                    editor.putString("username", user.getFullName());
                    editor.apply();

                    Toast.makeText(GenericLoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    redirectToMainActivity(userRole, user.getFullName());
                } else {
                    Toast.makeText(GenericLoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.tv_forget_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到忘记密码页面
                Toast.makeText(GenericLoginActivity.this, "忘了也没用，本系统暂无交互功能，建议查看数据库", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(GenericLoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }

    private void redirectToMainActivity(String userRole, String username) {
        Intent intent;
        switch (userRole) {
            case "患者":
                intent = new Intent(this, PatientMainActivity.class);
                break;
            case "医生":
                intent = new Intent(this, DoctorMainActivity.class);
                break;
            case "护士":
                intent = new Intent(this, NurseMainActivity.class);
                break;
            case "药房管理员":
                intent = new Intent(this, PharmacyMainActivity.class);
                break;
            default:
                intent = new Intent(this, PatientMainActivity.class);
        }
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}