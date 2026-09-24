package com.example.studentfeedbackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "StudentFeedback.db";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_FEEDBACK = "feedback";

    // Common column names
    private static final String KEY_ID = "id";

    // USERS Table - column names
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_ROLL_NUMBER = "roll_number";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_PASSWORD = "password";

    // FEEDBACK Table - column names
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_RATING = "rating";
    private static final String KEY_COMMENT = "comment";
    private static final String KEY_STATUS = "status";
    private static final String KEY_DATE = "date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_ROLL_NUMBER + " TEXT UNIQUE,"
                + KEY_EMAIL + " TEXT,"
                + KEY_MOBILE + " TEXT,"
                + KEY_DEPARTMENT + " TEXT,"
                + KEY_SEMESTER + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        // Create Feedback Table
        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USER_ID + " INTEGER,"
                + KEY_TYPE + " TEXT,"
                + KEY_SUBJECT + " TEXT,"
                + KEY_RATING + " REAL,"
                + KEY_COMMENT + " TEXT,"
                + KEY_STATUS + " TEXT,"
                + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_FEEDBACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        onCreate(db);
    }

    // --- USER OPERATIONS ---

    public boolean registerUser(String name, String roll, String email, String mobile, String dept, String sem, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FULL_NAME, name);
        values.put(KEY_ROLL_NUMBER, roll);
        values.put(KEY_EMAIL, email);
        values.put(KEY_MOBILE, mobile);
        values.put(KEY_DEPARTMENT, dept);
        values.put(KEY_SEMESTER, sem);
        values.put(KEY_PASSWORD, pass);

        long id = db.insert(TABLE_USERS, null, values);
        return id != -1;
    }

    public Cursor loginUser(String roll, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ROLL_NUMBER + "=? AND " + KEY_PASSWORD + "=?", new String[]{roll, pass});
    }

    // --- FEEDBACK OPERATIONS ---

    public boolean insertFeedback(int userId, String type, String subject, float rating, String comment, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, userId);
        values.put(KEY_TYPE, type);
        values.put(KEY_SUBJECT, subject);
        values.put(KEY_RATING, rating);
        values.put(KEY_COMMENT, comment);
        values.put(KEY_STATUS, "Submitted"); // Default status
        values.put(KEY_DATE, date);

        long id = db.insert(TABLE_FEEDBACK, null, values);
        return id != -1;
    }

    public Cursor getUserFeedback(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FEEDBACK + " WHERE " + KEY_USER_ID + "=? ORDER BY " + KEY_ID + " DESC", new String[]{String.valueOf(userId)});
    }

    public Cursor getUserFeedbackByStatus(int userId, String status) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_FEEDBACK + " WHERE " + KEY_USER_ID + "=? AND " + KEY_STATUS + "=? ORDER BY " + KEY_ID + " DESC", new String[]{String.valueOf(userId), status});
    }

    public Cursor getReportData(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT COUNT(*), AVG(" + KEY_RATING + "), " +
                "COUNT(CASE WHEN " + KEY_STATUS + "='Resolved' THEN 1 END) " +
                "FROM " + TABLE_FEEDBACK + " WHERE " + KEY_USER_ID + "=?", new String[]{String.valueOf(userId)});
    }

    public int getFeedbackCount(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_FEEDBACK + " WHERE " + KEY_USER_ID + "=?", new String[]{String.valueOf(userId)});
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        return count;
    }
}
