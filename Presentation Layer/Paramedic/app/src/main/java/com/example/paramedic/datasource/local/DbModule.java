package com.example.paramedic.datasource.local;

import android.app.Application;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DbModule {

    @Provides
    @Singleton
    public static DB provideDB(Application application) {
        return Room.databaseBuilder(application, DB.class, "fav_DB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }

    @Provides
    @Singleton
    public static PekemonDao pekemonDao(DB db) {
        return db.pekemonDao();
    }

    @Provides
    @Singleton
    public static UserLoginDao userLoginDao(DB db) {
        return db.userLoginDao();
    }

}
