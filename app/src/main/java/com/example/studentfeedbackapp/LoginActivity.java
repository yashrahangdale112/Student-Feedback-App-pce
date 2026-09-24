package com.example.studentfeedbackapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    EditText etRoll, etPassword;
    TextInputLayout tilRoll, tilPass;
    Button btnLogin;
    TextView tvGoToRegister, tvTitle, tvSubtitle;
    ImageView ivLogo;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        etRoll = findViewById(R.id.etLoginRoll);
        etPassword = findViewById(R.id.etLoginPassword);
        tilRoll = findViewById(R.id.tilRoll);
        tilPass = findViewById(R.id.tilPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);
        tvTitle = findViewById(R.id.tvTitle);
        tvSubtitle = findViewById(R.id.tvSubtitle);
        ivLogo = findViewById(R.id.ivLogo);

        // Animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        ivLogo.startAnimation(fadeIn);
        tvTitle.startAnimation(fadeIn);
        tvSubtitle.startAnimation(fadeIn);
        
        tilRoll.startAnimation(slideUp);
        tilPass.startAnimation(slideUp);
        btnLogin.startAnimation(slideUp);
        tvGoToRegister.startAnimation(slideUp);

        btnLogin.setOnClickListener(v -> {
            String roll = etRoll.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (roll.isEmpty()) {
                tilRoll.setError("Roll number is required");
                return;
            } else {
                tilRoll.setError(null);
            }

            if (pass.isEmpty()) {
                tilPass.setError("Password is required");
                return;
            } else {
                tilPass.setError(null);
            }

            Cursor cursor = db.loginUser(roll, pass);
            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dept = cursor.getString(4);
                String sem = cursor.getString(5);
                
                session.setLogin(true, id, name, roll, dept, sem);
                
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                Toast.makeText(this, "Invalid Roll Number or Password", Toast.LENGTH_SHORT).show();
            }
            if (cursor != null) cursor.close();
        });

        tvGoToRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        findViewById(R.id.btnDevDashboard).setOnClickListener(v -> {
            // Demo Session
            session.setLogin(true, 0, "Guest User", "G101", "Demo Dept", "Sem 1");
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        });
    }
}
