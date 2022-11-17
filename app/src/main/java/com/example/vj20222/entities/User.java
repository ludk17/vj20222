package com.example.vj20222.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "avatar")
    public String avatar;
    @ColumnInfo(name = "edad")
    public int edad;
    @ColumnInfo(name = "createdAt")
    public String createdAt;
}
