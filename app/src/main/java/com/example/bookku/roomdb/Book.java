package com.example.bookku.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tBook")
public class Book implements Serializable {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "img_cover")
    private String img_cover;

    @ColumnInfo(name = "judul")
    private String judul;

    @ColumnInfo(name = "deskripsi")
    private String deskripsi;

    @ColumnInfo(name = "file")
    private String file;

    public String getImg_cover() {
        return img_cover;
    }

    public void setImg_cover(String img_cover) {
        this.img_cover = img_cover;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
