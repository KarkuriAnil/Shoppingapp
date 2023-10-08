package com.example.onlineshopping.databasefile;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.onlineshopping.GroceryItem;
import java.util.List;
@Dao
public interface GroceryDao {
    @Insert
    void insert(GroceryItem groceryItem);
    @Query("SELECT * FROM grocery_items")
    List<GroceryItem> getitems();
    @Query("UPDATE grocery_items SET rate=:newrate WHERE id=:id")
    void update(int newrate,int id);
    @Query("SELECT * FROM grocery_items WHERE id=:id")
    GroceryItem item(int id);
    @Query("UPDATE grocery_items SET reviews=:reviews WHERE id=:id")
    void updatereviews(int id,String reviews);
    @Query("SELECT * FROM grocery_items WHERE name LIKE :name")
    List<GroceryItem> searchselecteditem(String name);
    @Query("SELECT  DISTINCT category FROM grocery_items")
    List<String> category();
@Query("SELECT * FROM grocery_items WHERE category=:category")
    List<GroceryItem> getcategpry(String category);
@Query("UPDATE grocery_items SET popularityPoint=:points WHERE id=:id")
    void popularpoints(int points,int id);
@Query("UPDATE grocery_items SET userPoint=:userpoint WHERE id=:id")
    void userpont(int userpoint,int id);
}
