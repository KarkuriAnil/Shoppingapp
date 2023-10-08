package com.example.onlineshopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class cartActivity extends AppCompatActivity {
private MaterialToolbar toolbar;
private FrameLayout layout;
private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        intialview();
        navigationButton();
      //  setSupportActionBar(toolbar);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fram,new FirstcartFragment());
        transaction.commit();

    }
    private void navigationButton()
    {
        navigationView.setSelectedItemId(R.id.cart);
        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent intent=new Intent(cartActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        Intent intent1=new Intent(cartActivity.this,SearchActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        break;
                    case R.id.cart:
                        break;
                    default:
                }
            }
        });
    }
    private void intialview()
    {
        toolbar=findViewById(R.id.toolbar2);
        layout=findViewById(R.id.fram);
        navigationView=findViewById(R.id.bottomnavigation123);
    }
}