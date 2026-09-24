package com.example.studentfeedbackapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentfeedbackapp.adapters.FeedbackAdapter;
import com.example.studentfeedbackapp.models.Feedback;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyFeedbackActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FeedbackAdapter adapter;
    List<Feedback> feedbackList;
    DatabaseHelper db;
    SessionManager session;
    ImageView ivBack;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);

        db = new DatabaseHelper(this);
        session = new SessionManager(this);

        ivBack = findViewById(R.id.ivBackMyFeedback);
        recyclerView = findViewById(R.id.rvMyFeedback);
        tabLayout = findViewById(R.id.tabLayout);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ivBack.setOnClickListener(v -> finish());

        setupTabs();
        loadFeedback("All");
    }

    private void setupTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Submitted"));
        tabLayout.addTab(tabLayout.newTab().setText("Under Review"));
        tabLayout.addTab(tabLayout.newTab().setText("Resolved"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                loadFeedback(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadFeedback(String status) {
        feedbackList = new ArrayList<>();
        Cursor cursor;
        if (status.equals("All")) {
            cursor = db.getUserFeedback(session.getUserId());
        } else {
            cursor = db.getUserFeedbackByStatus(session.getUserId(), status);
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Feedback f = new Feedback(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getFloat(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                );
                feedbackList.add(f);
            } while (cursor.moveToNext());
            cursor.close();
        }

        adapter = new FeedbackAdapter(feedbackList);
        recyclerView.setAdapter(adapter);

        if (feedbackList.isEmpty()) {
            Toast.makeText(this, "No feedback found for " + status, Toast.LENGTH_SHORT).show();
        }
    }
}
