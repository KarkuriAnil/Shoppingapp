package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class groceryActivity2 extends AppCompatActivity implements AddReviewDialog.AddReview {
    private static final String TAG = "groceryActivity2";
    private boolean isBound;
    private TrackUserTime mservice;
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TrackUserTime.localBlinder blinder=(TrackUserTime.localBlinder) service;
            mservice=blinder.getservice();
            isBound=true;
            mservice.setItem(incoming);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
isBound=false;
        }
    };
    private RecyclerView recyclerView;
    private TextView txtname, txtPrice, txtDescription, txtAddreview;
    private ImageView itemImage, firstempty, secondempty, thirdempty, ffilled, sfelled, tfilled;
    private Button btnAddCart;
    private RelativeLayout fr, sr, tr;
    private GroceryItem incoming;
    public static final String GROCERY_STORE_KEY = "KEY";
    private RecyclerAdapter recyclerAdapter;
    private MaterialToolbar toolbar;


    @Override
    public void onAddReviewResult(Review review) {
        Log.d(TAG, "onAddReviewResult: new reviewss" + review);
              Utils.addReview(this, review);
         ArrayList<Review> reviewl = Utils.getreview(this, review.getGroceryId());
          if (null != reviewl) {
            recyclerAdapter.setReviews(reviewl);
          }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery2);
        intialview();
      //  setSupportActionBar(toolbar);
        recyclerAdapter = new RecyclerAdapter();
//recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        //reviews=new ArrayList<>();
//        recyclerView.setAdapter(recyclerAdapter);
//       // reviews.add(review);
//      //  recyclerAdapter.setReviews(reviews);
        Intent intent = getIntent();
        if (null != intent) {
            incoming = intent.getParcelableExtra(GROCERY_STORE_KEY);
            if (incoming != null) {
                Utils.changeuserpoint(this,incoming,1);
                txtname.setText(incoming.getName());
                txtPrice.setText(String.valueOf(incoming.getPrice()));
                txtDescription.setText(incoming.getDescription());
                Glide.with(this)
                        .asBitmap()
                        .load(incoming.getImageUrl())
                        .into(itemImage);
                ArrayList<Review> reviews=Utils.getreview(this,incoming.getId());
                recyclerView.setAdapter(recyclerAdapter);
               recyclerView.setLayoutManager(new LinearLayoutManager(this));
//               recyclerAdapter.setReviews(reviews);
               if (null != reviews) {
                   if (reviews.size() > 0) {
                        recyclerAdapter.setReviews(reviews);
                    }
                }

            btnAddCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.addItemtoCrat(groceryActivity2.this,incoming);
                   Intent intent1=new Intent(groceryActivity2.this,cartActivity.class);
                   intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent1);
                }
            });
            txtAddreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddReviewDialog dialog = new AddReviewDialog();
                   Bundle bundle = new Bundle();
                   bundle.putParcelable(GROCERY_STORE_KEY, incoming);
                    dialog.setArguments(bundle);
                    dialog.show(getSupportFragmentManager(), "add review");
                }
            });}

        handleRating();
    }

}


    private void handleRating() {
        switch (incoming.getRate()) {
            case 0:
                firstempty.setVisibility(View.VISIBLE);
                ffilled.setVisibility(View.GONE);
                secondempty.setVisibility(View.VISIBLE);
                sfelled.setVisibility(View.GONE);
                thirdempty.setVisibility(View.VISIBLE);
                tfilled.setVisibility(View.GONE);
                break;
            case 1:
                firstempty.setVisibility(View.GONE);
                ffilled.setVisibility(View.VISIBLE);
                secondempty.setVisibility(View.VISIBLE);
                sfelled.setVisibility(View.GONE);
                thirdempty.setVisibility(View.VISIBLE);
                tfilled.setVisibility(View.GONE);
                break;
            case 2:
                firstempty.setVisibility(View.GONE);
                ffilled.setVisibility(View.VISIBLE);
                secondempty.setVisibility(View.GONE);
                sfelled.setVisibility(View.VISIBLE);
                thirdempty.setVisibility(View.VISIBLE);
                tfilled.setVisibility(View.GONE);
                break;
            case 3:
                firstempty.setVisibility(View.GONE);
                ffilled.setVisibility(View.VISIBLE);
                secondempty.setVisibility(View.GONE);
                sfelled.setVisibility(View.VISIBLE);
                thirdempty.setVisibility(View.GONE);
                tfilled.setVisibility(View.VISIBLE);
                break;
            default:
        }
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incoming.getRate() != 1) {
                    Utils.changeRate(groceryActivity2.this, incoming.getId(), 1);
                    Utils.changeuserpoint(groceryActivity2.this,incoming,(1-incoming.getRate())*2);
                    incoming.setRate(1);
                    handleRating();
                }
            }
        });
        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incoming.getRate() != 2) {
                    Utils.changeRate(groceryActivity2.this, incoming.getId(), 2);
                    Utils.changeuserpoint(groceryActivity2.this,incoming,(2-incoming.getRate())*2);
                    incoming.setRate(2);
                    handleRating();
                }
            }
        });
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incoming.getRate() != 3) {
                    Utils.changeRate(groceryActivity2.this, incoming.getId(), 3);
                    Utils.changeuserpoint(groceryActivity2.this,incoming,(3-incoming.getRate())*2);
                    incoming.setRate(3);
                    handleRating();
                }
            }
        });
     }

    private void intialview() {
        recyclerView = findViewById(R.id.reviewrecycle);
        txtname = findViewById(R.id.name1);
        txtPrice = findViewById(R.id.price1);
        txtDescription = findViewById(R.id.desc1);
        txtAddreview = findViewById(R.id.addreview);
        itemImage = findViewById(R.id.image1);
        firstempty = findViewById(R.id.emptystar);
        secondempty = findViewById(R.id.semptystar);
        thirdempty = findViewById(R.id.temptystar);
        ffilled = findViewById(R.id.filledstar);
        sfelled = findViewById(R.id.sfilledstar);
        tfilled = findViewById(R.id.tfilledstar);
        btnAddCart = findViewById(R.id.btn1);
        fr = findViewById(R.id.starr);
        sr = findViewById(R.id.sstarr);
        tr = findViewById(R.id.tstarr);
        toolbar = findViewById(R.id.toolbar1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(this,TrackUserTime.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound)
        {
            unbindService(connection);
        }
    }
}