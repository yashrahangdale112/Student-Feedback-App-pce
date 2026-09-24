package com.example.studentfeedbackapp.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentfeedbackapp.R;
import com.example.studentfeedbackapp.models.Feedback;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    private List<Feedback> feedbackList;

    public FeedbackAdapter(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        holder.tvSubject.setText(feedback.getSubject());
        holder.tvType.setText(feedback.getType());
        holder.tvStatus.setText(feedback.getStatus());
        holder.tvDate.setText(feedback.getDate());
        holder.ratingBar.setRating(feedback.getRating());

        // Dynamic icon based on type
        switch (feedback.getType()) {
            case "Faculty Feedback":
                holder.ivIcon.setImageResource(android.R.drawable.ic_menu_myplaces);
                break;
            case "Lab Facilities":
                holder.ivIcon.setImageResource(android.R.drawable.ic_menu_agenda);
                break;
            case "Canteen":
                holder.ivIcon.setImageResource(android.R.drawable.ic_menu_view);
                break;
            default:
                holder.ivIcon.setImageResource(android.R.drawable.ic_menu_info_details);
                break;
        }

        // Dynamic status colors
        switch (feedback.getStatus()) {
            case "Submitted":
                holder.tvStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.status_submitted));
                break;
            case "Resolved":
                holder.tvStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.status_resolved));
                break;
            case "Under Review":
                holder.tvStatus.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.status_pending));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubject, tvType, tvStatus, tvDate;
        RatingBar ratingBar;
        ImageView ivIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubject = itemView.findViewById(R.id.tvItemSubject);
            tvType = itemView.findViewById(R.id.tvItemType);
            tvStatus = itemView.findViewById(R.id.tvItemStatus);
            tvDate = itemView.findViewById(R.id.tvItemDate);
            ratingBar = itemView.findViewById(R.id.itemRatingBar);
            ivIcon = itemView.findViewById(R.id.ivItemIcon);
        }
    }
}