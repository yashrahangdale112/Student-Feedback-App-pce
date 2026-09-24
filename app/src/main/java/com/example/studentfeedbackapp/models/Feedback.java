package com.example.studentfeedbackapp.models;

public class Feedback {
    private int id;
    private int userId;
    private String type;
    private String subject;
    private float rating;
    private String comment;
    private String status;
    private String date;

    public Feedback(int id, int userId, String type, String subject, float rating, String comment, String status, String date) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.subject = subject;
        this.rating = rating;
        this.comment = comment;
        this.status = status;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getType() { return type; }
    public String getSubject() { return subject; }
    public float getRating() { return rating; }
    public String getComment() { return comment; }
    public String getStatus() { return status; }
    public String getDate() { return date; }
}