package com.example.myapplication.data;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import android.content.Context;
import com.example.myapplication.data.dao.AppointmentDao;
import com.example.myapplication.data.dao.UserDao;
import com.example.myapplication.data.dao.PrescriptionDao;
import com.example.myapplication.data.entities.Appointment;
import com.example.myapplication.data.entities.User;
import com.example.myapplication.data.dao.HistoryDao;
import com.example.myapplication.data.entities.History;
import com.example.myapplication.data.entities.Prescription;
@Database(entities = {User.class, Appointment.class, Prescription.class, History.class}, version = 6,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract AppointmentDao appointmentDao();
    public abstract PrescriptionDao prescriptionDao();
    public abstract HistoryDao historyDao();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "my_database"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

}
