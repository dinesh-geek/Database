package com.appstone.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentDetailAdapter extends RecyclerView.Adapter<StudentDetailAdapter.StudentDetailHolder>{
    private Context context;
    private ArrayList<StudentDetail> studentDetails;
    private StudentClickListener listener;
    public StudentDetailAdapter(Context context,ArrayList<StudentDetail> studentDetails){
        this.context = context;
        this.studentDetails = studentDetails;
    }
    public void setListener(StudentClickListener listener){
        this.listener = listener;
    }
    @NonNull
    @Override
    public StudentDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cell_student,parent,false);
        StudentDetailHolder holder = new StudentDetailHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentDetailHolder holder, int position) {
        final StudentDetail currentStudentDetail = studentDetails.get(position);

        holder.mTvstudentName.setText(currentStudentDetail.getStudentName());
        holder.mTvstudentEmail.setText(currentStudentDetail.getStudentEmail());
        holder.mTvstudentYear.setText(currentStudentDetail.getStudentYear());
        holder.mTvstudentContact.setText(currentStudentDetail.getStudentContact());
        holder.mTvstudentCourse.setText(currentStudentDetail.getStudentCourse());
        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onDeleteClicked(currentStudentDetail);
                }
            }
        });
        holder.mIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUpdateClicked(currentStudentDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentDetails.size();
    }

    class StudentDetailHolder extends RecyclerView.ViewHolder{
        private TextView mTvstudentName;
        private TextView mTvstudentEmail;
        private TextView mTvstudentYear;
        private TextView mTvstudentContact;
        private TextView mTvstudentCourse;

        private ImageView mIvEdit;
        private ImageView mIvDelete;

        public StudentDetailHolder(@NonNull View itemView) {
            super(itemView);
            mTvstudentName = itemView.findViewById(R.id.tv_student_name);
            mTvstudentEmail = itemView.findViewById(R.id.tv_student_email);
            mTvstudentYear = itemView.findViewById(R.id.tv_student_year);
            mTvstudentContact = itemView.findViewById(R.id.tv_student_contact);
            mTvstudentCourse = itemView.findViewById(R.id.tv_student_course);
            mIvDelete = itemView.findViewById(R.id.iv_delete);
            mIvEdit = itemView.findViewById(R.id.iv_edit);
        }
    }
    public interface StudentClickListener{
        void onUpdateClicked(StudentDetail studentDetail);
        void onDeleteClicked(StudentDetail studentDetail);
    }
}
