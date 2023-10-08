package com.example.onlineshopping;

import static com.example.onlineshopping.SecondCartFragment.ORDER_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class paymentResult extends Fragment {
    private Button Home;
    private TextView txtmessage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.payment_layout,container,false);
      intialview(view);
      Bundle bundle=getArguments();
      if(null!=bundle)
      {
          String jsonholder= bundle.getString(ORDER_KEY);
          Gson gson=new Gson();
          Type type=new TypeToken<order>() {}.getType();
          order order=gson.fromJson(jsonholder,type);
          if(null!=order)
          {
              if(order.isSuccess())
              {
                  txtmessage.setText("Payment was successfull\nYour order will arrive in 3 days");
                  Utils.clearCartItems(getActivity());
                  for(GroceryItem item: order.getItems())
                  {
                      Utils.increasepopularitypoint(getActivity(),item,1);
                      Utils.changeuserpoint(getActivity(),item,4);
                  }
              }
              else {
                  txtmessage.setText("payment failed \nplease try another method");
              }
          }
          else
          {
              txtmessage.setText("payment failed \nplease try another method");
          }
      }
      Home.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(getActivity(),MainActivity.class);
              startActivity(intent);
          }
      });
        return view;
    }
        private void intialview(View view)
        {
            Home=view.findViewById(R.id.shopping);
            txtmessage=view.findViewById(R.id.accepted);
        }

}
