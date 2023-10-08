package com.example.onlineshopping.databasefile;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.onlineshopping.GroceryItem;

import java.util.List;

@Dao
public interface cartDao {
  @Query("INSERT INTO cart_items (groceryitremId) VALUES (:id)")
    void insert(int id);
  @Query("SELECT grocery_items.id,grocery_items.name,grocery_items.description,grocery_items.imageUrl,grocery_items.category,grocery_items.price,grocery_items.availableamount,grocery_items.rate,grocery_items.userPoint,grocery_items.popularityPoint,grocery_items.reviews FROM grocery_items INNER JOIN cart_items ON cart_items.groceryitremId=grocery_items.id")
  List<GroceryItem> getallcartitems();
  @Query("DELETE FROM cart_items WHERE groceryitremId=:id")
  void deleteitem(int id);
  @Query("DELETE FROM cart_items")
  void clearcart();
}
