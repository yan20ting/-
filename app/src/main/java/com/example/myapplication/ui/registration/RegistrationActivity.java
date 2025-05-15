package com.example.myapplication.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.entities.User;
import com.example.myapplication.MainActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etIdCard;
    private EditText etPhoneNumber;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etAddress;
    private Spinner spUserRole;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFullName = findViewById(R.id.etFullName);
        etIdCard = findViewById(R.id.etIdCard);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        spUserRole = findViewById(R.id.spUserRole);
        btnRegister = findViewById(R.id.btnRegister);

        // 设置Spinner适配器
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUserRole.setAdapter(adapter);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入
                String fullName = etFullName.getText().toString().trim();
                String idCard = etIdCard.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString();
                String address = etAddress.getText().toString().trim();
                String userRole = spUserRole.getSelectedItem().toString();

                // 验证输入
                if (!isValidRegistration(fullName, idCard, phoneNumber, email, password, address)) {
                    return;
                }

                // 检查身份证号和电话号是否已存在
                AppDatabase db = MyApplication.getInstance().getAppDatabase();
                User existingUserByIdCard = db.userDao().getUserByIdCard2(idCard,userRole);
                User existingUserByPhoneNumber = db.userDao().getUserByPhoneNumber(phoneNumber,userRole);

                if (existingUserByIdCard != null) {
                    Toast.makeText(RegistrationActivity.this, "该身份证号已注册！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (existingUserByPhoneNumber != null) {
                    Toast.makeText(RegistrationActivity.this, "该手机号已注册！", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 创建用户对象
                User user = new User(idCard, password, fullName, phoneNumber, email, address, userRole);

                // 插入数据库
                db.userDao().insertUser(user);

                // 显示成功消息并返回主界面
                Toast.makeText(RegistrationActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValidRegistration(String fullName, String idCard, String phoneNumber, String email, String password, String address) {
        // 验证逻辑保持不变
        return true;
    }
}