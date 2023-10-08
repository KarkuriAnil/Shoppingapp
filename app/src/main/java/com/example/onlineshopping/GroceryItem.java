package com.example.onlineshopping;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.ArrayList;
@Entity(tableName = "grocery_items")
public class GroceryItem implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double price;
    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private int availableAmount;
    private int rate;
    private int userPoint;
    private int popularityPoint;
    @TypeConverters(ReviewsConverter.class)
    private ArrayList<Review> reviews;

    public GroceryItem(double price, String name, String description, String imageUrl, String category, int availableAmount, int rate, int userPoint, int popularityPoint, ArrayList<Review> reviews) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.availableAmount = availableAmount;
        this.rate = rate;
        this.userPoint = userPoint;
        this.popularityPoint = popularityPoint;
        this.reviews = reviews;
    }

    @Ignore
    public GroceryItem( String name, String description, String imageUrl, String category, int availableAmount,int price) {
        this.name = name;
        this.price=price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.availableAmount = availableAmount;
        this.rate=0;
        this.userPoint=0;
        this.popularityPoint=0;
        reviews=new ArrayList<>();
    }
@Ignore
    protected GroceryItem(Parcel in) {
        id = in.readInt();
        price = in.readDouble();
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        category = in.readString();
        availableAmount = in.readInt();
        rate = in.readInt();
        userPoint = in.readInt();
        popularityPoint = in.readInt();
    }
@Ignore
    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>() {
        @Override
        public GroceryItem createFromParcel(Parcel in) {
            return new GroceryItem(in);
        }

        @Override
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public int getPopularityPoint() {
        return popularityPoint;
    }

    public void setPopularityPoint(int popularityPoint) {
        this.popularityPoint = popularityPoint;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
@Ignore
    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category='" + category + '\'' +
                ", availableAmount=" + availableAmount +
                ", rate=" + rate +
                ", userPoint=" + userPoint +
                ", popularityPoint=" + popularityPoint +
                ", reviews=" + reviews +
                '}';
    }
@Ignore
    @Override
    public int describeContents() {
        return 0;
    }
@Ignore
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(price);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(category);
        dest.writeInt(availableAmount);
        dest.writeInt(rate);
        dest.writeInt(userPoint);
        dest.writeInt(popularityPoint);
    }
}
