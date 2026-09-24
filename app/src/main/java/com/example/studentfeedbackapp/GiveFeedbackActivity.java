package com.example.studentfeedbackapp;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GiveFeedbackActivity extends AppCompatActivity {

    Spinner spDept, spSem, spType, spSubject;
    RatingBar ratingBar;
    EditText etComment;
    Button btnSubmit;
    ImageView ivBack;
    TextView tvDeptTitle, tvSubjectLabel;
    DatabaseHelper db;
    SessionManager session;

    private static final String[] DEPARTMENTS = {
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

    private static final String[] SEMESTERS = {
            "Semester 1", "Semester 2", "Semester 3", "Semester 4",
            "Semester 5", "Semester 6", "Semester 7", "Semester 8"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_feedback);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        spDept = findViewById(R.id.spDepartment);
        spSem = findViewById(R.id.spSemester);
        spType = findViewById(R.id.spFeedbackType);
        spSubject = findViewById(R.id.spSubject);
        ratingBar = findViewById(R.id.ratingBar);
        etComment = findViewById(R.id.etFeedbackComment);
        btnSubmit = findViewById(R.id.btnSubmitFeedback);
        ivBack = findViewById(R.id.ivBackFeedback);
        tvDeptTitle = findViewById(R.id.tvDeptTitle);
        tvSubjectLabel = findViewById(R.id.tvSubjectLabel);

        String initialDept = session.getUserDept();
        String initialSem = session.getUserSem();
        if (initialDept.isEmpty()) initialDept = "Industrial IoT";
        if (initialSem.isEmpty()) initialSem = "Semester 7";

        // Animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        findViewById(android.R.id.content).startAnimation(fadeIn);

        setupFeedbackTypeSpinner();
        setupDepartmentAndSemesterSpinners(initialDept, initialSem);

        ivBack.setOnClickListener(v -> finish());

        btnSubmit.setOnClickListener(v -> {
            String type = spType.getSelectedItem().toString();
            String subject = spSubject.getSelectedItem() != null ? spSubject.getSelectedItem().toString() : "";
            float rating = ratingBar.getRating();
            String comment = etComment.getText().toString().trim();
            String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

            if (subject.isEmpty() || subject.startsWith("Select")) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            if (comment.isEmpty()) {
                Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean success = db.insertFeedback(session.getUserId(), type, subject, rating, comment, date);
            if (success) {
                Toast.makeText(this, "Feedback Submitted Successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDepartmentAndSemesterSpinners(String defaultDept, String defaultSem) {
        ArrayAdapter<String> deptAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DEPARTMENTS);
        deptAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDept.setAdapter(deptAdapter);

        int defaultDeptIndex = 0;
        for (int i = 0; i < DEPARTMENTS.length; i++) {
            if (DEPARTMENTS[i].equalsIgnoreCase(defaultDept) ||
                (defaultDept.toLowerCase().contains("iot") && DEPARTMENTS[i].toLowerCase().contains("iot"))) {
                defaultDeptIndex = i;
                break;
            }
        }
        spDept.setSelection(defaultDeptIndex);

        ArrayAdapter<String> semAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SEMESTERS);
        semAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSem.setAdapter(semAdapter);

        int defaultSemIndex = 0;
        for (int i = 0; i < SEMESTERS.length; i++) {
            if (SEMESTERS[i].equalsIgnoreCase(defaultSem) ||
                (defaultSem.contains("7") && SEMESTERS[i].contains("7"))) {
                defaultSemIndex = i;
                break;
            }
        }
        spSem.setSelection(defaultSemIndex);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubjectsList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };

        spDept.setOnItemSelectedListener(listener);
        spSem.setOnItemSelectedListener(listener);
        spType.setOnItemSelectedListener(listener);

        updateSubjectsList();
    }

    private void updateSubjectsList() {
        String selectedDept = spDept.getSelectedItem() != null ? spDept.getSelectedItem().toString() : "";
        String selectedSem = spSem.getSelectedItem() != null ? spSem.getSelectedItem().toString() : "";
        String selectedType = spType.getSelectedItem() != null ? spType.getSelectedItem().toString() : "Faculty Feedback";

        tvDeptTitle.setText("Department: " + selectedDept + " | " + selectedSem);

        List<String> options = new ArrayList<>();

        switch (selectedType) {
            case "Faculty Feedback":
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Subject & Faculty");
                options.add("Select Subject & Faculty");
                options.addAll(SubjectRepository.getSubjectsWithFaculty(selectedDept, selectedSem));
                break;

            case "Lab Facilities":
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Laboratory");
                options.add("Select Laboratory");
                List<String> rawSubjects = SubjectRepository.getSubjectsWithFaculty(selectedDept, selectedSem);
                for (String sub : rawSubjects) {
                    String labName = sub.split(" - ")[0] + " Lab";
                    options.add(labName);
                }
                break;

            case "Library":
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Library Category");
                options.add("Select Library Category");
                options.add("Book Availability & Variety");
                options.add("Digital Library & E-Journals");
                options.add("Reading Room & Environment");
                options.add("Book Issue / Return Process");
                options.add("Library Staff Assistance");
                break;

            case "Canteen":
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Canteen Category");
                options.add("Select Canteen Category");
                options.add("Food Quality & Taste");
                options.add("Hygiene & Cleanliness");
                options.add("Pricing & Quantity");
                options.add("Seating Arrangement");
                options.add("Service Speed & Staff");
                break;

            case "Hostel":
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Hostel Category");
                options.add("Select Hostel Category");
                options.add("Room Maintenance & Cleanliness");
                options.add("Mess Food Quality & Hygiene");
                options.add("Water & Electricity Supply");
                options.add("Wi-Fi & Internet Speed");
                options.add("Security & Discipline");
                break;

            case "Infrastructure":
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Infrastructure Category");
                options.add("Select Infrastructure Category");
                options.add("Classroom Furniture & Projectors");
                options.add("Washroom Hygiene & Water");
                options.add("Campus Wi-Fi & IT Support");
                options.add("Sports & Gym Facilities");
                options.add("Campus Cleanliness & Safety");
                break;

            default:
                if (tvSubjectLabel != null) tvSubjectLabel.setText("Select Option");
                options.add("Select Option");
                options.add("General Facility Feedback");
                break;
        }

        ArrayAdapter<String> subAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        subAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSubject.setAdapter(subAdapter);
    }

    private void setupFeedbackTypeSpinner() {
        String[] types = {"Faculty Feedback", "Lab Facilities", "Library", "Canteen", "Hostel", "Infrastructure"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(typeAdapter);
    }
}
