package com.example.onlineshopping.databasefile;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_items")
public class CartItem {
        @PrimaryKey(autoGenerate = true)
        private int id;
        private int groceryitremId;

        public CartItem(int groceryitremId) {
                this.groceryitremId = groceryitremId;
        }

        public int getId() {
                return id;
        }

        public int getGroceryitremId() {
                return groceryitremId;
        }

        public void setId(int id) {
                this.id = id;
        }
}
