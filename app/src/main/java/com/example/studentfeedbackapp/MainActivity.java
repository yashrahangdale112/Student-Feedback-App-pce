package com.example.studentfeedbackapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 

        session = new SessionManager(this);

        ImageView logo = findViewById(R.id.ivSplashLogo);
        TextView welcome = findViewById(R.id.tvSplashWelcome);
        TextView title = findViewById(R.id.tvSplashTitle);

        logo.setAlpha(0f);
        logo.setScaleX(0.5f);
        logo.setScaleY(0.5f);

        logo.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(1000).withEndAction(() -> {
            welcome.animate().alpha(1f).translationY(0).setDuration(800).start();
            title.animate().alpha(1f).setDuration(800).setStartDelay(400).start();
        }).start();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (session.isLoggedIn()) {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000); 
    }
}
