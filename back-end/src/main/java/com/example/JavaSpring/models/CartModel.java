package com.example.JavaSpring.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Cart")
public class CartModel {
    @Id
    private String _id;
    @Field("cartID")
    String cartID;
    @Field("accID")
    String accID;
    @Field("totalQuantity")
    int totalQuantity;
    @Field("totalCost")
    Long totalCost;

    public CartModel(String _id, String cartID, String accID, int totalQuantity, Long totalCost){
        this._id = _id;
        this.cartID = cartID;
        this.accID = accID;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "_id='" + _id + '\'' +
                ", cartID='" + cartID + '\'' +
                ", accID='" + accID + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalCost=" + totalCost +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }
}
