package com.example.studentfeedbackapp.models;

public class User {
    private int id;
    private String fullName;
    private String rollNumber;
    private String email;
    private String department;
    private String semester;
    private String password;

    public User(int id, String fullName, String rollNumber, String email, String department, String semester, String password) {
        this.id = id;
        this.fullName = fullName;
        this.rollNumber = rollNumber;
        this.email = email;
        this.department = department;
        this.semester = semester;
        this.password = password;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getRollNumber() { return rollNumber; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getSemester() { return semester; }
    public String getPassword() { return password; }
}