package com.example.vj20222.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vj20222.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users where id = :abc")
    User find(int abc); //1,2,3,4,5

    @Insert
    void create(User user);

    @Update
    void update(User user);

}
