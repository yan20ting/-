package com.example.myapplication.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointment")
public class Appointment {
    @PrimaryKey@NonNull
    private String idCard; // 使用身份证号作为主键
    private double money;
    private String userRole; // 用户身份
    private String description;
    private String status;
    private String recommendedDoctor;

    // 构造函数、getter 和 setter 方法
    public Appointment(String idCard, String userRole, String description) {
        this.idCard = idCard;
        this.userRole = userRole;
        this.description = description;
        this.status = "待处理";
        this.money = 0.0;
    }

    // Getter 和 Setter 方法
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecommendedDoctor() {
        return recommendedDoctor;
    }

    public void setRecommendedDoctor(String recommendedDoctor) {
        this.recommendedDoctor = recommendedDoctor;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    public double getMoney(){
        return money;
    }
}