package com.example.myapplication.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users") // 显式指定表名
public class User {
    @PrimaryKey@NonNull
    private String idCard; // 使用身份证号作为主键

    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private String userRole;

    // 构造函数
    public User(String idCard, String password, String fullName, String phoneNumber, String email, String address, String userRole) {
        this.idCard = idCard;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.userRole = userRole;
    }

    // Getter 和 Setter 方法
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}