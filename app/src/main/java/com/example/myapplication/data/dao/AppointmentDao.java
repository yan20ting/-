package com.example.myapplication.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.myapplication.data.entities.Appointment;
import java.util.List;

@Dao
public interface AppointmentDao {
    @Insert
    void insertAppointment(Appointment appointment);

    @Query("SELECT * FROM appointment WHERE userRole = '患者'")
    List<Appointment> getAllPatientAppointments();

    @Query("SELECT * FROM appointment WHERE idCard = :idCard AND userRole = :userRole")
    List<Appointment> getAppointmentsByUserIdentity(String idCard, String userRole);

    @Query("SELECT * FROM appointment WHERE idCard = :idCard AND userRole = :userRole AND status = :status")
    List<Appointment> getAppointmentsByUserIdentityAndStatus(String idCard, String userRole, String status);

    @Query("SELECT * FROM appointment WHERE recommendedDoctor = :recommendedDoctor")
    List<Appointment> getAppointmentsByRecommendedDoctor(String recommendedDoctor);

    @Query("SELECT * FROM appointment WHERE idCard = :idCard AND status = '已处理'")
    List<Appointment>getAppointsByIdCard(String idCard);

    @Query("SELECT * FROM appointment WHERE status = '待处理'")
    List<Appointment>getAppointList();

    @Query("UPDATE appointment SET recommendedDoctor = :recommendedDoctor, status = '已推荐' WHERE idCard = :idCard AND userRole = :userRole AND status = '待处理'")
    void updateRecommendedDoctor(String idCard, String userRole, String recommendedDoctor);

    @Query("UPDATE appointment SET status = '已完成' WHERE idCard = :patientIdCard")
    void updateAppointmentStatus(String patientIdCard);

    @Query("UPDATE appointment SET status = :status, money = :paymentAmount WHERE idCard = :patientIdCard")
    void updateAppointmentStatus2(String patientIdCard, String status, String paymentAmount);

    @Query("SELECT * FROM appointment WHERE status = :status")
    List<Appointment> getAppointmentsByStatus(String status);

    @Update
    void updateAppointment(Appointment appointment);

    @Query("DELETE FROM appointment WHERE idCard = :idCard")
    void deleteAppointment(String idCard);

}