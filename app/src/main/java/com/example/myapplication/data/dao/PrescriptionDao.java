package com.example.myapplication.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.myapplication.data.entities.Prescription;
import java.util.List;

@Dao
public interface PrescriptionDao {
    @Insert
    void insertPrescription(Prescription prescription);

    @Query("SELECT * FROM prescription WHERE patientIdCard = :patientIdCard")
    List<Prescription> getPrescriptionsByPatientIdCard(String patientIdCard);

    @Query("SELECT * FROM prescription WHERE doctorIdCard = :doctorIdCard")
    List<Prescription> getPrescriptionsByDoctorIdCard(String doctorIdCard);
    @Query("SELECT * FROM prescription")
    List<Prescription> getAllPrescriptions();
    @Query("DELETE FROM prescription WHERE patientIdCard = :patientIdCard")
    void deletePrescription(String patientIdCard);
    @Query("SELECT doctorIdCard from prescription where patientIdCard =:patientIdCard")
    String getDoctorIdCard(String patientIdCard);
    @Update
    void updatePrescription(Prescription prescription);
}