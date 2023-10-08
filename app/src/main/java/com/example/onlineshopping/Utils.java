package com.example.onlineshopping;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.onlineshopping.databasefile.ShopDataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String TAG = "Utils";
    private static int orderId=0;
    public static ArrayList<GroceryItem> getALLitems(Context context)
    {
        return (ArrayList<GroceryItem>) ShopDataBase.getInstance(context).groceryDao().getitems();
    }

    public static void changeRate(Context context,int ItemId,int newRate)
    {
        ShopDataBase.getInstance(context).groceryDao().update(newRate,ItemId);
    }
    public static void addReview(Context context,Review review)
    {
        GroceryItem item=ShopDataBase.getInstance(context).groceryDao().item(review.getGroceryId());
        ArrayList<Review> reviews=item.getReviews();
        if(null==reviews)
        {
            reviews=new ArrayList<>();
        }
        reviews.add(review);
        Gson gson=new Gson();
        String texxt=gson.toJson(reviews);
        ShopDataBase.getInstance(context).groceryDao().updatereviews(review.getGroceryId(), texxt);

    }
    public static ArrayList<Review> getreview(Context context,int itemid) {
        return ShopDataBase.getInstance(context).groceryDao().item(itemid).getReviews();
    }
    public static void addItemtoCrat(Context context,GroceryItem item)
    {
       ShopDataBase.getInstance(context).cartDao().insert(item.getId());
}
public static ArrayList<GroceryItem> getcart(Context context)
{
  return(ArrayList<GroceryItem>) ShopDataBase.getInstance(context).cartDao().getallcartitems();
}
public static ArrayList<GroceryItem> searchForItems(Context context,String text) {
        String finalstring="%"+text+"%";
  return (ArrayList<GroceryItem>) ShopDataBase.getInstance(context).groceryDao().searchselecteditem(finalstring);
    }
public static ArrayList<String> getCategories(Context context)
{return (ArrayList<String>) ShopDataBase.getInstance(context).groceryDao().category();
}
public static ArrayList<GroceryItem> getItemBycaterogy(Context context,String category)
{return (ArrayList<GroceryItem>)ShopDataBase.getInstance(context).groceryDao().getcategpry(category);}
public static void deleteItemFromCart(Context context,GroceryItem item)
{
   ShopDataBase.getInstance(context).cartDao().deleteitem(item.getId());
    }

    public static int getOrderId() {
        orderId++;
        return orderId;
    }
    public static void clearCartItems(Context context)
    {
      ShopDataBase.getInstance(context).cartDao().clearcart();
    }
    public static void increasepopularitypoint(Context context, GroceryItem item,int position)
    {
     int newpoints=item.getPopularityPoint()+position;
     ShopDataBase.getInstance(context).groceryDao().popularpoints(newpoints,item.getId());
    }
    public static void changeuserpoint(Context context,GroceryItem item,int points)
    {
        int newpoints= item.getUserPoint()+points;
        ShopDataBase.getInstance(context).groceryDao().userpont(points,item.getId());

        }
    }




