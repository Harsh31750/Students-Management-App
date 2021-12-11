package com.example.studentmanagement;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

   public LiveData<List<StudentData>> allData;


    public MyViewModel(@NonNull Application application) {
        super(application);

        MyDao dao = MyDatabase.getInstance(application).myDao();
        allData = dao.getAllData();

    }
}
