package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView newItems,popularItems,suggestedItems;
    private GroceryAdapter newItemAdapter,popularAdapter,suggestedAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
       //   Utils.clearShredPreferences(getActivity());
        intialview(view);
        initnavigationview();
       return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter();
    }

    private void intialview(View view)
    {
        bottomNavigationView=view.findViewById(R.id.bottomnavigation);
        newItems=view.findViewById(R.id.r1);
        popularItems=view.findViewById(R.id.r2);
        suggestedItems=view.findViewById(R.id.r3);



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

                       break;
                   case R.id.search:
                     Intent intent=new Intent(getActivity(),SearchActivity.class);
                     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                     startActivity(intent);
                       break;
                   case R.id.cart:
                       Intent intent1=new Intent(getActivity(),cartActivity.class);
                       intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                       startActivity(intent1);
                       break;
                   default:
               }
           }
       });
   }
   private void adapter()
   {
       newItemAdapter=new GroceryAdapter(getActivity());
       newItems.setAdapter(newItemAdapter);
       newItems.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

       popularAdapter=new GroceryAdapter(getActivity());
       popularItems.setAdapter(popularAdapter);
       popularItems.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

       suggestedAdapter=new GroceryAdapter(getActivity());
       suggestedItems.setAdapter(suggestedAdapter);
       suggestedItems.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
       ArrayList<GroceryItem> items=Utils.getALLitems(getActivity());
       if(null!=items) {
           Comparator<GroceryItem> comparator=new Comparator<GroceryItem>() {
               @Override
               public int compare(GroceryItem o1, GroceryItem o2) {
                   return o1.getId()-o2.getId();
               }
           };
           Comparator<GroceryItem> reverseorder=Collections.reverseOrder(comparator);
           Collections.sort(items,reverseorder);
           newItemAdapter.setItems(items);
       }
ArrayList<GroceryItem> popularItem=Utils.getALLitems(getActivity());
       if(null!=popularItem)
       {
           Comparator<GroceryItem> popularcomparator=new Comparator<GroceryItem>() {
               @Override
               public int compare(GroceryItem o1, GroceryItem o2) {
                   return o1.getPopularityPoint()-o2.getPopularityPoint();
               }
           };
           Collections.sort(popularItem,Collections.reverseOrder(popularcomparator));
           popularAdapter.setItems(popularItem);
       }
       ArrayList<GroceryItem> suggestedItem=Utils.getALLitems(getActivity());
       if(null!=suggestedItem)
       {
           Comparator<GroceryItem> suggestedIIComparator=new Comparator<GroceryItem>() {
               @Override
               public int compare(GroceryItem o1, GroceryItem o2) {
                   return o1.getUserPoint()-o2.getUserPoint();
               }
           };
           Collections.sort(suggestedItem,Collections.reverseOrder(suggestedIIComparator));
           suggestedAdapter.setItems(suggestedItem);
       }
   }
}
