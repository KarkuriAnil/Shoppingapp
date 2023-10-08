package com.example.onlineshopping;

import static com.example.onlineshopping.groceryActivity2.GROCERY_STORE_KEY;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder>{
    ArrayList<GroceryItem> Items =new ArrayList<>();
    private Context context;

    public GroceryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
holder.name.setText(Items.get(position).getName());
holder.price.setText(String.valueOf(Items.get(position).getPrice()) + "$");
        Glide.with(context)
               .asBitmap()
                .load(Items.get(position).getImageUrl())
                .into(holder.imageView);
     holder.card.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context,groceryActivity2.class);
               intent.putExtra(GROCERY_STORE_KEY,Items.get(position));
               context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
        private TextView name,price;
        private MaterialCardView card;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            card=itemView.findViewById(R.id.parent);

        }
    }

    public void setItems(ArrayList<GroceryItem> items) {
        this.Items = items;
    }
}
