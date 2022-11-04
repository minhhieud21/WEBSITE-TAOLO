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
    @Field("status")
    int status;
    @Field("address")
    String address;
    @Field("methodPay")
    String methodPay;

    public CartModel(String _id, String cartID, String accID, int totalQuantity, Long totalCost, int status, String address, String methodPay) {
        this._id = _id;
        this.cartID = cartID;
        this.accID = accID;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
        this.status = status;
        this.address = address;
        this.methodPay = methodPay;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "_id='" + _id + '\'' +
                ", cartID='" + cartID + '\'' +
                ", accID='" + accID + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalCost=" + totalCost +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", methodPay='" + methodPay + '\'' +
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

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMethodPay() {
        return methodPay;
    }

    public void setMethodPay(String methodPay) {
        this.methodPay = methodPay;
    }
}
