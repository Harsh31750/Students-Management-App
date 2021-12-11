package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnStudentItemsClickListener {

    private MyViewModel myViewModel;
    private MyRepository repository;
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private ArrayList<StudentData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // todo create entity class
        // todo create DAO interface
        // todo create Database class
        // todo create repository class
        // todo create viewmodel class

        init();

        myViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(MyViewModel.class);
        MyDao dao = MyDatabase.getInstance(getApplicationContext()).myDao();
        repository = new MyRepository(dao);

        myViewModel.allData.observe(this, new Observer<List<StudentData>>() {
            @Override
            public void onChanged(List<StudentData> studentData) {
                arrayList.clear();
                adapter.notifyDataSetChanged();

                 arrayList.addAll(studentData);
                 adapter.notifyDataSetChanged();
            }
        });

    }



    private void init(){
        recyclerView = findViewById(R.id.recycler_view);
        arrayList = new ArrayList<>();
        adapter = new StudentAdapter(arrayList,this);
        fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDeleteClickListener(int position) {
        StudentData data = arrayList.get(position);
        repository.deleteStudent(data);
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEditClickListener(int position) {
        StudentData data = arrayList.get(position);
        Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
        intent.putExtra("myKey",data);
        startActivity(intent);

    }
}