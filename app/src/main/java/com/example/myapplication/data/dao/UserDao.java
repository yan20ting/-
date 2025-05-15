package com.example.myapplication.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.myapplication.data.entities.User;
import java.util.List;
@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE idCard = :idCard AND password = :password AND userRole = :userRole")
    User getUserByIdCardAndPasswordAndRole(String idCard, String password, String userRole);


    @Query("SELECT * FROM users WHERE fullName = :username AND password = :password AND userRole = :userRole")
    User getUserByUsernameAndPasswordAndRole(String username, String password, String userRole);

    @Query("SELECT * FROM users WHERE (idCard = :idCardOrPhone OR phoneNumber = :idCardOrPhone) AND password = :password AND userRole = :userRole")
    User getUserByIdCardOrPhoneAndPassword(String idCardOrPhone, String password, String userRole);

    @Query("SELECT * FROM users WHERE idCard = :idCard")
    User getUserByIdCard(String idCard);

    @Query("SELECT * FROM users WHERE idCard = :idCard and userRole = :role")
    User getUserByIdCard2(String idCard,String role);

    @Query("SELECT * FROM users WHERE phoneNumber = :phoneNumber and userRole = :role")
    User getUserByPhoneNumber(String phoneNumber, String role);
    @Query("SELECT * FROM users WHERE userRole = :role")
    List<User> getUsersByRole(String role);

    @Query("SELECT fullName FROM users WHERE idCard = :idCard")
    String getUserFullName(String idCard);

    @Query("SELECT fullName FROM users WHERE idCard = :idCard and userRole = :userRole")
    String getUserFullName2(String idCard, String userRole);

}