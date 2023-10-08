package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SecondCartFragment extends Fragment {
    public static final String ORDER_KEY="ORDER";
    private TextView txtwarning;
    private EditText adress,zip,number,mail;
    private Button back,next;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_caset_second_xml,container,false);
      intialview(view);
      Bundle bundle=getArguments();
      if(null!=bundle)
      {
           String jsonholder=bundle.getString(ORDER_KEY);
           if(null!=jsonholder)
           {
               Gson gson=new Gson();
               Type type=new TypeToken<order>() {}.getType();
               order order=gson.fromJson(jsonholder,type);
               if(null!=order)
               {
                   adress.setText(order.getAddress());
                   zip.setText(order.getZipcode());
                   number.setText(order.getNumber());
                   mail.setText(order.getEmail());
               }
      }}
      back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
              transaction.replace(R.id.fram,new FirstcartFragment());
              transaction.commit();
          }
      });
      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
           if(vaildatedata())
           {
              txtwarning.setVisibility(View.GONE);
               ArrayList<GroceryItem> cartitems=Utils.getcart(getActivity());
               if(null!=cartitems)
               {
                   order order=new order();
                   order.setItems(cartitems);
                   order.setAddress(adress.getText().toString());
                   order.setEmail(mail.getText().toString());
                   order.setNumber(number.getText().toString());
                   order.setZipcode(zip.getText().toString());
                   order.setTotalprice(caluculation(cartitems));
                   Gson gson =new Gson();
                   String jsonholder=gson.toJson(order);
                   Bundle bundle=new Bundle();
                   bundle.putString(ORDER_KEY,jsonholder);
                  Third_fragment third_fragment=new Third_fragment();
                  third_fragment.setArguments(bundle);
                  FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                  transaction.replace(R.id.fram,third_fragment);
                  transaction.commit();
               }
           }
           else
           {
               txtwarning.setVisibility(View.VISIBLE);
               txtwarning.setText("please fill the blanks");
           }
          }
      });
        return view;
    }
    private double caluculation(ArrayList<GroceryItem> Cartitems)
    {
        double price=0;
        for(GroceryItem i:Cartitems)
        {
            price+=i.getPrice();
        }
        price=Math.round(price*100.0)/100.0;
     return price;
    }
    private boolean vaildatedata()
    {
        if(adress.getText().toString().equals("")||zip.getText().toString().equals("")||number.getText().toString().equals("")||mail.getText().toString().equals(""))
        {
            return false;
        }
        return true;
    }
    private void intialview(View view)
    {
        txtwarning=view.findViewById(R.id.warningg);
        adress=view.findViewById(R.id.eaddress);
        zip=view.findViewById(R.id.ezip);
        number=view.findViewById(R.id.enumber);
        mail=view.findViewById(R.id.eemail);
        back=view.findViewById(R.id.back);
        next=view.findViewById(R.id.nextt);
    }
}
