package com.example.myapplication.data;

import android.content.Context;
import androidx.room.Room;
import com.example.myapplication.R;
public class DatabaseClient {

    private Context context;
    private static DatabaseClient instance;

    // 数据库对象
    private AppDatabase appDatabase;

    private DatabaseClient(Context context) {
        this.context = context;

        // 创建数据库
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "my_database")
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}