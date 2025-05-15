package com.example.myapplication;
import androidx.room.Room;
import android.app.Application;
import android.content.Context;
import com.example.myapplication.data.AppDatabase;
public class MyApplication extends Application {
    private static MyApplication instance;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "my_database"
        ).allowMainThreadQueries() .fallbackToDestructiveMigration().build();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}