package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("CartDetail")
public class CartDetailModel {

    @Id
    private String _id;
    @Field("cartDID")
    String cartDID;
    @Field("cartID")
    String cartID;
    @Field("proID")
    String proID;
    @Field("quantity")
    int quantity;
    @Field("cost")
    long cost;

    public CartDetailModel(String _id, String cartDID, String cartID, String proID, int quantity, long cost) {
        this._id = _id;
        this.cartDID = cartDID;
        this.cartID = cartID;
        this.proID = proID;
        this.quantity = quantity;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CartDetailModel{" +
                ", cartDID='" + cartDID + '\'' +
                ", cartID='" + cartID + '\'' +
                ", proID='" + proID + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCartDID() {
        return cartDID;
    }

    public void setCartDID(String cartDID) {
        this.cartDID = cartDID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }
}
