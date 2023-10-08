package com.example.onlineshopping;

import static com.example.onlineshopping.AllcategoriesDialog.ALL_CATEGORIES;
import static com.example.onlineshopping.AllcategoriesDialog.CALLING;
import static com.example.onlineshopping.AllcategoriesDialog.CATEGORY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements AllcategoriesDialog.GetCategory {
    private static final String TAG = "SearchActivity";
    private EditText search;
    private TextView firstcart,secondcart,thirdcart,allcaterogiew;
    private ImageView searchbutton;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private GroceryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        intialview();
        allcaterogiew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllcategoriesDialog allcategoriesDialog=new AllcategoriesDialog();
                Bundle bundle=new Bundle();
                bundle.putStringArrayList(ALL_CATEGORIES,Utils.getCategories(SearchActivity.this));
                bundle.putString(CALLING,"search_Activity");
                allcategoriesDialog.setArguments(bundle);
                allcategoriesDialog.show(getSupportFragmentManager(),"all categories");
            }
        });

        initnavigationview();
       // setSupportActionBar(tool);
        adapter=new GroceryAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        Intent intent=getIntent();
        if(null!=intent)
        {
            String categorys=intent.getStringExtra(CATEGORY);
            if(null!=categorys)
            {
                ArrayList<GroceryItem> anil=Utils.getItemBycaterogy(this,categorys);
                if(null!=anil){

                    adapter.setItems(anil);
                    increaseuserpoint(anil);
                }
            }}
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initsearch();
              //  ArrayList<GroceryItem> items=Utils.searchForItems(this,name)
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               initsearch();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ArrayList<String> categories=Utils.getCategories(this);
        if(null!=categories)
        {
            if(categories.size()>0)
            {
                if(categories.size()==1)
                {
                    showcaterogies(categories,1);
                }
                else if (categories.size()==2)
                {
                    showcaterogies(categories,2);
                }
                else
                {
                   showcaterogies(categories,3);
                }
            }
        }}

    private void showcaterogies(final ArrayList<String> categories,int i)
    {
        switch (i)
        {
            case 1:
                firstcart.setVisibility(View.VISIBLE);
                firstcart.setText(categories.get(0));
                secondcart.setVisibility(View.GONE);
                thirdcart.setVisibility(View.GONE);
                firstcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<GroceryItem> items=Utils.getItemBycaterogy(SearchActivity.this,categories.get(0));
                        if(null!=items)
                        {
                            adapter.setItems(items);
                            increaseuserpoint(items);


                        }
                    }
                });
                break;
            case 2:
                secondcart.setVisibility(View.VISIBLE);
                firstcart.setVisibility(View.VISIBLE);
                thirdcart.setVisibility(View.GONE);
                firstcart.setText(categories.get(0));
                secondcart.setText(categories.get(1));
                firstcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<GroceryItem> items=Utils.getItemBycaterogy(SearchActivity.this,categories.get(0));
                        if(null!=items)
                        {
                            adapter.setItems(items);
                            increaseuserpoint(items);


                        }
                    }
                });
                secondcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<GroceryItem> itemss=Utils.getItemBycaterogy(SearchActivity.this,categories.get(1));
                        if(null!=itemss)
                        {
                            adapter.setItems(itemss);
                            increaseuserpoint(itemss);

                        }
                    }
                });
                break;
            case 3:
                firstcart.setVisibility(View.VISIBLE);
                firstcart.setText(categories.get(0));
                secondcart.setVisibility(View.VISIBLE);
                thirdcart.setVisibility(View.VISIBLE);
                secondcart.setText(categories.get(1));
                thirdcart.setText(categories.get(2));
                firstcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<GroceryItem> items=Utils.getItemBycaterogy(SearchActivity.this,categories.get(0));
                        if(null!=items)
                        {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }
                    }
                });

                secondcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<GroceryItem> itemss=Utils.getItemBycaterogy(SearchActivity.this,categories.get(1));
                        if(null!=itemss)
                        {
                            adapter.setItems(itemss);
                            increaseuserpoint(itemss);

                        }
                    }
                });
                thirdcart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<GroceryItem> items=Utils.getItemBycaterogy(SearchActivity.this,categories.get(2));
                        if(null!=items)
                        {
                            adapter.setItems(items);
                            increaseuserpoint(items);

                        }
                    }
                });
                break;
            default:
                firstcart.setVisibility(View.GONE);
                secondcart.setVisibility(View.GONE);
                thirdcart.setVisibility(View.GONE);
                break;
        }
    }
    private void initsearch()
    {
        if(!search.getText().toString().equals(""))
       {
            String name=search.getText().toString();
            ArrayList<GroceryItem> items=Utils.searchForItems(this,name);
            if(null!=items)
            {
                adapter.setItems(items);
                increaseuserpoint(items);
            }
        }
    }
    private void initnavigationview()
    {
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                    case R.id.search:

                        break;
                    case R.id.cart:
                        Intent intent1=new Intent(SearchActivity.this,cartActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        break;
                    default:
                }
            }
        });
    }
    private void intialview()
    {
       // tool=findViewById(R.id.seachtol);
        search=findViewById(R.id.searchbox);
        firstcart=findViewById(R.id.first);
        secondcart=findViewById(R.id.second);
        thirdcart=findViewById(R.id.third);
        allcaterogiew=findViewById(R.id.allcategories);
        searchbutton=findViewById(R.id.btnsearch);
        recyclerView=findViewById(R.id.searchrecycle);
        bottomNavigationView=findViewById(R.id.bottomnavigation12);
    }

    @Override
    public void onGetCategoryResult(String category) {
        ArrayList<GroceryItem> items=Utils.getItemBycaterogy(this,category);
        if(null!=items)
        {
            adapter.setItems(items);
            increaseuserpoint(items);
        }
    }
    private void increaseuserpoint(ArrayList<GroceryItem> items)
    {
        for(GroceryItem i:items)
        {
            Utils.changeuserpoint(this,i,1);
        }
    }
}