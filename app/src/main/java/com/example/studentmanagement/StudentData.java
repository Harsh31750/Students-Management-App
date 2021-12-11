package com.example.studentmanagement;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_data")
public class StudentData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
  private int id;

    @ColumnInfo(name = "student_name")
    private String name;

    private String lastName;

    private int studentClass;

    public StudentData(String name, String lastName, int studentClass) {
        this.name = name;
        this.lastName = lastName;
        this.studentClass = studentClass;
    }

    protected StudentData(Parcel in) {
        id = in.readInt();
        name = in.readString();
        lastName = in.readString();
        studentClass = in.readInt();
    }

    public static final Creator<StudentData> CREATOR = new Creator<StudentData>() {
        @Override
        public StudentData createFromParcel(Parcel in) {
            return new StudentData(in);
        }

        @Override
        public StudentData[] newArray(int size) {
            return new StudentData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(int studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeInt(studentClass);
    }
}
