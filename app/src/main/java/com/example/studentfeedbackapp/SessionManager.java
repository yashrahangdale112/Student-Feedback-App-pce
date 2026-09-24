package com.example.studentfeedbackapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "StudentFeedbackPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_ROLL = "userRoll";
    private static final String KEY_USER_DEPT = "userDept";
    private static final String KEY_USER_SEM = "userSem";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, int userId, String name, String roll, String dept, String sem) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putInt(KEY_USER_ID, userId);
        editor.putString(KEY_USER_NAME, name);
        editor.putString(KEY_USER_ROLL, roll);
        editor.putString(KEY_USER_DEPT, dept);
        editor.putString(KEY_USER_SEM, sem);
        editor.commit();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public int getUserId() {
        return pref.getInt(KEY_USER_ID, -1);
    }

    public String getUserName() {
        return pref.getString(KEY_USER_NAME, "");
    }

    public String getUserRoll() {
        return pref.getString(KEY_USER_ROLL, "");
    }

    public String getUserDept() {
        return pref.getString(KEY_USER_DEPT, "");
    }

    public String getUserSem() {
        return pref.getString(KEY_USER_SEM, "");
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}