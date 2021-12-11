package com.example.studentmanagement;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyRepository {

    private MyDao myDao;

    public MyRepository(MyDao myDao) {
        this.myDao = myDao;
    }

    public void addStudent(StudentData data){
       new Thread(new Runnable() {
           @Override
           public void run() {
               myDao.addStudent(data);
           }
       }).start();
    }

    public void deleteStudent(StudentData data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDao.deleteStudent(data);
            }
        }).start();
    }

    public void updateStudent(StudentData data){
        new Thread(new Runnable() {
            @Override
            public void run() {
                myDao.updateStudent(data);
            }
        }).start();

    }

    public LiveData<List<StudentData>> getAllData(){
       return myDao.getAllData();
    }

}
