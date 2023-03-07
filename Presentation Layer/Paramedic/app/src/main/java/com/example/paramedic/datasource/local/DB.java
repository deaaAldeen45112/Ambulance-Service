package com.example.paramedic.datasource.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.paramedic.model.userlogin.UserLogin;

@Database(entities = { UserLogin.class} ,version = 10,exportSchema = false)
public abstract class DB extends RoomDatabase {

    public  abstract PekemonDao pekemonDao();
    public  abstract UserLoginDao userLoginDao();




}
