package com.example.onlineshopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirstcartFragment extends Fragment implements CartAdapter.Deleteitem,CartAdapter.Totalprice {
    private RecyclerView recyclerView;
    private TextView txtsum,txtnoitem;
    private Button next;
    private RelativeLayout itemsrelative;
    private CartAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.first_cart_fragment,container,false);
       intialview(view);
       adapter=new CartAdapter(this,getActivity());
       recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<GroceryItem> cartitems=Utils.getcart(getActivity());
        if(null!=cartitems)
        {
            if(cartitems.size()>0){
                txtnoitem.setVisibility(View.GONE);
                itemsrelative.setVisibility(View.VISIBLE);
                adapter.setCartitems(cartitems);
            }
            else {
                txtnoitem.setVisibility(View.VISIBLE);
                itemsrelative.setVisibility(View.GONE);
            }}

        else {
            txtnoitem.setVisibility(View.VISIBLE);
            itemsrelative.setVisibility(View.GONE);

        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fram, new SecondCartFragment());
                transaction.commit();
            }
        });
        return view;
    }
    private void intialview(View view)
    {
        recyclerView=view.findViewById(R.id.recycle1);
        txtnoitem=view.findViewById(R.id.emptycart);
        txtsum=view.findViewById(R.id.sum);
        next=view.findViewById(R.id.nextbutton);
        itemsrelative=view.findViewById(R.id.lay1);

    }

    @Override
    public void onDeleteResult(GroceryItem item) {
        Utils.deleteItemFromCart(getActivity(),item);
        ArrayList<GroceryItem> cartitems=Utils.getcart(getActivity());
        if(null!=cartitems)
        {
            if(cartitems.size()>0){
                txtnoitem.setVisibility(View.GONE);
                itemsrelative.setVisibility(View.VISIBLE);
            adapter.setCartitems(cartitems);
        }
            else {
                txtnoitem.setVisibility(View.VISIBLE);
                itemsrelative.setVisibility(View.GONE);
            }}
        else {
            txtnoitem.setVisibility(View.VISIBLE);
            itemsrelative.setVisibility(View.GONE);

        }
}

    @Override
    public void getprice(double price) {
        txtsum.setText(String.valueOf(price)+"$");
    }
}
