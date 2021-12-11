package com.example.studentmanagement;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addStudent(StudentData data);

    @Delete
    public void deleteStudent(StudentData data);

    @Update
    public void updateStudent(StudentData data);

    @Query("SELECT * FROM STUDENT_DATA order by id DESC")
    public LiveData<List<StudentData>> getAllData();


}
