package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {


    EditText name,lastName,stu_class;
    Button save;
    MyRepository repository;
    StudentData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student_actrivity);
        init();
        setUP();
    }

    private void init(){
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.last_name);
        stu_class = findViewById(R.id.stu_class);
        save = findViewById(R.id.save);

        repository = new MyRepository(MyDatabase.getInstance(getApplicationContext()).myDao());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data==null){
                    // add data to database
                    addDataToDatabase();
                }else {
                    // update data
                 updateData();
                }

            }


        });
    }

    private void setUP(){
        Intent intent = getIntent();
      data = intent.getParcelableExtra("myKey");
        if (data!=null){
            name.setText(data.getName());
            lastName.setText(data.getLastName());
            stu_class.setText(String.valueOf(data.getStudentClass()));
        }


    }

    private void addDataToDatabase() {
        String name_ = name.getText().toString();
        String last_name = lastName.getText().toString();
        String user_class = stu_class.getText().toString();

        StudentData data = new StudentData(name_,last_name,Integer.parseInt(user_class));
        repository.addStudent(data);
        Toast.makeText(getApplicationContext(),"Student added",Toast.LENGTH_SHORT).show();

        name.setText("");
        lastName.setText("");
        stu_class.setText("");
    }

    private void updateData(){
        String name_ = name.getText().toString();
        String last_name = lastName.getText().toString();
        String user_class = stu_class.getText().toString();

        data.setName(name_);
        data.setLastName(last_name);
        data.setStudentClass(Integer.parseInt(user_class));

        repository.updateStudent(data);
        Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show();
        finish();


    }
}