package com.appstone.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements StudentDetailAdapter.StudentClickListener {
    private DBhelper dBhelper;
    private RecyclerView mRcStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mRcStudentList = findViewById(R.id.rc_student_data);
         /* recycler view  behaves in three ways
        1.linear
        2.grid
        3.staggered grid
         */
        mRcStudentList.setLayoutManager(new LinearLayoutManager(ViewActivity.this, RecyclerView.VERTICAL, false));

        // syntax for grid  mRcStudentList.setLayoutManager(new GridLayoutManager(ViewActivity.this,3));
        //syntax for staggered grid    mRcStudentList.setLayoutManager(new StaggeredGridLayoutManager(2,RecyclerView.VERTICAL));
        dBhelper = new DBhelper(ViewActivity.this);
        loadDatatoDatabase();
    }

    private void loadDatatoDatabase() {
        ArrayList<StudentDetail> details = dBhelper.getDataFromDatabase(dBhelper.getWritableDatabase());

        StudentDetailAdapter adapter = new StudentDetailAdapter(ViewActivity.this, details);
        adapter.setListener(this);
        mRcStudentList.setAdapter(adapter);
    }

    @Override
    public void onUpdateClicked(StudentDetail studentDetail) {
        Intent updateIntent = new Intent(ViewActivity.this, MainActivity.class);
        updateIntent.putExtra("student", studentDetail);
        updateIntent.putExtra("is_update", true);
        startActivityForResult(updateIntent, 1000);
    }

    @Override
    public void onDeleteClicked(StudentDetail studentDetail) {
        dBhelper.deleteDatafromDatabase(dBhelper.getWritableDatabase(), studentDetail);
        loadDatatoDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            loadDatatoDatabase();
        }
    }
}