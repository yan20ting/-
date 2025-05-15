package com.example.myapplication.ui.nurse;

public class NurseInstructionItem {
    private String patientIdCard;
    private String nurseInstructions;

    public NurseInstructionItem(String patientIdCard, String nurseInstructions) {
        this.patientIdCard = patientIdCard;
        this.nurseInstructions = nurseInstructions;
    }

    public String getPatientIdCard() {
        return patientIdCard;
    }

    public String getNurseInstructions() {
        return nurseInstructions;
    }
}