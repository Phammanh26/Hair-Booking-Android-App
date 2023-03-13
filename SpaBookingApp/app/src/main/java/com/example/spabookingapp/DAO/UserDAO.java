package com.example.spabookingapp.DAO;

import androidx.room.
        Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("select * from user where uid= :first")
    User findByID(String first);

    @Delete
    void delete(User product);

    @Query("delete from user")
    void deleteAll();

    @Query("select * from user where phoneNumber= :phoneNumber and password=:password")
    User login(String phoneNumber,String password);

    @Query("select * from user where phoneNumber= :phoneNumber")
    User checkExist(String phoneNumber);

    @Update
    void updateUser(User user);


}
