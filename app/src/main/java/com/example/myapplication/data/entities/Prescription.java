package com.example.myapplication.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.List;
@Entity(tableName = "prescription")
public class Prescription {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String patientIdCard;
    private String doctorIdCard;
    private String medicine;
    private String dosage;
    private String instructions;
    private String nurseInstructions;
    private String pharmacyInstructions;
    private String patientInstructions;

    public Prescription(String patientIdCard, String doctorIdCard, String medicine, String dosage, String instructions) {
        this.patientIdCard = patientIdCard;
        this.doctorIdCard = doctorIdCard;
        this.medicine = medicine;
        this.dosage = dosage;
        this.instructions = instructions;
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
}