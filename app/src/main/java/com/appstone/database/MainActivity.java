package com.appstone.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEtStudentName;
    private EditText mEtStudentEmail;
    private EditText mEtStudentContact;
    private EditText mEtStudentYear;
    private EditText mEtStudentCourse;

    private DBhelper dbHelper;
    private boolean isUpdate;
    private int studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEtStudentName = findViewById(R.id.et_student_name);
        mEtStudentEmail = findViewById(R.id.et_student_mail);
        mEtStudentContact = findViewById(R.id.et_student_phone);
        mEtStudentYear = findViewById(R.id.et_student_year);
        mEtStudentCourse = findViewById(R.id.et_student_course);
        Button mBtnEnterdata = findViewById(R.id.btn_enter_student);

        Bundle data =getIntent().getExtras();
        if(data!=null){
        isUpdate = data.getBoolean("is_update");
        StudentDetail studentDetail = (StudentDetail) data.getSerializable("student");
        mEtStudentName.setText(studentDetail.getStudentName());
            mEtStudentEmail.setText(studentDetail.getStudentEmail());
            mEtStudentContact.setText(studentDetail.getStudentContact());
            mEtStudentCourse.setText(studentDetail.getStudentCourse());
            mEtStudentYear.setText(studentDetail.getStudentYear());
            studentID = studentDetail.getStudentID();
            mBtnEnterdata.setText("Update Student");
        }
        dbHelper = new DBhelper(MainActivity.this);
    }
    public void onViewStudentClicked(View view){
        startActivity(new Intent(MainActivity.this,ViewActivity.class));
    }
    public void onEnterClicked(View view){
        String studentName = mEtStudentName.getText().toString();
        String studentEmail = mEtStudentEmail.getText().toString();
        String studentYear = mEtStudentYear.getText().toString();
        String studentContact = mEtStudentContact.getText().toString();
        String studentCourse = mEtStudentCourse.getText().toString();

        StudentDetail newStudent =  new StudentDetail();
        //newStudent.studentName = studentName();
        newStudent.setStudentEmail(studentEmail);
        newStudent.setStudentName(studentName);
        newStudent.setStudentContact(studentContact);
        newStudent.setStudentYear(studentYear);
        newStudent.setStudentCourse(studentCourse);

        mEtStudentName.setText("");
        mEtStudentCourse.setText("");
        mEtStudentContact.setText("");
        mEtStudentYear.setText("");
        mEtStudentEmail.setText("");
        if(isUpdate){
newStudent.setStudentID(studentID);
dbHelper.updateDataToDatabase(dbHelper.getWritableDatabase(),newStudent);
setResult(Activity.RESULT_OK);
finish();
        }else {
            dbHelper.insertDatatoDatabase(dbHelper.getWritableDatabase(),newStudent);
        }


    }
}