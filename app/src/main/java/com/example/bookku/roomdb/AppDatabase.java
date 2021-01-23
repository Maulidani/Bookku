package com.example.bookku.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    //Untuk mengakses Database menggunakan method abstract
    public abstract BookDAO bookDAO();
}
