package com.example.onlineshopping;

import static com.example.onlineshopping.SecondCartFragment.ORDER_KEY;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.zip.Inflater;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Third_fragment extends Fragment {
    private static final String TAG = "Third_fragment";
    private TextView adress,itemname,phonenumber,totalprice;
    private Button back,checkout;
    private RadioGroup payment;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_cart_thirdlayout,container,false);
       intialview(view);
       Bundle bundle=getArguments();
       if(null!=bundle)
       {
           String jsonholder= bundle.getString(ORDER_KEY);
           if(null!=jsonholder)
           {
               Gson gson=new Gson();
              Type type=new TypeToken<order>() {}.getType();
             order order=gson.fromJson(jsonholder,type);
             if(null!=order)
             {
                 String items="";
                 for(GroceryItem i: order.getItems())
                 {
                     items+= "\n\t" +i.getName();

                 }
                 itemname.setText(items);
                 adress.setText(order.getAddress());
                 phonenumber.setText(order.getNumber());
                 totalprice.setText(String.valueOf(order.getTotalprice()));
                 back.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Bundle bundle1=new Bundle();
                         bundle1.putString(ORDER_KEY,jsonholder);
                         SecondCartFragment secondCartFragment=new SecondCartFragment();
                         secondCartFragment.setArguments(bundle1);
                         FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                         transaction.replace(R.id.fram,secondCartFragment);
                         transaction.commit();
                     }
                 });
                 checkout.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         switch (payment.getCheckedRadioButtonId())
                         {
                             case R.id.credit:
                                 order.setPayment("credit");
                                 break;
                             case R.id.paypal:
                                 order.setPayment("paypal");
                                 break;
                             default:
                                 order.setPayment("unknown");
                                 break;
                         }
                         order.setSuccess(true);
                         HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor()
                                 .setLevel(HttpLoggingInterceptor.Level.BODY);
                         OkHttpClient client=new OkHttpClient.Builder()
                                 .addInterceptor(interceptor)
                                 .build();
                         Retrofit retrofit=new Retrofit.Builder()
                                 .baseUrl("https://jsonplaceholder.typicode.com/")
                                 .addConverterFactory(GsonConverterFactory.create())
                                 .client(client)
                                 .build();
                         orderEndpoint endpoint=retrofit.create(orderEndpoint.class);
                         Call<order> call= endpoint.neworder(order);
                         call.enqueue(new Callback<com.example.onlineshopping.order>() {
                             @Override
                             public void onResponse(Call<com.example.onlineshopping.order> call, Response<com.example.onlineshopping.order> response) {
                                 Log.d(TAG, "onResponse: code" + response.code());
                                 if(response.isSuccessful())
                                 {
                                     Bundle bundle1=new Bundle();
                                     bundle1.putString(ORDER_KEY,gson.toJson(response.body()));
                                     paymentResult paymentResult=new paymentResult();
                                     paymentResult.setArguments(bundle1);
                                     FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                                     transaction.replace(R.id.fram,paymentResult);
                                     transaction.commit();
                                 }
                                 else
                                 {
                                     FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                                     transaction.replace(R.id.fram, new paymentResult());
                                     transaction.commit();
                                 }
                             }

                             @Override
                             public void onFailure(Call<com.example.onlineshopping.order> call, Throwable t) {
                                 t.printStackTrace();

                             }
                         });


                     }
                 });
             }
           }
       }
        return view;
    }
    private void intialview(View view)
    {
        adress=view.findViewById(R.id.cartadd);
        itemname=view.findViewById(R.id.itemsname);
        totalprice=view.findViewById(R.id.totalprice1);
        phonenumber=view.findViewById(R.id.cartphone);
        back=view.findViewById(R.id.paymentback);
        checkout=view.findViewById(R.id.checked);
        payment=view.findViewById(R.id.radiogroup);
    }
}
