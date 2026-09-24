package com.example.studentfeedbackapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class NoticesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        ImageView ivBack = findViewById(R.id.ivBackNotices);
        ivBack.setOnClickListener(v -> finish());

        RecyclerView rv = findViewById(R.id.rvNotices);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Notice> notices = new ArrayList<>();
        notices.add(new Notice(
                "Semester 7 Faculty & Course Feedback 2026",
                "25 Sep 2026",
                "ACADEMIC",
                "Dear Students, the Mid-Semester Feedback for Semester 7 (including MDWD, DIOT, Python for Data Science, WSN, and Big Data Analytics) is now active. Please submit your feedback before 30th September 2026."
        ));
        notices.add(new Notice(
                "Industrial IoT & Data Science Lab Upgrades",
                "20 Sep 2026",
                "LABORATORY",
                "High-performance IoT Kits, Edge Computing nodes, and Data Science GPU Workstations have been installed in the Industrial IoT lab. Students are requested to test facilities and share feedback."
        ));
        notices.add(new Notice(
                "Library E-Portal & Digital Resources 2026",
                "15 Sep 2026",
                "LIBRARY",
                "The college digital library portal now offers free access to IEEE journals, ScienceDirect e-books, and technical research databases. Share your experience under the Library feedback section."
        ));
        notices.add(new Notice(
                "Campus Canteen Hygiene & Menu Survey",
                "10 Sep 2026",
                "CANTEEN",
                "A fresh nutritious menu and automated billing system have been introduced in the main campus canteen. Please rate canteen hygiene, food quality, and service in your feedback."
        ));
        notices.add(new Notice(
                "Hostel Facilities & Wi-Fi Network Maintenance",
                "05 Sep 2026",
                "HOSTEL",
                "High-speed optical fiber Wi-Fi access points have been deployed across all student hostel blocks. Report any connectivity or maintenance feedback through the app portal."
        ));
        notices.add(new Notice(
                "End-Semester Examination & Evaluation Portal",
                "01 Sep 2026",
                "EXAMINATIONS",
                "The end-semester exam schedule and practical lab assessment guidelines for 2026 have been published on the notice board. Feedback submissions are open."
        ));

        NoticesAdapter adapter = new NoticesAdapter(notices, this::showNoticeDetailsDialog);
        rv.setAdapter(adapter);
    }

    private void showNoticeDetailsDialog(Notice notice) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(notice.title)
                .setMessage("Category: " + notice.category + "\nDate: " + notice.date + "\n\n" + notice.content)
                .setPositiveButton("GIVE FEEDBACK NOW", (dialog, which) -> 
                        startActivity(new Intent(NoticesActivity.this, GiveFeedbackActivity.class)))
                .setNegativeButton("CLOSE", (dialog, which) -> dialog.dismiss())
                .show();
    }

    public static class Notice {
        public String title, date, category, content;
        public Notice(String title, String date, String category, String content) {
            this.title = title;
            this.date = date;
            this.category = category;
            this.content = content;
        }
    }

    public interface OnNoticeClickListener {
        void onNoticeClick(Notice notice);
    }

    public static class NoticesAdapter extends RecyclerView.Adapter<NoticesAdapter.VH> {
        private final List<Notice> list;
        private final OnNoticeClickListener listener;

        public NoticesAdapter(List<Notice> list, OnNoticeClickListener listener) {
            this.list = list;
            this.listener = listener;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            Notice n = list.get(position);
            holder.tvTitle.setText(n.title);
            holder.tvDate.setText(n.date);
            holder.tvCategory.setText(n.category);
            holder.tvContent.setText(n.content);

            holder.itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onNoticeClick(n);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class VH extends RecyclerView.ViewHolder {
            TextView tvTitle, tvDate, tvCategory, tvContent;

            public VH(View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(R.id.tvNoticeTitle);
                tvDate = itemView.findViewById(R.id.tvNoticeDate);
                tvCategory = itemView.findViewById(R.id.tvNoticeCategory);
                tvContent = itemView.findViewById(R.id.tvNoticeContent);
            }
        }
    }
}
