package com.example.studentfeedbackapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    TextView tvWelcome, tvUserDetail, tvAnnouncements, tvFeedbackCount, tvMotto;
    CardView cardGiveFeedback, cardMyFeedback, cardReports, cardAbout, cardAnnouncement, profileLayout;
    GridLayout gridLayout;
    ImageView ivLogout, ivMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    SessionManager session;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        session = new SessionManager(this);
        db = new DatabaseHelper(this);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvUserDetail = findViewById(R.id.tvUserDetail);
        tvAnnouncements = findViewById(R.id.tvAnnouncements);
        tvMotto = findViewById(R.id.tvMotto);
        tvFeedbackCount = findViewById(R.id.tvFeedbackCount);
        cardGiveFeedback = findViewById(R.id.cardGiveFeedback);
        cardMyFeedback = findViewById(R.id.cardMyFeedback);
        cardReports = findViewById(R.id.cardReports);
        cardAbout = findViewById(R.id.cardAbout);
        cardAnnouncement = findViewById(R.id.cardAnnouncement);
        ivLogout = findViewById(R.id.ivLogout);
        ivMenu = findViewById(R.id.ivMenu);
        profileLayout = findViewById(R.id.profileLayout);
        gridLayout = findViewById(R.id.gridLayoutContainer);

        // Set Nav Header Data
        View headerView = navigationView.getHeaderView(0);
        TextView navName = headerView.findViewById(R.id.tvNavName);
        TextView navRoll = headerView.findViewById(R.id.tvNavRoll);
        navName.setText(session.getUserName());
        navRoll.setText("Roll No: " + session.getUserRoll());

        String name = session.getUserName();
        tvWelcome.setText("Hello, " + name + "!");

        String detail = session.getUserDept() + " | " + session.getUserSem();
        tvUserDetail.setText(detail);

        int count = db.getFeedbackCount(session.getUserId());
        tvFeedbackCount.setText("Total Feedbacks: " + count);

        // Menu Toggle
        ivMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // NavigationView Item Clicks
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_dashboard) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else if (id == R.id.nav_give_feedback) {
                startActivity(new Intent(this, GiveFeedbackActivity.class));
            } else if (id == R.id.nav_my_feedback) {
                startActivity(new Intent(this, MyFeedbackActivity.class));
            } else if (id == R.id.nav_reports) {
                startActivity(new Intent(this, ReportsActivity.class));
            } else if (id == R.id.nav_about) {
                startActivity(new Intent(this, AboutActivity.class));
            } else if (id == R.id.nav_logout) {
                logout();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Load Animations
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        profileLayout.startAnimation(fadeIn);
        gridLayout.startAnimation(slideUp);

        // Ticker effect simulation
        tvAnnouncements.setSelected(true);

        View.OnClickListener openNoticesListener = v -> {
            startActivity(new Intent(DashboardActivity.this, NoticesActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        };

        findViewById(R.id.tvViewAllAnnouncements).setOnClickListener(openNoticesListener);
        if (cardAnnouncement != null) {
            cardAnnouncement.setOnClickListener(openNoticesListener);
        }

        cardGiveFeedback.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, GiveFeedbackActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        cardMyFeedback.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, MyFeedbackActivity.class));
        });

        cardReports.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, ReportsActivity.class));
        });

        cardAbout.setOnClickListener(v -> {
            startActivity(new Intent(DashboardActivity.this, AboutActivity.class));
        });

        ivLogout.setOnClickListener(v -> logout());
    }

    private void logout() {
        session.logoutUser();
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
