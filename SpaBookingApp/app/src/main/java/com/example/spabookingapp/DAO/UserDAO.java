package com.example.spabookingapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.spabookingapp.Entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("Select * from user")
    List<User> getAll();

    @Insert
    void insertAll(User... users);

    @Query("select * from user where fullname= :first")
    User findName(String first);

    @Delete
    void delete(User product);

    @Query("delete from user")
    void deleteAll();
}
