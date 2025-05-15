package com.example.myapplication.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.myapplication.data.entities.History;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insertHistory(History history);

    @Query("SELECT * FROM history WHERE patientIdCard = :patientIdCard")
    List<History> getHistoriesByPatientIdCard(String patientIdCard);

    @Query("SELECT * FROM history WHERE status = :status")
    List<History> getHistoriesByStatus(String status);

    @Query("SELECT * FROM history WHERE id = :id")
    History getHistoryById(int id);
}