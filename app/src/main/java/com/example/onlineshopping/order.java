package com.example.onlineshopping;

import java.util.ArrayList;

public class order {
    private int id;
    private ArrayList<GroceryItem> items;
    private String address;
    private String zipcode;
    private String number;
    private String email;
    private double totalprice;
    private String payment;
    private boolean success;

    public order(ArrayList<GroceryItem> items, String address, String zipcode, String number, String email, double totalprice, String payment, boolean success) {
        this.id=Utils.getOrderId();
        this.items = items;
        this.address = address;
        this.zipcode = zipcode;
        this.number = number;
        this.email = email;
        this.totalprice = totalprice;
        this.payment = payment;
        this.success = success;
    }

    public order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<GroceryItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<GroceryItem> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", items=" + items +
                ", address='" + address + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", totalprice=" + totalprice +
                ", payment='" + payment + '\'' +
                ", success=" + success +
                '}';
    }
}
