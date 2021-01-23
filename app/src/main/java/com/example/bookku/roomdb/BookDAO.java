package com.example.bookku.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
@Dao
public interface BookDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertBook(Book book);

    @Query("SELECT * FROM TBOOK")
    Book[] readDataBook();

    @Delete
    void deleteBook(Book book);
}
