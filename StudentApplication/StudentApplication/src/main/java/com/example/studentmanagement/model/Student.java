package com.example.studentmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.IndexColumn;


@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @IndexColumn(name = "student_id_index")
    private Long studentId;
    private String name;
    private String grade;
    private String mobileNumber;
    private String schoolName;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String name, Long studentId, String grade, String mobileNumber, String schoolName) {
        this.name = name;
        this.studentId = studentId; // Initialize studentId
        this.grade = grade;
        this.mobileNumber = mobileNumber;
        this.schoolName = schoolName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", schoolName='" + schoolName + '\'' +
                '}';
    }
}
