package com.example.studentfeedbackapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ReportsActivity extends AppCompatActivity {

    TextView tvTotal, tvAvg, tvResolved;
    DatabaseHelper db;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        tvTotal = findViewById(R.id.tvTotalFeedbacks);
        tvAvg = findViewById(R.id.tvAvgRating);
        tvResolved = findViewById(R.id.tvResolvedIssues);
        ImageView ivBack = findViewById(R.id.ivBackReports);

        ivBack.setOnClickListener(v -> finish());

        loadReport();
    }

    private void loadReport() {
        Cursor cursor = db.getReportData(session.getUserId());
        if (cursor != null && cursor.moveToFirst()) {
            int total = cursor.getInt(0);
            float avg = cursor.getFloat(1);
            int resolved = cursor.getInt(2);

            tvTotal.setText(String.valueOf(total));
            tvAvg.setText(String.format(Locale.getDefault(), "%.1f", avg));
            tvResolved.setText(String.valueOf(resolved));
            cursor.close();
        }
    }
}