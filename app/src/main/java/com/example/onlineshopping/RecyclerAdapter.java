package com.example.onlineshopping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
  private ArrayList<Review> reviews=new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.review,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.username.setText(reviews.get(position).getUsername());
holder.txtreview.setText(reviews.get(position).getText());
holder.txtdate.setText(reviews.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
  {
      private TextView username,txtreview,txtdate;
      private MaterialCardView card;
      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          username=itemView.findViewById(R.id.username);
          txtreview=itemView.findViewById(R.id.textreview);
          txtdate=itemView.findViewById(R.id.date);
         // card=itemView.findViewById(R.id.pare);
      }
  }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }
}
