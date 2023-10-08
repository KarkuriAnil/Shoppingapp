package com.example.onlineshopping;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    public interface Totalprice
    {
        void getprice(double price);
    }
    public interface Deleteitem
    {
        void onDeleteResult(GroceryItem item);
    }
    private Deleteitem deleteitem;
    private Totalprice totalprice;
    private Fragment fragment;
    ArrayList<GroceryItem> Cartitems =new ArrayList<>();
    private Context context;

    public CartAdapter(Fragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartadapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
holder.name.setText(Cartitems.get(position).getName());
holder.price.setText(String.valueOf(Cartitems.get(position).getPrice())+"$");
holder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context)
                .setTitle("Deleting....")
                .setMessage("Are you sure You want to delete")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     deleteitem=(Deleteitem) fragment;
                     deleteitem.onDeleteResult(Cartitems.get(position));
                    }
                });
        builder.create().show();
    }
});
    }

    @Override
    public int getItemCount() {
        return Cartitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private TextView name,price,delete;
       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           name=itemView.findViewById(R.id.itemname);
           price=itemView.findViewById(R.id.priceitem);
           delete=itemView.findViewById(R.id.delete);
       }
   }

    public void setCartitems(ArrayList<GroceryItem> cartitems) {
        this.Cartitems = cartitems;
        caluculation();
        notifyDataSetChanged();
    }
    private void caluculation()
    {
        double price=0;
        for(GroceryItem i:Cartitems)
        {
            price+=i.getPrice();
        }
        price=Math.round(price*100.0)/100.0;
     try
     {
       totalprice=(Totalprice) fragment;
       totalprice.getprice(price);
     }
     catch (ClassCastException e)
     {

     }
    }

}
