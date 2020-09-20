package com.appstone.database;

import java.io.Serializable;

public class StudentDetail implements Serializable {
    private int studentID;
    private String studentName;
    private String studentEmail;
    private String studentContact;
    private String studentCourse;
    private String studentYear;

    public StudentDetail(){

    }
public StudentDetail(String studentName, String studentEmail, String studentContact, String studentCourse, String studentYear){
        this.studentName= studentName;
    this.studentCourse= studentCourse;
    this.studentEmail= studentEmail;
    this.studentContact= studentContact;
    this.studentYear= studentYear;
}
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentContact() {
        return studentContact;
    }

    public void setStudentContact(String studentContact) {
        this.studentContact = studentContact;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(String studentYear) {
        this.studentYear = studentYear;
    }
/*  public int studentID;
    public String studentName;
    public String studentEmail;
    public String studentContact;
    public String studentCourse;
    public String studentYear; if public is written then we can add the values of studen in mainactivity .java*/


}
