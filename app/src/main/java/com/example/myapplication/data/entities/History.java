package com.example.myapplication.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String patientIdCard;
    private String patientName;
    private String doctorIdCard;
    private String description;
    private String medicine;
    private String dosage;
    private String instructions;
    private String nurseInstructions;
    private String pharmacyInstructions;
    private String patientInstructions;
    private String status;
    private String paymentAmount;

    public History(String patientIdCard, String patientName, String doctorIdCard, String description,String medicine, String dosage, String instructions, String nurseInstructions, String pharmacyInstructions, String patientInstructions, String paymentAmount) {
        this.patientIdCard = patientIdCard;
        this.patientName = patientName;
        this.doctorIdCard = doctorIdCard;
        this.description = description;
        this.medicine = medicine;
        this.dosage = dosage;
        this.instructions = instructions;
        this.nurseInstructions = nurseInstructions;
        this.pharmacyInstructions = pharmacyInstructions;
        this.patientInstructions = patientInstructions;
        this.status = "已缴费";
        this.paymentAmount = paymentAmount;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientIdCard() {
        return patientIdCard;
    }

    public void setPatientIdCard(String patientIdCard) {
        this.patientIdCard = patientIdCard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorIdCard() {
        return doctorIdCard;
    }

    public void setDoctorIdCard(String doctorIdCard) {
        this.doctorIdCard = doctorIdCard;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getNurseInstructions() {
        return nurseInstructions;
    }

    public void setNurseInstructions(String nurseInstructions) {
        this.nurseInstructions = nurseInstructions;
    }

    public String getPharmacyInstructions() {
        return pharmacyInstructions;
    }

    public void setPharmacyInstructions(String pharmacyInstructions) {
        this.pharmacyInstructions = pharmacyInstructions;
    }

    public String getPatientInstructions() {
        return patientInstructions;
    }

    public void setPatientInstructions(String patientInstructions) {
        this.patientInstructions = patientInstructions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}