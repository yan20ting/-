package com.example.myapplication.ui.pharmacy;

public class PharmacyInstructionItem {
    private String patientIdCard;
    private String pharmacyInstructions;

    public PharmacyInstructionItem(String patientIdCard, String pharmacyInstructions) {
        this.patientIdCard = patientIdCard;
        this.pharmacyInstructions = pharmacyInstructions;
    }

    public String getPatientIdCard() {
        return patientIdCard;
    }

    public String getPharmacyInstructions() {
        return pharmacyInstructions;
    }
}