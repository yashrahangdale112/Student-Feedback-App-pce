package com.example.studentfeedbackapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etRoll, etEmail, etMobile, etPassword;
    TextInputLayout tilName, tilRoll, tilEmail, tilMobile, tilPass;
    Spinner spDept, spSem;
    Button btnRegister;
    TextView tvGoToLogin, tvTitle, tvSubtitle;
    ImageView ivLogo;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        etName = findViewById(R.id.etRegName);
        etRoll = findViewById(R.id.etRegRoll);
        etEmail = findViewById(R.id.etRegEmail);
        etMobile = findViewById(R.id.etRegMobile);
        etPassword = findViewById(R.id.etRegPassword);
        tilName = findViewById(R.id.tilRegName);
        tilRoll = findViewById(R.id.tilRegRoll);
        tilEmail = findViewById(R.id.tilRegEmail);
        tilMobile = findViewById(R.id.tilRegMobile);
        tilPass = findViewById(R.id.tilRegPass);
        spDept = findViewById(R.id.spDepartment);
        spSem = findViewById(R.id.spSemester);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);
        tvTitle = findViewById(R.id.tvTitleReg);
        tvSubtitle = findViewById(R.id.tvSubtitleReg);
        ivLogo = findViewById(R.id.ivLogoReg);

        // Animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        ivLogo.startAnimation(fadeIn);
        tvTitle.startAnimation(fadeIn);
        tvSubtitle.startAnimation(fadeIn);
        
        btnRegister.startAnimation(slideUp);

        // Name Typing Animation
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    ivLogo.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100).withEndAction(() -> 
                        ivLogo.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100)
                    );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup Spinners
        String[] departments = {
            "Select Department", 
            "Computer Science & Engineering", 
            "Information Technology", 
            "Computer Technology",
            "Aeronautical Engineering", 
            "AI & Data Science",
            "Robotics & AI",
            "Electronics & Telecommunication", 
            "Electrical Engineering",
            "Mechanical Engineering", 
            "Civil Engineering",
            "Chemical Engineering", 
            "Industrial IoT"
        };
        String[] semesters = {"Select Semester", "Semester 1", "Semester 2", "Semester 3", "Semester 4", "Semester 5", "Semester 6", "Semester 7", "Semester 8"};

        ArrayAdapter<String> deptAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDept.setAdapter(deptAdapter);

        ArrayAdapter<String> semAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, semesters);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSem.setAdapter(semAdapter);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String roll = etRoll.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String mobile = etMobile.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            String dept = spDept.getSelectedItem().toString();
            String sem = spSem.getSelectedItem().toString();

            if (name.isEmpty()) { 
                tilName.setError("Name is required"); 
                return; 
            } else { tilName.setError(null); }

            if (roll.isEmpty()) { 
                tilRoll.setError("Roll number is required"); 
                return; 
            } else { tilRoll.setError(null); }

            if (email.isEmpty()) {
                tilEmail.setError("Email is required");
                return;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.setError("Please enter a valid email address (e.g., student@example.com)");
                return;
            } else { tilEmail.setError(null); }

            if (mobile.isEmpty()) {
                tilMobile.setError("Phone number is required");
                return;
            } else if (mobile.length() < 10) {
                tilMobile.setError("Enter a valid 10-digit phone number");
                return;
            } else { tilMobile.setError(null); }

            if (pass.isEmpty()) { 
                tilPass.setError("Password is required"); 
                return; 
            } else if (pass.length() < 6) {
                tilPass.setError("Password must be at least 6 characters");
                return;
            } else { tilPass.setError(null); }
            
            if (dept.equals("Select Department") || sem.equals("Select Semester")) {
                Toast.makeText(this, "Please select Department and Semester", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean registered = db.registerUser(name, roll, email, mobile, dept, sem, pass);
            if (registered) {
                Toast.makeText(this, "Registration Successful! Please login.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            } else {
                Toast.makeText(this, "Registration Failed (Roll Number may exist)", Toast.LENGTH_SHORT).show();
            }
        });

        tvGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }
}