package com.example.onlineshopping.databasefile;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.onlineshopping.GroceryItem;

import java.util.ArrayList;

@Database(entities ={ GroceryItem.class,CartItem.class},version = 1)
public abstract class ShopDataBase extends RoomDatabase {
    public abstract cartDao cartDao();
    public  abstract GroceryDao groceryDao();
    private static ShopDataBase instance;

    public static  synchronized ShopDataBase getInstance(Context context) {
        if(null==instance)
        {
            instance= Room.databaseBuilder(context,ShopDataBase.class,"shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback callback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new intialasync(instance).execute();
        }
    };
    private static class intialasync extends AsyncTask<Void,Void,Void>
    {
        private GroceryDao groceryDao;
        private cartDao cartDao;
        public intialasync(ShopDataBase db)
        {
            this.cartDao= db.cartDao();
            this.groceryDao=db.groceryDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<GroceryItem> allItems=new ArrayList<>();
            allItems.add(new GroceryItem("fruits","nice","https://m.economictimes.com/thumb/msid-89885004,width-1200,height-900,resizemode-4,imgsize-137412/healthy-food_think.jpg","food",2,20));
            allItems.add(new GroceryItem("MILK","DCD","https://img.freepik.com/free-vector/bottle-glass-milk_1284-14094.jpg?w=2000","drink",10,12));

            allItems.add(new GroceryItem("Chocos","dd","https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/51-YbsziNnL.jpg","food",2,5));
            allItems.add(new GroceryItem("Icecream","emdekm","https://media.istockphoto.com/id/1400292359/photo/ice-cream-cones-bouquet.jpg?b=1&s=170667a&w=0&k=20&c=WWRPlrH9XrlZ74wkUhiK5S6nzm9O0vjRDpSJ-CHAC70=","wine",2,122));
for(GroceryItem s: allItems)
{
    groceryDao.insert(s);
}return null;
        }
    }
}
