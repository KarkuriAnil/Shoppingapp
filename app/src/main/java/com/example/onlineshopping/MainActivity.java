package com.example.onlineshopping;

import static com.example.onlineshopping.AllcategoriesDialog.ALL_CATEGORIES;
import static com.example.onlineshopping.AllcategoriesDialog.CALLING;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
private DrawerLayout drawerLayout;
private NavigationView navigationView;
private MaterialToolbar toolbar;
private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intilaview();


     // setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,(R.string.drawer_open),(R.string.drawer_close));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.cart:
                        Intent intent=new Intent(MainActivity.this,cartActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.categories:
                        AllcategoriesDialog allcategoriesDialog=new AllcategoriesDialog();
                        Bundle bundle=new Bundle();
                        bundle.putStringArrayList(ALL_CATEGORIES,Utils.getCategories(MainActivity.this));
                        bundle.putString(CALLING,"main_Activity");
                        allcategoriesDialog.setArguments(bundle);
                        allcategoriesDialog.show(getSupportFragmentManager(),"all categories");
                        break;
                    case R.id.about:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("About us")
                                .setMessage("Designed and developed by anil")
                                .setPositiveButton("visit", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //todo
                                    }
                                }).create().show();
                        break;
                    case R.id.terms:
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Terms")
                                .setMessage("there is no terms")
                                .setPositiveButton("dismiss", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();
                    default:
                        break;
                }
                return false;
            }
        });
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new MainFragment());
        transaction.commit();
    }
    private void intilaview()
    {
        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navgation);
       toolbar=findViewById(R.id.toolbar);
        frameLayout=findViewById(R.id.container);
    }
}