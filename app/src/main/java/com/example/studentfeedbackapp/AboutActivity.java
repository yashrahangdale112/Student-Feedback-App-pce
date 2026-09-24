package com.example.studentfeedbackapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ImageView ivBack = findViewById(R.id.ivBackAbout);
        ivBack.setOnClickListener(v -> finish());
    }
}